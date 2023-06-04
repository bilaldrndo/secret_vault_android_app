package com.example.secretvault.ui.screens.main_tab

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.secretvault.navigation.BottomNavGraph
import com.example.secretvault.ui.theme.Pink40
import com.example.secretvault.ui.theme.Purple40
import com.example.secretvault.ui.theme.Purple80
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.NotesViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainTabBarScreen(
    notesViewModel: NotesViewModel,
    contactsViewModel: ContactsViewModel,
) {
    val navController2 = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController2)
        }
    ) {
        BottomNavGraph(
            navController = navController2,
            notesViewModel = notesViewModel,
            contactsViewModel = contactsViewModel,
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = Purple40) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.4f),
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}