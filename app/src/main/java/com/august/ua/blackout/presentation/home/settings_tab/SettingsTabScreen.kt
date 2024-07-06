package com.august.ua.blackout.presentation.home.settings_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.august.ua.blackout.ui.components.AppButton
import com.pluto.Pluto
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin

@Composable
fun SettingsTabScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            text = "Open Debug",
            contentPadding = PaddingValues()
        ) {
            Pluto.open(PlutoRoomsDatabasePlugin.ID)
        }
    }
}