package com.andres_sagadoc.androidmaster.superheroapp

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.andres_sagadoc.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.andres_sagadoc.androidmaster.superheroapp.SuperHeroList.Companion.ID_SUPER_HERO
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

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
        binding.pbDetailSuperHero.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail: Response<SuperHeroDetailResponse> =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)
            if (superHeroDetail.body() != null) {
                runOnUiThread {
                    createUI(superHeroDetail.body()!!)
                }
            }
            runOnUiThread {
                binding.pbDetailSuperHero.isVisible = false
                binding.detailSuperHero.isVisible = true
            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerstats)
        binding.tvSuperHeroFullName.text = superHero.biography.fullName
        binding.tvSuperHeroPublisher.text = superHero.biography.publisher
    }

    private fun prepareStats(powerstats: Powerstats) {
        updateHeight(binding.vIntelligence, powerstats.intelligence)
        updateHeight(binding.vStrength, powerstats.strength)
        updateHeight(binding.vSpeed, powerstats.speed)
        updateHeight(binding.vDurability, powerstats.durability)
        updateHeight(binding.vPower, powerstats.power)
        updateHeight(binding.vCombat, powerstats.combat)
    }


    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        val statValid = if(stat == "null") "1" else stat
        params.height = pxToDp(statValid.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.superheroapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}