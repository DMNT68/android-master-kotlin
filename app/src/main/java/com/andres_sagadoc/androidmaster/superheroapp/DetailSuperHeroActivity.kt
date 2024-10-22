package com.andres_sagadoc.androidmaster.superheroapp

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.andres_sagadoc.androidmaster.superheroapp.SuperHeroList.Companion.ID_SUPER_HERO
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //enableEdgeToEdge()
        enableDarkModeActionBar()
        changeNameActionBar(getString(R.string.detail_superhero))

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idSuperHero: String = intent.getStringExtra(ID_SUPER_HERO).orEmpty()
        getSuperHeroInformation(idSuperHero)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail: Response<SuperHeroDetailResponse> =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)
            if (superHeroDetail.body() != null) {
                runOnUiThread {
                    createUI(superHeroDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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