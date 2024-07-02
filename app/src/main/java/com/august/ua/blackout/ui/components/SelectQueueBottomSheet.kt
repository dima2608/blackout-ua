package com.august.ua.blackout.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.august.ua.blackout.data.dvo.OblastDvo
import com.august.ua.blackout.data.dvo.OblastsDvo
import com.august.ua.blackout.ui.common.extensions.bottomSpacer

@Composable
fun SelectQueueBottomSheet(
    oblast: OblastDvo,
    onClick: (String) -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        state = scrollState,
    ) {
        items(oblast.queue) {
            QueueItem(queue = it) { selectedOblast ->
                onClick(selectedOblast)
            }
        }

        bottomSpacer()
    }
}