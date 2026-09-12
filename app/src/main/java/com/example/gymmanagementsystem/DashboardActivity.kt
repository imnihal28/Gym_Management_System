package com.example.gymmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        databaseHelper = DatabaseHelper(this)

        val memberCount = findViewById<TextView>(R.id.tvMemberCount)
        val activeCount = findViewById<TextView>(R.id.tvActiveCount)
        val trainerCount = findViewById<TextView>(R.id.tvTrainerCount)
        val attendanceCount = findViewById<TextView>(R.id.tvAttendanceCount)

        updateDashboardCounts(
            memberCount,
            activeCount,
            trainerCount,
            attendanceCount
        )

        val members = findViewById<Button>(R.id.btnMembers)
        val membership = findViewById<Button>(R.id.btnMembership)
        val trainers = findViewById<Button>(R.id.btnTrainers)
        val attendance = findViewById<Button>(R.id.btnAttendance)
        val payments = findViewById<Button>(R.id.btnPayments)
        val logout = findViewById<Button>(R.id.btnLogout)

        members.setOnClickListener {
            startActivity(Intent(this, MembersActivity::class.java))
        }

        membership.setOnClickListener {
            startActivity(Intent(this, MembershipActivity::class.java))
        }

        trainers.setOnClickListener {
            startActivity(Intent(this, TrainerActivity::class.java))
        }

        attendance.setOnClickListener {
            startActivity(Intent(this, AttendanceActivity::class.java))
        }

        payments.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }

        logout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        if (::databaseHelper.isInitialized) {
            val memberCount = findViewById<TextView>(R.id.tvMemberCount)
            val activeCount = findViewById<TextView>(R.id.tvActiveCount)
            val trainerCount = findViewById<TextView>(R.id.tvTrainerCount)
            val attendanceCount = findViewById<TextView>(R.id.tvAttendanceCount)

            updateDashboardCounts(
                memberCount,
                activeCount,
                trainerCount,
                attendanceCount
            )
        }
    }

    private fun updateDashboardCounts(
        memberCount: TextView,
        activeCount: TextView,
        trainerCount: TextView,
        attendanceCount: TextView
    ) {
        memberCount.text =
            "👥\n\n${databaseHelper.getMemberCount()}\nMembers"

        activeCount.text =
            "💳\n\n${databaseHelper.getActiveMemberCount()}\nActive"

        trainerCount.text =
            "🏋️\n\n${databaseHelper.getTrainerCount()}\nTrainers"

        attendanceCount.text =
            "📋\n\n${databaseHelper.getAttendanceCount()}\nAttendance"
    }
}