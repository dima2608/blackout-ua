package com.august.ua.blackout.utils.push_notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.august.ua.blackout.App
import com.august.ua.blackout.MainActivity
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.PushNotificationDto
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.domain.repository.user.UserRepository
import com.august.ua.blackout.ui.common.extensions.getDeviceHardwareId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationFirebaseMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var userRepository: UserRepository<Flow<UserDto?>, UserDto>

    private val job = SupervisorJob()
    private val coroutine = CoroutineScope(job + Dispatchers.Default)

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.i("onNewToken", p0)
        refreshNewToken(token = p0)
    }

    private fun refreshNewToken(token: String) {
        coroutine.launch {
            try {
                userRepository.sendFcmToken(
                    deviceId = getDeviceHardwareId(),
                    token = token
                )
            } catch (e: Exception) {
                Log.e(
                    NotificationFirebaseMessagingService::class.java.simpleName,
                    "refreshNewToken error: ${e.message.orEmpty()}"
                )
            }
        }
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(
            NotificationFirebaseMessagingService::class.java.simpleName,
            "==========>>>>> onMessageReceived"
        )
        processRemoteMessage(p0)
    }

    private fun processRemoteMessage(remoteMessage: RemoteMessage) {

        Log.i("received_push_data", remoteMessage.toString())
        Log.i("received_push_data", remoteMessage.data.toString())

        val pushId = remoteMessage.data["pushId"].orEmpty()
        val title = remoteMessage.data["title"].orEmpty()
        val body = remoteMessage.data["body"].orEmpty()
        val entityId = remoteMessage.data["entityId"]
        val date = remoteMessage.data["date"]
        //val example = Gson().fromJson(remoteMessage.data["example"], Example::class.java)

        if (!App.AppLifecycleTracker.appIsForeground) {
            showNotification(
                model = PushNotificationDto(
                    pushId = pushId,
                    title = title,
                    body = body,
                    date = date,
                    entityId = entityId,
                )
            )
        }
    }

    private fun showNotification(
        model: PushNotificationDto
    ) {
        val pendingIntent = buildPendingIntent(model = model)
        val notificationManager =
            ContextCompat.getSystemService(this, NotificationManager::class.java)
        val notification = builderNotificationCompat(
            model = model,
            pendingIntent = pendingIntent
        )
        notification.setChannelId(CHANNEL_ID)
        val ringtoneManager = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val audioAttributes = buildAudioAttributes()
        createNotificationChannel(ringtoneManager, audioAttributes, notificationManager)
        notificationManager?.notify(1, notification.build())
    }

    private fun buildPendingIntent(
        model: PushNotificationDto
    ): PendingIntent {
        return PendingIntent.getActivity(
            this,
            0,
            MainActivity.prepareIntent(
                model = model,
                context = this
            ),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun builderNotificationCompat(
        model: PushNotificationDto,
        pendingIntent: PendingIntent?
    ) = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(model.title)
        .setContentText(model.body)
        .setDefaults(Notification.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    private fun buildAudioAttributes() = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()

    private fun createNotificationChannel(
        ringtoneManager: Uri?,
        audioAttributes: AudioAttributes?,
        notificationManager: NotificationManager?
    ) {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE_HIGH)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        channel.setSound(ringtoneManager, audioAttributes)
        notificationManager?.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    companion object {
        const val CHANNEL_ID = "com.blackout.app"
        private const val CHANNEL_NAME = "BlackoutMessage"
    }

}