package com.andres_sagadoc.androidmaster

import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andres_sagadoc.androidmaster.FirstApp.FirstAppActivity
import com.andres_sagadoc.androidmaster.TODO.TodoActivity
import com.andres_sagadoc.androidmaster.imccalculator.IMCActivity
import com.andres_sagadoc.androidmaster.settings.SettingsActivity
import com.andres_sagadoc.androidmaster.superheroapp.SuperHeroList

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        enableDarkModeActionBar()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnIMC = findViewById<Button>(R.id.btnIMC)
        val btnTODO = findViewById<Button>(R.id.btnTodo)
        val btnSuperHero = findViewById<Button>(R.id.btnSuperHero)
        val btnSettings = findViewById<Button>(R.id.btnSettings)

        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        btnIMC.setOnClickListener { navigateToIMC() }
        btnTODO.setOnClickListener { navigateToTODO() }
        btnSuperHero.setOnClickListener { navigateToSuperHero() }
        btnSettings.setOnClickListener { navigateToSettings() }
    }


    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHero() {
        val intent = Intent(this, SuperHeroList::class.java)
        startActivity(intent)
    }

    private fun navigateToTODO() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMC() {
        val intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludApp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun enableDarkModeActionBar() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                // Modo oscuro
                supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.background_app)))
            }
        }
    }
}