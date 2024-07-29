package com.august.ua.blackout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R

@Composable
fun NoLocationFound(
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {

        Image(
            modifier = Modifier
                .padding(top = 100.dp)
                .padding(vertical = 12.dp),
            painter = painterResource(id = R.drawable.ic_no_locations),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.no_locations),
            modifier = Modifier.padding(top = 12.dp),
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = stringResource(id = R.string.no_locations_description),
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 12.dp),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

    }
}