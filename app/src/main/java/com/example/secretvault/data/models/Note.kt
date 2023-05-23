package com.example.secretvault.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.secretvault.util.Constants.NOTES_DATABASE_TABLE

@Entity(tableName = NOTES_DATABASE_TABLE)
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
)