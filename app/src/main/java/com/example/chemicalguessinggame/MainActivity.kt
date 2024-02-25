package com.example.chemicalguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var chemicalList: MutableList<String>
    private lateinit var inputChemicalName: EditText
    private lateinit var addChemicalButton: Button
    private lateinit var guessChemicalButton: Button
    private lateinit var guessChemicalName: EditText
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chemicalList = mutableListOf();


        inputChemicalName = findViewById(R.id.add_chemical_name)
        addChemicalButton = findViewById(R.id.add_chemical_btn)
        guessChemicalName = findViewById(R.id.guess_id)
        guessChemicalButton = findViewById(R.id.guess_btn)
        textView = findViewById(R.id.textView)


        addChemicalButton.setOnClickListener {
            val newChemical = inputChemicalName.text.toString().trim()
            handleAddChemical(newChemical)
        }


        guessChemicalButton.setOnClickListener {
            val guessedChemical = guessChemicalName.text.toString().trim()
            handleGuessChemical(guessedChemical)
        }
    }

    private fun handleAddChemical(newChemical: String) {

        if (newChemical.isNotEmpty()) {
            if (newChemical in chemicalList) {
                textView.text = "Chemical '$newChemical' is already available"
                inputChemicalName.text.clear()
            } else {
                chemicalList.add(newChemical)
                textView.text = "Chemical '$newChemical' added successfully"
                inputChemicalName.text.clear()
            }
        } else {
            textView.text = "Please enter a valid chemical name"
        }
    }

    private fun handleGuessChemical(guessedChemical: String) {
        if (chemicalList.isEmpty()) {
            textView.text = "Please add a chemical(s) first to play this game!."
            return
        }

        if (guessedChemical.isNotEmpty()) {

            val randomIndex = Random.nextInt(chemicalList.size)
            val randomChemical = chemicalList[randomIndex]

            if (guessedChemical.equals(randomChemical, ignoreCase = true)) {
                textView.text = "Congratulations! You guessed it right. It was $randomChemical"
            } else {
                textView.text = "Sorry! wrong guess. The correct answer was $randomChemical"
            }

            guessChemicalName.text.clear()
        } else {
            textView.text = "Please enter your guess"
        }
    }
}