package com.example.t_gamer.imc

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        calcBTN.setOnClickListener() {
            if (!weightEDT.text.toString().isEmpty() && !heightEDT.text.toString().isEmpty()) {
                val weight: Double = formatValues(weightEDT.text).toDouble()
                val height: Double = formatValues(heightEDT.text).toDouble()
                val result: Double = calcIMC(weight, height)
                setTextViewTXT(result)
            } else {
                renderError()
            }
        }
    }

    private fun renderError() {
        msgTXT.text = "Preencha todos os campos"
        msgTXT.setTextColor(Color.parseColor("#f44336"))
    }

    private fun formatValues(value: Editable): String {
        if (!value.isEmpty()) {
            return (value.toString().replace(",", "."))
        }
        return ""
    }

    private fun calcIMC(weight: Double, height: Double): Double {
        return weight / Math.pow(height, 2.0)
    }

    private fun setTextViewTXT(result: Double) {
        if (result < 17) msgTXT.text = "Muito abaixo do peso"
        else if (result > 17 && result < 18.5) msgTXT.text = "Abaixo do peso"
        else if (result > 18.5 && result < 25) msgTXT.text = "Peso normal"
        else if (result >= 25 && result < 30) msgTXT.text = "Acima do peso"
        else if (result >= 30 && result < 35) msgTXT.text = "Obesidade I"
        else if (result >= 35 && result < 40) msgTXT.text = "Obesidade II"
        else msgTXT.text = "Obesidade III (mórbida)"

        viewTXT.text = result.toBigDecimal().setScale(1, RoundingMode.UP).toDouble().toString().replace(".", ",")
    }
}
