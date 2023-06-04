package com.example.secretvault.util

object Constants {
    const val DATABASE_NAME = "app_database"
    const val NOTES_DATABASE_TABLE = "notes_table"
    const val CONTACTS_DATABASE_TABLE = "contacts_table"
    const val IMAGES_DATABASE_TABLE = "images_table"

    const val SPLASH_SCREEN = "splash"
    const val PIN_SCREEN = "pin"
    const val CALCULATOR_SCREEN = "calculator"
    const val MAIN_TAB_BAR_SCREEN = "maintabbarscreen"

    const val ITEM_ARGUMENT_KEY = "action"
    const val NEW_ITEM_ARGUMENT_KEY = "id"

    const val NOTES_SCREEN_BASE = "notes"
    const val NEW_NOTE_SCREEN_SCREEN_BASE = "newNote"
    const val NOTES_SCREEN = "$NOTES_SCREEN_BASE/{$ITEM_ARGUMENT_KEY}"
    const val NEW_NOTE_SCREEN = "$NEW_NOTE_SCREEN_SCREEN_BASE/{$NEW_ITEM_ARGUMENT_KEY}"

    const val CONTACTS_SCREEN_BASE = "contacts"
    const val NEW_CONTACT_SCREEN_SCREEN_BASE = "newContact"
    const val CONTACTS_SCREEN = "$CONTACTS_SCREEN_BASE/{$ITEM_ARGUMENT_KEY}"
    const val NEW_CONTACT_SCREEN = "$NEW_CONTACT_SCREEN_SCREEN_BASE/{$NEW_ITEM_ARGUMENT_KEY}"

    const val APP_PREFERENCES = "app_preferences"
    const val USER_PIN = "pin"
}