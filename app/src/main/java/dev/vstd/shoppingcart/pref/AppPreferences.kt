package dev.vstd.shoppingcart.pref

@Suppress("StaticFieldLeak")
object AppPreferences : Preferences("default") {
    var userCredential by stringPref(null)
}