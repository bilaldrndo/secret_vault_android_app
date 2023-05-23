package com.example.secretvault.ui.screens.pin

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.secretvault.ui.viewmodels.PinViewModel
import com.example.secretvault.util.Constants.CALCULATOR_SCREEN
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PinScreen(
    pinViewModel: PinViewModel = hiltViewModel(),
    navController: NavController,
) {

    var firstFieldTextContent: String by remember { mutableStateOf("") }
    var secondFieldTextContent: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
//            horizontalAlignment = HorizontalAlignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(70.dp))
            Text(text = "Please Enter Your 4 digit Pin that You are going to use and make sure to remember it:",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Box(modifier = Modifier.height(10.dp))
            PinField(
                value= firstFieldTextContent,
                onValueChange = {
                    if (it.length <= 4) {
                        firstFieldTextContent = it
                    }
                },
                label = "Enter PIN"
            )
            Box(modifier = Modifier.height(5.dp))
            PinField(
                value= secondFieldTextContent,
                onValueChange = {
                    if (it.length <= 4) {
                        secondFieldTextContent = it
                    }                },
                label = "Confirm PIN"
            )
            Box(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier.align(Alignment.End),
                shape = CutCornerShape(10),
                enabled = (firstFieldTextContent.length == 4
                        && firstFieldTextContent == secondFieldTextContent
                        && firstFieldTextContent.contains(Regex("^\\d+\$"))
                ),
                onClick = {
                    pinViewModel.saveOnBoardingState(pin = firstFieldTextContent.toInt())
                    navController.popBackStack()
                    navController.navigate(CALCULATOR_SCREEN)
                },

            ) {
                Text(text = "Continue")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label)},
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
    )
}