package com.example.secretvault.ui.screens.contact.new_contact

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secretvault.R
import com.example.secretvault.data.models.Priority
import com.example.secretvault.ui.components.PriorityDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactContent(
    nameAndSurname: String,
    onNameAndSurnameChange: (String) -> Unit,
    number: String,
    onNumberChange: (String) -> Unit,
) {
    val maxChar = 30
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp, 5.dp, 12.dp, 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameAndSurname,
            onValueChange =  {
                if (it.length < maxChar) {
                    onNameAndSurnameChange(it)
                }
            },
            label = { Text(text = stringResource(id = R.string.name_and_surname))},
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            singleLine = true,
            maxLines = 1,

        )
        Box(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = number,
            onValueChange =  {
                onNumberChange(it)
            },
            label = { Text(text = stringResource(id = R.string.number))},
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            singleLine = true,
            maxLines = 1,
        )
    }
}

