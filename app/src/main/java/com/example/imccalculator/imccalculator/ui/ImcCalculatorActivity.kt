package com.example.imccalculator.imccalculator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.imccalculator.R
import com.example.imccalculator.databinding.ActivityImcCalculatorBinding
import com.example.imccalculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 30
    private var currentHeight: Int = 120

    private lateinit var binding: ActivityImcCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initUI()
    }
    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun initListeners() {
        binding.viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        binding.viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        binding.rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            binding.tvHeight.text = "$currentHeight cm"
        }
        binding.btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        binding.btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        binding.btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        binding.btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        binding.btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble() /100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun setAge() {
        binding.tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        binding.tvWeight.text = currentWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        binding.viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        binding.viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {

        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }
}