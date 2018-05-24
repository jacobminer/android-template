package com.steamclock.template.persistence.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Template
 * Created by jake on 2018-05-23, 1:41 PM
 * Provides Kotlin singleton functionality for accessing SharedPreferences using Context. Best used with the SharedPreferenceExtensions
 */
object SharedPreferencesHelper {
    fun defaultPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPrefs(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }
}

