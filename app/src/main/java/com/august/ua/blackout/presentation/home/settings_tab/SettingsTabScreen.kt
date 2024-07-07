package com.august.ua.blackout.presentation.home.settings_tab

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import com.august.ua.blackout.MainActivity
import com.august.ua.blackout.ui.components.AppButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.pluto.Pluto
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin

@Composable
fun SettingsTabScreen() {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var tokenText by remember { mutableStateOf("")}

//    LaunchedEffect(Unit) {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                return@OnCompleteListener
//            }
//            val token = task.result
//            Log.d(MainActivity::class.java.simpleName, "token =========>>>>>> $token")
//            tokenText = token
//        })
//    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                text = "Open Debug",
                contentPadding = PaddingValues()
            ) {
                Pluto.open(PlutoRoomsDatabasePlugin.ID)
            }


            AppButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                text = "Get my FCM",
                contentPadding = PaddingValues()
            ) {
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    val token = task.result
                    Log.d(MainActivity::class.java.simpleName, "token =========>>>>>> $token")
                    clipboardManager.setText(AnnotatedString(token))
                })
            }
        }

    }
}