package dev.vstd.shoppingcart.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun intentOf(vararg data: Pair<String, String>) = Intent().apply {
    data.forEach { (key, value) -> putExtra(key, value) }
}