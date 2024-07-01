package com.august.ua.blackout.presentation.select_oblast

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.august.ua.blackout.ui.components.AppModalBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOblastBottomSheet(

    onDismissRequest: () -> Unit
) {
    AppModalBottomSheet(
        modifier = Modifier,
        onDismissRequest = onDismissRequest,

    ) {

    }
}