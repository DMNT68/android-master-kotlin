package com.andres_sagadoc.androidmaster.imccalculator

import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.imccalculator.IMCActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

    private var imc: Double = -1.0
    private var range: String = ""
    private var description: String = ""

    private lateinit var tvImc: TextView
    private lateinit var tvRange: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_result_imc)
        enableDarkModeActionBar()
        changeNameActionBar(getString(R.string.result_imc))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()

    }

    private fun initComponents() {
        tvImc = findViewById(R.id.tvIMC)
        btnRecalculate = findViewById(R.id.btnReCalculate)
        tvDescription = findViewById(R.id.tvDescription)
        tvRange = findViewById(R.id.tvRange)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { navigateToCalculte() }
    }

    private fun navigateToCalculte() {
        val intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }

    private fun setIMC() {
        imc = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        tvImc.text = "$imc"
    }

    private fun setRange() {
        when (imc) {
            in 0.00..18.50 -> {
                range = getString(R.string.underweight)
                description = getString(R.string.description_underweight)
                tvRange.setTextColor(getColor(R.color.underweight))
            }
            in 18.51..24.99 -> {
                range = getString(R.string.healthy_weight)
                description = getString(R.string.description_healthy_weight)
                tvRange.setTextColor(getColor(R.color.healthy_weight))
            }
            in 25.00..29.99 -> {
                range = getString(R.string.overweight)
                description = getString(R.string.description_overweight)
                tvRange.setTextColor(getColor(R.color.overweight))
            }
            in 30.00..99.00 -> {
                range = getString(R.string.obesity)
                description = getString(R.string.description_obesity)
                tvRange.setTextColor(getColor(R.color.obesity))
            }
            else -> {
                range = getString(R.string.error)
                description = getString(R.string.error)
                imc = 0.0
                tvImc.text = "$imc"
                tvRange.setTextColor(getColor(R.color.obesity))
            }
        }
        tvRange.text = range
        tvDescription.text = description

    }

    private fun initUI() {
        setIMC()
        setRange()
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