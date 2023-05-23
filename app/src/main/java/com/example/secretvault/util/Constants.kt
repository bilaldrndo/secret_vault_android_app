package com.example.secretvault.util

object Constants {
    const val NOTES_DATABASE_TABLE = "notes_table"
    const val DATABASE_NAME = "app_database"

    const val NOTES_SCREEN_BASE = "notes"
    const val NEW_NOTE_SCREEN_SCREEN_BASE = "newNote"

    const val NOTES_ARGUMENT_KEY = "action"
    const val NEW_NOTE_ARGUMENT_KEY = "noteId"

    const val CALCULATOR_SCREEN = "calculator"
    const val PIN_SCREEN = "pin"
    const val NOTES_SCREEN = "$NOTES_SCREEN_BASE/{$NOTES_ARGUMENT_KEY}"
    const val NEW_NOTE_SCREEN = "$NEW_NOTE_SCREEN_SCREEN_BASE/{$NEW_NOTE_ARGUMENT_KEY}"

    const val APP_PREFERENCES = "app_preferences"
    const val USER_PIN = "pin"
}