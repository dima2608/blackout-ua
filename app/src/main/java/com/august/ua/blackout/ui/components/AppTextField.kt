package com.august.ua.blackout.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.common.extensions.conditional
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import org.checkerframework.checker.units.qual.C

@Composable
fun InputTextField(
    modifier: Modifier,
    value: String,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    supportingText: String? = null,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    focusRequester: FocusRequester? = null,
    onFocusChanged: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    val focus = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .conditional(focusRequester != null) {
                Modifier
                    .focusRequester(focusRequester!!)
                    .onFocusChanged {
                        if (focus.value != it.isFocused) {
                            focus.value = it.isFocused
                            if (!it.isFocused) {
                                onFocusChanged?.invoke()
                            }
                        }
                    }
            },
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = singleLine,
        enabled = enabled,
        placeholder = if (placeholder.isNullOrEmpty()) {
            null
        } else {
            {
                Text(text = placeholder)
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = MaterialTheme.shapes.small,
        isError = isError,
        supportingText = if (supportingText.isNullOrEmpty()) {
            null
        } else {
            {
                Text(text = supportingText)
            }
        },
        trailingIcon = trailingIcon,
        readOnly = readOnly
    )

}

@DevicePreviews
@Composable
private fun AppTextFieldPreview() {
    BlackoutUaTheme {
        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = "example@gmail.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

        )
    }
}

@DevicePreviews
@Composable
private fun AppTextFieldPreviewError() {
    BlackoutUaTheme {
        InputTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = "",
            onValueChange = {},
            placeholder = "example@gmail.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = true,
            supportingText = "Error message",
            )
    }
}