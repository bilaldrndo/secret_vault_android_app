package com.example.secretvault.ui.screens.note.new_note

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
import com.example.secretvault.R
import com.example.secretvault.data.models.Priority
import com.example.secretvault.ui.components.PriorityDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
) {
    val maxChar = 20
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp, 0.dp, 12.dp, 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange =  {
                if (it.length < maxChar) {
                    onTitleChange(it)
                }
            },
            label = { Text(text = stringResource(id = R.string.title))},
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            maxLines = 1,

        )
        Box(modifier = Modifier.height(8.dp))
        PriorityDropdown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            value = description,
            onValueChange =  {
                onDescriptionChange(it)
            },
            label = { Text(text = stringResource(id = R.string.description))},
            textStyle = MaterialTheme.typography.bodyMedium,
        )
    }
}

