# CustomThemeManager


Custom Theme Manager in Android using Kotlin, allowing users to toggle between themes dynamically. This solution enables applying themes to the entire application without the need to handle each activity individually.
## Features

- Apply themes dynamically across the entire app.
- No need to handle themes on each activity.
## Installation

res/values/attrs.xml
```bash
 <resources>
    <attr name="textColor" format="color" />
</resources>
```

styles.xml
```bash
<resources>
    <!-- Default Theme -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="textColor">@color/defaultTextColor</item>
    </style>
    <!-- Alternate Theme -->
    <style name="AlternateTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="colorPrimary">@color/alternatePrimary</item>
        <item name="colorPrimaryDark">@color/alternatePrimaryDark</item>
        <item name="colorAccent">@color/alternateAccent</item>
        <item name="textColor">@color/alternateTextColor</item>
    </style>
</resources>

```

colors.xml
```bash
<resources>
    <color name="defaultTextColor">#000000</color> <!-- Black -->
    <color name="alternateTextColor">#FF0000</color> <!-- Red -->
    <color name="colorPrimary">#6200EE</color> <!-- Purple -->
    <color name="alternatePrimary">#FF5722</color> <!-- Orange -->
</resources>
```


ThemeManager
```bash
object ThemeManager {
private const val PREF_NAME = "theme_pref"
    private const val KEY_THEME = "key_theme"
    fun applyTheme(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val theme = sharedPreferences.getString(KEY_THEME, "AppTheme")
        when (theme) {
            "AppTheme" -> context.setTheme(R.style.AppTheme)
            "AlternateTheme" -> context.setTheme(R.style.AlternateTheme)
        }
    }
    fun saveTheme(context: Context, theme: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_THEME, theme).apply()
    }
}
```


Base Activity
```bash
open class BaseActivity : AppCompatActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the saved theme before calling super.onCreate()
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
    }
}
```
res/layout/activity_main.xml
```bash
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">
<TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello, World!"
        android:textSize="24sp"
        android:textColor="?attr/textColor" /> <!-- Dynamic text color based on theme -->
    <SwitchCompat
        android:id="@+id/themeSwitch"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Toggle Theme" />
</LinearLayout>
```
MainActivity
```bash
class MainActivity : BaseActivity() {
private lateinit var themeSwitch: SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        themeSwitch = findViewById(R.id.themeSwitch)
        // Set the switch based on the current theme
        val currentTheme = getSharedPreferences("theme_pref", MODE_PRIVATE).getString("key_theme", "AppTheme")
        themeSwitch.isChecked = currentTheme == "AlternateTheme"
        // Handle theme toggle
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ThemeManager.saveTheme(this, "AlternateTheme")
            } else {
                ThemeManager.saveTheme(this, "AppTheme")
            }
            // Recreate the activity to apply the theme change
            recreate()
        }
    }
}
```
If you have other activities in your app, make sure they also extend BaseActivity so the theme is applied to them as well.
```bash
class AnotherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
    }
}
```

AndroidManifest.xml
```bash
<application
    android:theme="@style/AppTheme"
    ... >
</application>
```

https://github.com/user-attachments/assets/baefa89d-334b-442f-94ef-d18df610c377


## Contact
For any inquiries, please contact prashant.karamadi31@gmail.com.
