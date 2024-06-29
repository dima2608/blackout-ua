package com.august.ua.blackout.presentation.common

import androidx.compose.ui.tooling.preview.Preview

/**
 * Multipreview annotation that represents various device sizes. Add this annotation to a composable
 * to render various devices.
 */
@Preview(name = "phone_1", device = "spec:shape=Normal,width=360,height=1920,unit=dp,dpi=240", showBackground = true, showSystemUi = true)
@Preview(name = "phone_2", device = "spec:shape=Normal,width=650,height=1334,unit=dp,dpi=240", showSystemUi = true)
@Preview(name = "phone_3", device = "spec:shape=Normal,width=1080,height=1920,unit=dp,dpi=120")
@Preview(name = "phone_4", device = "spec:shape=Normal,width=1440,height=2960,unit=dp,dpi=60")
annotation class DevicePreviews