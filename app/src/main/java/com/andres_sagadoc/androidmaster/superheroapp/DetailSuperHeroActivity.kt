package com.andres_sagadoc.androidmaster.superheroapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
        enableEdgeToEdge()

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
}