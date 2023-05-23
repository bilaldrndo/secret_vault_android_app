package com.example.secretvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.secretvault.ui.theme.ExampleTheme
import com.example.secretvault.ui.theme.shade_of_cyan_blue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.secretvault.navigation.SetupNavigation
import com.example.secretvault.ui.viewmodels.NotesViewModel
import com.example.secretvault.ui.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.secretvault.ui.viewmodels.PinViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val notesViewModel: NotesViewModel by viewModels()

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            ExampleTheme {
                val startScreen by splashViewModel.startDestination
                val pin by splashViewModel.pin

                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state

                navController = rememberNavController()

                SetupNavigation(
                    startScreen = startScreen,
                    pin = pin,
                    navController = navController,
                    notesViewModel = notesViewModel,
                    calculatorState = state,
                    calculatorOnAction = viewModel::onAction,
                )
            }
        }
    }
}