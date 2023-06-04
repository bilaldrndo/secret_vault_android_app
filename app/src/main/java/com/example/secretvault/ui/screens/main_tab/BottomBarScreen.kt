package com.example.secretvault.ui.screens.main_tab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.secretvault.R
import com.example.secretvault.util.Constants.CONTACTS_SCREEN
import com.example.secretvault.util.Constants.NOTES_SCREEN

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = NOTES_SCREEN,
        title = "Notes",
        icon = R.drawable.ic_note
    )

    object Profile : BottomBarScreen(
        route = CONTACTS_SCREEN,
        title = "Contacts",
        icon = R.drawable.ic_contact
    )
}