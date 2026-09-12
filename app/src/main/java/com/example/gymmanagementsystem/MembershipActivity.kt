package com.example.gymmanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MembershipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership)

        val monthly = findViewById<Button>(R.id.btnMonthly)
        val quarterly = findViewById<Button>(R.id.btnQuarterly)
        val yearly = findViewById<Button>(R.id.btnYearly)

        monthly.setOnClickListener {
            showPlan("Monthly Plan - ₹999")
        }

        quarterly.setOnClickListener {
            showPlan("Quarterly Plan - ₹2499")
        }

        yearly.setOnClickListener {
            showPlan("Yearly Plan - ₹7999")
        }
    }

    private fun showPlan(plan: String) {
        Toast.makeText(
            this,
            "$plan Selected",
            Toast.LENGTH_SHORT
        ).show()
    }
}