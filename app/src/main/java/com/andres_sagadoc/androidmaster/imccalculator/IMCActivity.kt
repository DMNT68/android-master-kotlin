package com.andres_sagadoc.androidmaster.imccalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andres_sagadoc.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 71
    private var currentAge: Int = 31
    private var currentHeight: Int = 170

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var sliderHeight: Slider
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var tvWeight: TextView
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
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
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFamale)
        tvHeight = findViewById(R.id.tvHeight)
        sliderHeight = findViewById(R.id.sliderHeight)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        tvAge = findViewById(R.id.tvAge)
        tvWeight = findViewById(R.id.tvWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            if (!isMaleSelected) {
                changeGender()
                setGenderColor()
            }
        }
        viewFemale.setOnClickListener {
            if (!isFemaleSelected) {
                changeGender()
                setGenderColor()
            }
        }

        sliderHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()
            tvHeight.text = "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeigth()
        }

        btnSubstractWeight.setOnClickListener {
            currentWeight -= 1
            setWeigth()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            calculateIMC()
        }

    }

    private fun calculateIMC() {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() / 100 * currentHeight.toDouble() / 100)
        val result = df.format(imc).toDouble()
        navigateToResult(result)
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeigth() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setHeight() {
        tvHeight.text = currentHeight.toString()
        sliderHeight.value = currentHeight.toFloat()
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference =
            if (isSelectedComponent) R.color.background_component_selected else R.color.background_component
        return ContextCompat.getColor(this, colorReference)
    }


    private fun initUI() {
        setGenderColor()
        setWeigth()
        setAge()
        setHeight()
    }

}