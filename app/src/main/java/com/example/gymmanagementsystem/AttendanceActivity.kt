package com.example.gymmanagementsystem

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AttendanceActivity : AppCompatActivity() {

    private lateinit var memberName: EditText
    private lateinit var present: RadioButton
    private lateinit var attendanceList: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        memberName = findViewById(R.id.etAttendanceMember)
        present = findViewById(R.id.radioPresent)
        attendanceList = findViewById(R.id.tvAttendance)

        val markAttendance =
            findViewById<Button>(R.id.btnMarkAttendance)

        databaseHelper = DatabaseHelper(this)

        showAttendance()

        markAttendance.setOnClickListener {

            val name = memberName.text.toString().trim()

            if (name.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter member name",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val status = if (present.isChecked) {
                    "Present"
                } else {
                    "Absent"
                }

                val success = databaseHelper.addAttendance(
                    name,
                    status
                )

                if (success) {

                    Toast.makeText(
                        this,
                        "Attendance Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    memberName.text.clear()
                    present.isChecked = true

                    showAttendance()

                } else {

                    Toast.makeText(
                        this,
                        "Failed to save attendance",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showAttendance() {

        attendanceList.text =
            databaseHelper.getAllAttendance()
    }
}