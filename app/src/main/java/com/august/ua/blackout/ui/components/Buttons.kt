package com.august.ua.blackout.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.ui.theme.BlackoutUaTheme

@Composable
fun AppButton(
    modifier: Modifier,
    image: ImageVector? = null,
    imageRes: Int? = null,
    imageTint: Color = Color.Unspecified,
    enabled: Boolean = true,
    text: String?,
    contentPadding: PaddingValues = PaddingValues(),
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        contentPadding = contentPadding,
        enabled = enabled,
        colors = buttonColors,
        onClick = onClick
    ) {
        if (image != null) {
            Icon(imageVector = image, contentDescription = null)
        }
        if (imageRes != null) {
            Icon(
                painter = painterResource(id = imageRes),
                tint = imageTint,
                contentDescription = null
            )
        }
        if (!text.isNullOrEmpty()) {
            if (image != null || imageRes != null) {
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            }
            Text(
                text = text,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier,
    image: ImageVector? = null,
    imageRes: Int? = null,
    imageTint: Color = Color.Unspecified,
    text: String? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit
) {
    AppButton(
        modifier = modifier,
        enabled = enabled,
        text = text,
        image = image,
        imageRes = imageRes,
        imageTint = imageTint,
        contentPadding = contentPadding,
        onClick = onClick
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier,
    image: ImageVector? = null,
    imageRes: Int? = null,
    imageTint: Color = Color.Unspecified,
    text: String? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit
) {
    AppButton(
        modifier = modifier,
        text = text,
        image = image,
        imageRes = imageRes,
        imageTint = imageTint,
        enabled = enabled,
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        contentPadding = contentPadding,
        onClick = onClick
    )
}

@Composable
fun SecondaryContainerButton(
    modifier: Modifier,
    image: ImageVector? = null,
    imageRes: Int? = null,
    imageTint: Color = Color.Unspecified,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    text: String? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit
) {
    AppButton(
        modifier = modifier,
        text = text,
        image = image,
        imageRes = imageRes,
        imageTint = imageTint,
        enabled = enabled,
        buttonColors = buttonColors,
        contentPadding = contentPadding,
        onClick = onClick
    )
}

@Composable
fun SecondaryContainerAlignStartButton(
    modifier: Modifier,
    image: ImageVector? = null,
    imageRes: Int? = null,
    imageTint: Color = Color.Unspecified,
    text: String? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ),
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        contentPadding = contentPadding,
        enabled = enabled,
        colors = colors,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null) {
                Icon(imageVector = image, contentDescription = null)
            }
            if (imageRes != null) {
                Icon(
                    painter = painterResource(id = imageRes),
                    tint = imageTint,
                    contentDescription = null
                )
            }
            if (!text.isNullOrEmpty()) {
                if (image != null || imageRes != null) {
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                }
                Text(
                    text = text,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
private fun PrimaryButton_Preview() {
    BlackoutUaTheme {
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "Primary",
            onClick = {}
        )
    }
}

@Composable
private fun SecondaryButton_Preview() {
    BlackoutUaTheme {
        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "Secondary",
            image = Icons.Filled.FavoriteBorder,
            onClick = {}
        )
    }
}

@Composable
private fun SecondaryButtonNoText_Preview() {
    BlackoutUaTheme {
        SecondaryButton(
            modifier = Modifier
                .padding(8.dp),
            image = Icons.Filled.FavoriteBorder,
            onClick = {}
        )
    }
}