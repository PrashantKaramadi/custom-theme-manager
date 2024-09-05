package com.pk.customthememanager.view.activities

import android.os.Bundle
import androidx.appcompat.widget.SwitchCompat
import com.pk.customthememanager.R
import com.pk.customthememanager.utils.ThemeManager

class MainActivity : BaseActivity() {
    private lateinit var themeSwitch: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the saved theme before the activity is created
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize your switch for theme toggling
        themeSwitch = findViewById(R.id.themeSwitch)

        // Set the switch state based on the current theme
        val savedTheme = ThemeManager.getSavedTheme(this)
        themeSwitch.isChecked = savedTheme == ThemeManager.ThemeType.ALTERNATE

        // Listen for switch toggle and apply theme
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val newTheme =
                if (isChecked) ThemeManager.ThemeType.ALTERNATE else ThemeManager.ThemeType.DEFAULT
            ThemeManager.saveTheme(this, newTheme)

            // Recreate the activity to apply the new theme
            recreate()
        }
    }
}