package dev.vstd.shoppingcart

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.vstd.shoppingcart.pref.Preferences
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        plantLog()
        initAppPreferences()
    }

    private fun plantLog() {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, tag, ">> $message", t)
            }
        })
    }

    private fun initAppPreferences() {
        Preferences.init(this)
    }
}