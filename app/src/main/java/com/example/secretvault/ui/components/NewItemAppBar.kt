package com.example.secretvault.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.secretvault.R
import com.example.secretvault.ui.theme.Purple40
import com.example.secretvault.util.Action
import com.example.secretvault.util.TypeOfScreen

@Composable
fun NewItemAppBar(
    topTitle: String = "",
    typeOfScreen: TypeOfScreen,
    navigateToScreenBefore: (Action) -> Unit,
) {
    if (topTitle == "") {
        NewItemAppBar(navigateToScreenBefore = navigateToScreenBefore, typeOfScreen = typeOfScreen)
    } else {
        ExistingItemAppBar(topTitle = topTitle, navigateToScreenBefore = navigateToScreenBefore)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemAppBar(
    navigateToScreenBefore: (Action) -> Unit,
    typeOfScreen: TypeOfScreen,
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40, titleContentColor = Color.White, actionIconContentColor = Color.White, navigationIconContentColor = Color.White),
        navigationIcon = {
            BackAction(onBackClicked = navigateToScreenBefore)
        },
        title = {
            if (typeOfScreen == TypeOfScreen.NoteScreen) {
                Text(text  = stringResource(id = R.string.new_note))
            } else {
                Text(text  = stringResource(id = R.string.new_contact))

            }
        },
        actions = {
            AddAction(onAddClicked = navigateToScreenBefore)
        }
    )
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(
        onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.check)
        )
    }
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingItemAppBar(
    topTitle: String,
    navigateToScreenBefore: (Action) -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40, titleContentColor = Color.White, actionIconContentColor = Color.White, navigationIconContentColor = Color.White),
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToScreenBefore)
        },
        title = {
            Text(
                topTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = {
            DeleteAction(onDeleteClicked = navigateToScreenBefore)
            UpdateAction(onUpdateClicked = navigateToScreenBefore)
        }
    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close)
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete)
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        onClick = {onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update)
        )
    }
}
