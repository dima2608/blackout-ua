package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.R
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.EggshellAlpha80
import com.august.ua.blackout.ui.theme.White

@Composable
fun CalendarDrawer(
    dividerColor: Color = Black.copy(0.25f),
) = ModalDrawerSheet(
//    drawerShape = MaterialTheme.shapes.extraSmall.copy(
//        bottomStart = CornerSize(0.dp),
//        topStart = CornerSize(0.dp)
//    ),
) {
    LazyColumn(
    ) {
        item {
            Image(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
            )
        }
        item {
            Column(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(dividerColor)
                )

                NavigationDrawerItem(
                    modifier = Modifier
                        .padding(end = 16.dp),
                    label = { Text(text = "Refresh") },
                    selected = false,
                    shape = MaterialTheme.shapes.medium.copy(topStart = CornerSize(0.dp), bottomStart = CornerSize(0.dp)),
                    icon = {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                    },
                    onClick = {

                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(dividerColor)
                )
            }
        }
    }
}