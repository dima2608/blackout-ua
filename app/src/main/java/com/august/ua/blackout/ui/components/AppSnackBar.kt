package com.august.ua.blackout.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.RedAttention
import com.august.ua.blackout.ui.theme.RedNegative
import com.august.ua.blackout.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppSnackBar(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
) = SnackbarHost(
    modifier = modifier,
    hostState = snackBarHostState,
) { snackBarData ->

    AppSnackBarContent(
        message = snackBarData.visuals.message,
        actionText = snackBarData.visuals.actionLabel,
        onDismiss = {
            snackBarData.dismiss()
        },
        onActionClick = {
            snackBarData.performAction()
        }
    )

}

@Composable
private fun AppSnackBarContent(
    message: String,
    actionText: String?,
    onDismiss: () -> Unit,
    onActionClick: () -> Unit,
) {
    Snackbar(
        dismissActionContentColor = White,
        dismissAction = {
            IconButton(
                onClick = { onDismiss() }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "close snack bar"
                )
            }
        },
        action = {
            actionText?.let { title ->
                TextButton(onClick = onActionClick) {
                    Text(
                        text = title,
                        modifier = Modifier,
                        style = BlackoutTextStyle.h5SmallestHeading,
                        color = White,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        },
        containerColor = RedNegative,
        shape = RoundedCornerShape(14.dp),
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 18.sp, fontSize = 15.sp),
            color = White
        )
    }
}

fun showSnackbar(
    message: String,
    actionLabel: String? = null,
    scope: CoroutineScope,
    snackbar: SnackbarHostState,
    onSnackbarDismissed: () -> Unit,
    duration: SnackbarDuration = SnackbarDuration.Indefinite,
    onActionPerformed: (() -> Unit)? = null,
) {
    scope.launch {
        snackbar.currentSnackbarData?.dismiss()
        val result = snackbar.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = duration
        )
        when (result) {
            SnackbarResult.ActionPerformed -> {
                onActionPerformed?.invoke() ?: onSnackbarDismissed()
            }

            SnackbarResult.Dismissed -> {
                onSnackbarDismissed()
            }
        }
    }
}
