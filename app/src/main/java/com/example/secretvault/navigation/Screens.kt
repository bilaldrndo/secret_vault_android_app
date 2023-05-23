package com.example.secretvault.navigation
//
//import androidx.navigation.NavHostController
//import com.example.secretvault.util.Action
//import com.example.secretvault.util.Constants.NEW_NOTE_SCREEN
//import com.example.secretvault.util.Constants.NEW_NOTE_SCREEN_SCREEN_BASE
//import com.example.secretvault.util.Constants.NOTES_ARGUMENT_KEY
//import com.example.secretvault.util.Constants.NOTES_SCREEN
//import com.example.secretvault.util.Constants.NOTES_SCREEN_BASE
//
//class Screens(navController: NavHostController) {
//    val notes: (Action) -> Unit = {action ->
//        navController.navigate("$NOTES_SCREEN_BASE/${action.name}") {
//            popUpTo(NOTES_SCREEN) {inclusive = true}
//        }
//    }
//    val newNote: (Int) -> Unit = {noteId ->
//        navController.navigate("$NEW_NOTE_SCREEN_SCREEN_BASE/$noteId")
//    }
//}