package com.ariflutfhansah.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ariflutfhansah.template.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGImageView
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.ariflutfhansah.template.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Ubah Tema
    private lateinit var themeSelect: SVGImageView
    private lateinit var sharedPreferences: SharedPreferences

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        setSupportActionBar(toolbar)
        navView.setupWithNavController(navController)

        // Memanggil Image SVG
        val theme_select: SVGImageView = findViewById(R.id.theme_select)

        val svg2: SVG = SVG.getFromResource(resources, R.raw.theme_light)
        theme_select.setSVG(svg2)

        // Ubah Tema
        sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false)

        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        themeSelect = findViewById(R.id.theme_select)

        // Mengatur listener untuk mengubah tema saat ikon diklik
        themeSelect.setOnClickListener {
            toggleTheme()
        }
    }

    private fun toggleTheme() {
        // Mengubah tema
        val isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false)
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.edit().putBoolean("isDarkTheme", false).apply()
            themeSelect.setSVG(SVG.getFromResource(resources, R.raw.theme_light)) // Ganti dengan ikon light
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPreferences.edit().putBoolean("isDarkTheme", true).apply()
            themeSelect.setSVG(SVG.getFromResource(resources, R.raw.theme_dark)) // Ganti dengan ikon dark
        }
    }
}