package com.example.secretvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.secretvault.ui.theme.SecretVaultTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.secretvault.navigation.SetupNavigation
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.ui.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.secretvault.ui.screens.splash.SplashScreen
import com.example.secretvault.ui.viewmodels.CalculatorViewModel
import com.example.secretvault.ui.viewmodels.ContactsViewModel
import com.example.secretvault.ui.viewmodels.PinViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val splashViewModel: SplashViewModel by viewModels()
    private val pinViewModel: PinViewModel by viewModels()

    private val notesViewModel: NotesViewModel by viewModels()
    private val contactsViewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            SecretVaultTheme {
                val startScreen by splashViewModel.startDestination
                val pin by splashViewModel.pin

                val calcViewModel = viewModel<CalculatorViewModel>()
                val calcState = calcViewModel.state

                navController = rememberNavController()

                SetupNavigation(
                    startScreen = startScreen,
                    pin = pin,
                    navController = navController,
                    notesViewModel = notesViewModel,
                    contactsViewModel = contactsViewModel,
                    pinViewModel = pinViewModel,
                    splashViewModel = splashViewModel,
                    calculatorState = calcState,
                    calculatorOnAction = calcViewModel::onAction,
                )
            }
        }
    }
}