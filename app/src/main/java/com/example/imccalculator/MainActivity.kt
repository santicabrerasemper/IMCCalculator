package com.example.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imccalculator.databinding.ActivityMainBinding
import com.example.imccalculator.imccalculator.ui.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnGoToImcCalculator.setOnClickListener{
            navigateToImcCalculatorApp()
        }
    }
    private fun navigateToImcCalculatorApp(){
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}