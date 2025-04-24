package com.example.calculatortip

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptions: RadioGroup
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var roundUpSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var tipResultTextView: TextView

    companion object {
        private const val KEY_COST = "key_cost"
        private const val KEY_TIP_RESULT = "key_tip_result"
        private const val KEY_RADIO_ID = "key_radio_id"
        private const val KEY_SWITCH_STATE = "key_switch_state"
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        costOfServiceEditText = findViewById(R.id.cost_of_service_edit_text)
        tipOptions = findViewById(R.id.tip_options)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        calculateButton = findViewById(R.id.calculate_button)
        tipResultTextView = findViewById(R.id.tip_result)

        // Restore state setelah rotasi
        if (savedInstanceState != null) {
            costOfServiceEditText.setText(savedInstanceState.getString(KEY_COST, ""))
            tipResultTextView.text = savedInstanceState.getString(KEY_TIP_RESULT, "")
            tipOptions.check(savedInstanceState.getInt(KEY_RADIO_ID, R.id.amazing_option))
            roundUpSwitch.isChecked = savedInstanceState.getBoolean(KEY_SWITCH_STATE, false)
        }

        calculateButton.setOnClickListener {
            val costInput = costOfServiceEditText.text.toString()
            val cost = costInput.toDoubleOrNull()

            if (cost == null || cost == 0.0) {
                costOfServiceEditText.error = "Masukkan angka yang valid dan lebih besar dari 0"
                Toast.makeText(this, "Input tidak valid! Masukkan angka yang benar.", Toast.LENGTH_SHORT).show()
                tipResultTextView.text = getString(R.string.tip_amount, 0.0)
                return@setOnClickListener
            }

            val tipPercentage = when (tipOptions.checkedRadioButtonId) {
                R.id.amazing_option -> 0.20
                R.id.good_option -> 0.18
                R.id.okay_option -> 0.15
                else -> 0.0
            }

            var tip = cost * tipPercentage

            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            tipResultTextView.text = "Tip Amount: $${"%.2f".format(tip)}"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_COST, costOfServiceEditText.text.toString())
        outState.putString(KEY_TIP_RESULT, tipResultTextView.text.toString())
        outState.putInt(KEY_RADIO_ID, tipOptions.checkedRadioButtonId)
        outState.putBoolean(KEY_SWITCH_STATE, roundUpSwitch.isChecked)
    }
}