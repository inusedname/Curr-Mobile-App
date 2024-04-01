package dev.vstd.shoppingcart.pref

object AppPreferences : Preferences("default") {
    var userCredential by stringPref(null)
}