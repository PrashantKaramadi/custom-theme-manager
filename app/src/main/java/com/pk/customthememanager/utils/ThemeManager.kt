package com.pk.customthememanager.utils

import android.app.Activity
import android.content.Context
import com.pk.customthememanager.R

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_THEME = "theme"

    enum class ThemeType {
        DEFAULT,
        ALTERNATE
    }

    // Apply the saved theme when the app starts or theme is changed
    fun applyTheme(activity: Activity) {
        val savedTheme = getSavedTheme(activity)
        activity.setTheme(
            when (savedTheme) {
                ThemeType.DEFAULT -> R.style.AppTheme
                ThemeType.ALTERNATE -> R.style.AlternateTheme
            }
        )
    }

    // Save the user's theme choice
    fun saveTheme(context: Context, themeType: ThemeType) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_THEME, themeType.name).apply()
    }

    // Get the saved theme from SharedPreferences
    fun getSavedTheme(context: Context): ThemeType {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val themeName = sharedPreferences.getString(KEY_THEME, ThemeType.DEFAULT.name)
        return ThemeType.valueOf(themeName ?: ThemeType.DEFAULT.name)
    }
}