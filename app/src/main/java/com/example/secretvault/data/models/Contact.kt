package com.example.secretvault.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.secretvault.util.Constants.CONTACTS_DATABASE_TABLE

@Entity(tableName = CONTACTS_DATABASE_TABLE)
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nameAndSurname: String,
    val number: String,
)