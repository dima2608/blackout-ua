package com.august.ua.blackout.ui.common.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.LocaleList
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

val Context.isConnected: Boolean
    get() {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

val Context.isNotConnected: Boolean
    get() {
        return !isConnected
    }

fun Context.isNotificationPermissionGranted(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else true
}

fun Context.openTelegram(chatLink: String) {
    try {
        val telegram = Intent(Intent.ACTION_VIEW, Uri.parse(chatLink))
        telegram.setPackage("org.telegram.messenger")
        if (hasTelegramApplication()) {
            ContextCompat.startActivity(this, telegram, null)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(chatLink))
            if (webIntent.resolveActivity(packageManager) != null) {
                ContextCompat.startActivity(this, webIntent, null)
            }
        }
    } catch (e: Throwable) {
        Log.e("Open Telegram Exception","message: ${e.message}")
    }
}

fun Context.hasTelegramApplication(): Boolean {
    val packageManager = packageManager
    return try {
        val applicationInfo = packageManager.getApplicationInfo("org.telegram.messenger", 0)
        applicationInfo.enabled
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

@SuppressLint("HardwareIds")
fun Context.getDeviceHardwareId(): String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

fun Context.updateResources(language: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(language)
    } else {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        applicationContext.resources.updateConfiguration(configuration, applicationContext.resources.displayMetrics)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}