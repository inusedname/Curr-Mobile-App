package dev.vstd.shoppingcart.common.pref

import android.content.Context
import kotlin.reflect.KClass

@Suppress("StaticFieldLeak")
open class Preferences(
    private val name: String,
) {
    private val sharedPreferences by lazy {
        if (context == null) {
            throw IllegalStateException("Context not initialized")
        } else {
            context!!.getSharedPreferences(name, Context.MODE_PRIVATE)
        }
    }

    class Preference<T : Any?> constructor(private val defaultValue: T, private val klass: KClass<*>) {
        operator fun getValue(preferences: Preferences, property: kotlin.reflect.KProperty<*>): T {
            if (!preferences.sharedPreferences.contains(property.name)) {
                return defaultValue
            }
            return when (klass) {
                Int::class -> {
                    preferences.sharedPreferences.getInt(property.name, defaultValue as Int) as T
                }

                String::class -> {
                    preferences.sharedPreferences.getString(property.name, defaultValue as String?) as T
                }

                Boolean::class -> {
                    preferences.sharedPreferences.getBoolean(property.name, defaultValue as Boolean) as T
                }

                Float::class -> {
                    preferences.sharedPreferences.getFloat(property.name, defaultValue as Float) as T
                }

                Long::class -> {
                    preferences.sharedPreferences.getLong(property.name, defaultValue as Long) as T
                }

                else -> {
                    throw IllegalArgumentException("This type (${klass.simpleName}) can be saved into Preferences")
                }
            }
        }

        operator fun setValue(
            preferences: Preferences,
            property: kotlin.reflect.KProperty<*>,
            value: T,
        ) {
            with(preferences.sharedPreferences.edit()) {
                when (value) {
                    is Int -> putInt(property.name, value)
                    is String -> putString(property.name, value)
                    is Boolean -> putBoolean(property.name, value)
                    is Float -> putFloat(property.name, value)
                    is Long -> putLong(property.name, value)
                    else -> throw IllegalArgumentException("This type (${klass.simpleName}) can be saved into Preferences")
                }
                apply()
            }
        }
    }

    protected fun booleanPref(defaultValue: Boolean) = Preference(defaultValue, Boolean::class)
    protected fun intPref(defaultValue: Int) = Preference(defaultValue, Int::class)
    protected fun stringPref(defaultValue: String?) = Preference(defaultValue, String::class)
    protected fun floatPref(defaultValue: Float) = Preference(defaultValue, Float::class)
    protected fun longPref(defaultValue: Long) = Preference(defaultValue, Long::class)

    companion object {
        private var context: Context? = null

        fun init(context: Context) {
            Companion.context = context
        }
    }
}