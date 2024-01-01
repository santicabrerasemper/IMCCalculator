package com.example.imccalculator.imccalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.imccalculator.R
import com.example.imccalculator.databinding.ActivityImcCalculatorBinding
import com.example.imccalculator.databinding.ActivityResultImcactivityBinding
import com.example.imccalculator.imccalculator.ui.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultImcactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRecalculate.setOnClickListener{onBackPressedDispatcher.onBackPressed()}
    }

    private fun initUI(result: Double) {
        binding.tvImc.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                binding.tvResult.text = getString(R.string.title_bajo_peso)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                binding.tvDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> { //Peso normal
                binding.tvResult.text = getString(R.string.title_peso_normal)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                binding.tvDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> { //Sobrepeso
                binding.tvResult.text = getString(R.string.title_sobre_peso)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.sobre_peso))
                binding.tvDescription.text = getString(R.string.description_sobre_peso)
            }
            in 30.00..99.00 -> { //Obesidad
                binding.tvResult.text = getString(R.string.title_obesidad)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                binding.tvDescription.text = getString(R.string.description_obesidad)
            }
            else -> {//error
                binding.tvImc.text = getString(R.string.error)
                binding.tvResult.text = getString(R.string.error)
                binding.tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                binding.tvDescription.text = getString(R.string.error)
            }
        }
    }
}

