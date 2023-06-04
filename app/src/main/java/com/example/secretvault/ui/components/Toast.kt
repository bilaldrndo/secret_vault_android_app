package com.example.secretvault.ui.components

import android.content.Context
import android.widget.Toast

fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty",
        Toast.LENGTH_SHORT
    ).show()
}