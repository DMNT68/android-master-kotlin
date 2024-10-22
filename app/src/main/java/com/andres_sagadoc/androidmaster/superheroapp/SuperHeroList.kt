package com.andres_sagadoc.androidmaster.superheroapp

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroList : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var superHeroAdapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        // IU setting
        enableDarkModeActionBar()
        changeNameActionBar(getString(R.string.superHeroList))

        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.svHero.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean = false
        })

        superHeroAdapter = SuperHeroAdapter()
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = superHeroAdapter
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperHeros(query)
            if (myResponse.isSuccessful) {
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        superHeroAdapter.updateList(response.superHeros)
                    }
                }
            } else {
                Log.i("andres", "No funciona :(")
            }
            runOnUiThread {
                binding.progressBar.isVisible = false
            }
        }
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