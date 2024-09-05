package com.pk.customthememanager.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pk.customthememanager.utils.ThemeManager

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
    }
}