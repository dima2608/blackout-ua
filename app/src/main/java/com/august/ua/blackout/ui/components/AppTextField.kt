package com.august.ua.blackout.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import org.checkerframework.checker.units.qual.C

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onClick: () -> Unit,
    isError: Boolean,
//    icon: ImageVector,
//    errorMessage: String,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        readOnly = true,
        onValueChange = {

        },
        textStyle = BlackoutTextStyle.t3TextBody,
        label = {
            Text(
                text = placeholder,
                style = BlackoutTextStyle.t4TextSmallDescription
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = BlackoutTextStyle.t4TextSmallDescription
            )
        },
        shape = MaterialTheme.shapes.small,
        isError = isError,
        singleLine = true
    )
}

@DevicePreviews
@Composable
private fun AppTextFieldPreview() {
    BlackoutUaTheme {
        AppTextField(
            value = "",
            placeholder = "labnnle",
            onClick = {

            },
            isError = false
        )
    }
}