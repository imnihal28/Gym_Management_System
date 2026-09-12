package com.example.gymmanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MembersActivity : AppCompatActivity() {

    private lateinit var memberName: EditText
    private lateinit var memberPhone: EditText
    private lateinit var memberEmail: EditText
    private lateinit var membership: EditText
    private lateinit var membersList: TextView

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)

        memberName = findViewById(R.id.etMemberName)
        memberPhone = findViewById(R.id.etMemberPhone)
        memberEmail = findViewById(R.id.etMemberEmail)
        membership = findViewById(R.id.etMembership)
        membersList = findViewById(R.id.tvMembers)

        val addMember = findViewById<Button>(R.id.btnAddMember)

        databaseHelper = DatabaseHelper(this)

        // Display saved members
        showMembers()

        addMember.setOnClickListener {

            val name = memberName.text.toString().trim()
            val phone = memberPhone.text.toString().trim()
            val email = memberEmail.text.toString().trim()
            val plan = membership.text.toString().trim()

            if (name.isEmpty() ||
                phone.isEmpty() ||
                email.isEmpty() ||
                plan.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val success = databaseHelper.addMember(
                    name,
                    phone,
                    email,
                    plan
                )

                if (success) {

                    Toast.makeText(
                        this,
                        "Member Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    memberName.text.clear()
                    memberPhone.text.clear()
                    memberEmail.text.clear()
                    membership.text.clear()

                    showMembers()

                } else {

                    Toast.makeText(
                        this,
                        "Failed to add member",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showMembers() {
        membersList.text = databaseHelper.getAllMembers()
    }
}