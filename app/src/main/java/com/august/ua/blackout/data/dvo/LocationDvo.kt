package com.august.ua.blackout.data.dvo

import androidx.compose.ui.graphics.vector.ImageVector

data class LocationDvo(
    val status: ElectricityStatus,
    val queueAndLocation: String,
    val period: String,
    val lightTurnOnIn: String,
    val locationName: String,
    val icon: ImageVector,
    val isCollapsedState: Boolean,
    val order: Int? = null,
    val canBeCollapsed: Boolean
)