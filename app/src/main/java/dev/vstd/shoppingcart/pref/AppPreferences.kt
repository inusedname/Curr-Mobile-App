package dev.vstd.shoppingcart.pref

class AppPreferences: Preferences("default") {
    private var userCredential by stringPref(null)
}