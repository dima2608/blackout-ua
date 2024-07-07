package com.august.ua.blackout.data.dvo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

enum class LocationIconType(
    val icon: ImageVector
) {

    Location(
        icon = Icons.Default.LocationOn
    ),
    Home(
        icon = Icons.Default.Home
    ),
    Work(
        icon = Icons.Default.Work
    ),
    Restaurant(
        icon = Icons.Default.Restaurant
    ),
    Shopping(
        icon = Icons.Default.ShoppingCart
    ),
    Dumbbell(
        icon = Icons.Default.SportsVolleyball
    ),
    Book(
        icon = Icons.Default.Book
    ),
    Car(
        icon = Icons.Default.DirectionsCar
    ),
    Mail(
        icon = Icons.Default.Mail
    ),
    Apartment(
        icon = Icons.Default.Apartment
    ),
    Diamond(
        icon = Icons.Default.Diamond
    ),
}