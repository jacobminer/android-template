package com.steamclock.template.extensions

import android.content.Context
import android.content.SharedPreferences
import com.steamclock.template.persistence.local.SharedPreferencesHelper

/**
 * Template
 * Created by jake on 2018-05-23, 1:47 PM
 * Makes using shared preferences easier when in an activity, fragment, or other context based object.
 */
val Context.defaultPrefs: SharedPreferences
    get() = SharedPreferencesHelper.defaultPrefs(this)

fun Context.customPrefs(name: String) = SharedPreferencesHelper.customPrefs(this, name)