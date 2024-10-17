package com.andres_sagadoc.androidmaster.FirstApp

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andres_sagadoc.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        enableDarkModeActionBar()
        changeNameActionBar(getString(R.string.result_first_app))

        val tvResult = findViewById<TextView>(R.id.tvTitleResult)
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text = "Hola $name"
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
    private fun changeNameActionBar(name: String) {
        supportActionBar?.title = name
    }
}