package com.andres_sagadoc.androidmaster.superheroapp

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.superheroapp.SuperHeroList.Companion.ID_SUPER_HERO

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //enableEdgeToEdge()
        enableDarkModeActionBar()
        changeNameActionBar(getString(R.string.detail_superhero))

        setContentView(R.layout.activity_detail_super_hero)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idSuperHero: String = intent.extras?.getString(ID_SUPER_HERO).orEmpty()
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