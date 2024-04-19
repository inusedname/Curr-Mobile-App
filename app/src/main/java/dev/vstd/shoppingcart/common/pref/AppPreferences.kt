package dev.vstd.shoppingcart.common.pref

@Suppress("StaticFieldLeak")
object AppPreferences : Preferences("default") {
    var userCredential by stringPref(null)
}