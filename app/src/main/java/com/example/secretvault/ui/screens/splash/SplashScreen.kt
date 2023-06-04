package com.example.secretvault.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secretvault.R
import com.example.secretvault.ui.theme.PurpleGrey80
import com.example.secretvault.ui.viewmodels.SplashViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SplashScreen(
    navigateToNewScreen: () -> Unit,
    splashViewModel: SplashViewModel,
) {
    LaunchedEffect(key1 = splashViewModel.isLoading.value) {
        delay(2000)
        navigateToNewScreen()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey80),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = null,
                modifier = Modifier.width(80.dp)
            )
            Box(modifier = Modifier.height(10.dp))
            Text(
                text = "Calculator",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                fontSize = 22.sp,
            )
        }
    }
}