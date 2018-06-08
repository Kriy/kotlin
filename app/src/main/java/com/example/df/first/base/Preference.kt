package com.example.df.first.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.df.first.constant.Constant
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(private val name: String, private val defalt: T) : ReadWriteProperty<Any?, T> {

    companion object {
        lateinit var preferences: SharedPreferences

        fun setContext(context: Context) {
            preferences = context.getSharedPreferences(
                    context.packageName + Constant.SHARED_NAME,
                    Context.MODE_PRIVATE
            )
        }

        fun clear() {
            preferences.edit().clear().apply()
        }

    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, defalt)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = pubPreference(name, value)

    @Suppress("UNCHECKED_CAST")
    private fun <U> findPreference(name: String, defalt: U): U = with(preferences) {
        val res: Any = when (defalt) {
            is Long -> getLong(name, defalt)
            is String -> getString(name, defalt)
            is Int -> getInt(name, defalt)
            is Boolean -> getBoolean(name, defalt)
            is Float -> getFloat(name, defalt)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    @SuppressLint("CommitPrefEdits")
    private fun <U> pubPreference(name: String, value: U) = with(preferences.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}