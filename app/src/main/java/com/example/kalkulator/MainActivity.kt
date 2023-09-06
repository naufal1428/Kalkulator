package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentInput = ""
    private var angka1 = 0.0
    private var angka2 = 0.0
    private var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun inputNilai(view: View) {
        if (view is Button) {
            currentInput += view.text.toString()
            updateInput()
        }
    }

    fun operator(view: View) {
        if (view is Button) {
            if (currentInput.isNotEmpty()) {
                if (currentOperator.isEmpty()) {
                    angka1 = currentInput.toDouble()
                } else {
                    angka2 = currentInput.toDouble()
                }
                currentInput = ""
                currentOperator = view.text.toString()
                updateAngka()
            }
        }
    }

    fun onEqualClick(view: View) {
        if (currentInput.isNotEmpty() && currentOperator.isNotEmpty()) {
            angka2 = currentInput.toDouble()
            val result = performOperation()
            currentInput = result.toString()
            currentOperator = ""
            updateTextViews()
        }
    }

    fun onClearClick(view: View) {
        currentInput = ""
        angka1 = 0.0
        angka2 = 0.0
        currentOperator = ""
        updateTextViews()
    }

    private fun performOperation(): Double {
        return when (currentOperator) {
            "+" -> angka1 + angka2
            "-" -> angka1 - angka2
            "*" -> angka1 * angka2
            "/" -> angka1 / angka2
            else -> 0.0
        }
    }

    private fun updateInput() {
        binding.hasilTextView.text = "$currentInput"

    }

    private fun updateAngka() {

        binding.inputAngka1.text = "Angka 1: $angka1"

    }

    private fun updateTextViews() {
        binding.inputAngka1.text = "Angka 1: $angka1"
        binding.inputAngka2.text = "Angka 2: $angka2"
        binding.hasilTextView.text = "Hasil: $currentInput"
    }
}