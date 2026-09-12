package com.example.gymmanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TrainerActivity : AppCompatActivity() {

    private lateinit var trainerName: EditText
    private lateinit var trainerPhone: EditText
    private lateinit var trainerEmail: EditText
    private lateinit var specialization: EditText
    private lateinit var experience: EditText
    private lateinit var trainersList: TextView

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)

        trainerName = findViewById(R.id.etTrainerName)
        trainerPhone = findViewById(R.id.etTrainerPhone)
        trainerEmail = findViewById(R.id.etTrainerEmail)
        specialization = findViewById(R.id.etSpecialization)
        experience = findViewById(R.id.etExperience)
        trainersList = findViewById(R.id.tvTrainers)

        val addTrainer = findViewById<Button>(R.id.btnAddTrainer)

        databaseHelper = DatabaseHelper(this)

        showTrainers()

        addTrainer.setOnClickListener {

            val name = trainerName.text.toString().trim()
            val phone = trainerPhone.text.toString().trim()
            val email = trainerEmail.text.toString().trim()
            val spec = specialization.text.toString().trim()
            val exp = experience.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() ||
                email.isEmpty() || spec.isEmpty() || exp.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val success = databaseHelper.addTrainer(
                    name,
                    phone,
                    email,
                    spec,
                    exp
                )

                if (success) {

                    Toast.makeText(
                        this,
                        "Trainer Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    trainerName.text.clear()
                    trainerPhone.text.clear()
                    trainerEmail.text.clear()
                    specialization.text.clear()
                    experience.text.clear()

                    showTrainers()

                } else {

                    Toast.makeText(
                        this,
                        "Failed to add trainer",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showTrainers() {
        trainersList.text = databaseHelper.getAllTrainers()
    }
}