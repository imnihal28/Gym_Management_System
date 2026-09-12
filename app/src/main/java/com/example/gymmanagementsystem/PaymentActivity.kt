package com.example.gymmanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    private lateinit var memberName: EditText
    private lateinit var amount: EditText
    private lateinit var paid: RadioButton
    private lateinit var paymentList: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        memberName = findViewById(R.id.etPaymentMember)
        amount = findViewById(R.id.etPaymentAmount)
        paid = findViewById(R.id.radioPaid)
        paymentList = findViewById(R.id.tvPayments)

        val addPayment = findViewById<Button>(R.id.btnAddPayment)

        databaseHelper = DatabaseHelper(this)

        showPayments()

        addPayment.setOnClickListener {

            val name = memberName.text.toString().trim()
            val paymentAmount = amount.text.toString().trim()

            if (name.isEmpty() || paymentAmount.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val status = if (paid.isChecked) {
                    "Paid"
                } else {
                    "Pending"
                }

                val success = databaseHelper.addPayment(
                    name,
                    paymentAmount,
                    status
                )

                if (success) {

                    Toast.makeText(
                        this,
                        "Payment Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    memberName.text.clear()
                    amount.text.clear()
                    paid.isChecked = true

                    showPayments()

                } else {

                    Toast.makeText(
                        this,
                        "Failed to save payment",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showPayments() {
        paymentList.text = databaseHelper.getAllPayments()
    }
}