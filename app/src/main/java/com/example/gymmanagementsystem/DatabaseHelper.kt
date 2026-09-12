package com.example.gymmanagementsystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "GymDatabase", null, 3) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE members (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                phone TEXT,
                email TEXT,
                membership TEXT
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE attendance (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                member_name TEXT,
                status TEXT
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE payments (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                member_name TEXT,
                amount TEXT,
                status TEXT
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE trainers (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                phone TEXT,
                email TEXT,
                specialization TEXT,
                experience TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        if (oldVersion < 2) {

            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS attendance (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    member_name TEXT,
                    status TEXT
                )
                """.trimIndent()
            )

            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS payments (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    member_name TEXT,
                    amount TEXT,
                    status TEXT
                )
                """.trimIndent()
            )
        }

        if (oldVersion < 3) {
            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS trainers (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT,
                    phone TEXT,
                    email TEXT,
                    specialization TEXT,
                    experience TEXT
                )
                """.trimIndent()
            )
        }
    }

    fun addMember(
        name: String,
        phone: String,
        email: String,
        membership: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put("name", name)
        values.put("phone", phone)
        values.put("email", email)
        values.put("membership", membership)

        val result = db.insert("members", null, values)

        db.close()

        return result != -1L
    }

    fun getAllMembers(): String {

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM members", null)

        val data = StringBuilder()

        if (cursor.count == 0) {

            data.append("No members added yet")

        } else {

            while (cursor.moveToNext()) {

                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val phone = cursor.getString(2)
                val email = cursor.getString(3)
                val membership = cursor.getString(4)

                data.append(
                    "Member ID: $id\n" +
                            "Name: $name\n" +
                            "Phone: $phone\n" +
                            "Email: $email\n" +
                            "Membership: $membership\n\n"
                )
            }
        }

        cursor.close()
        db.close()

        return data.toString()
    }

    fun addAttendance(
        memberName: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put("member_name", memberName)
        values.put("status", status)

        val result = db.insert("attendance", null, values)

        db.close()

        return result != -1L
    }

    fun getAllAttendance(): String {

        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM attendance",
            null
        )

        val data = StringBuilder()

        if (cursor.count == 0) {

            data.append("No attendance records yet")

        } else {

            while (cursor.moveToNext()) {

                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val status = cursor.getString(2)

                data.append(
                    "Attendance ID: $id\n" +
                            "Member: $name\n" +
                            "Status: $status\n\n"
                )
            }
        }

        cursor.close()
        db.close()

        return data.toString()
    }

    fun addPayment(
        memberName: String,
        amount: String,
        status: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put("member_name", memberName)
        values.put("amount", amount)
        values.put("status", status)

        val result = db.insert("payments", null, values)

        db.close()

        return result != -1L
    }

    fun getAllPayments(): String {

        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM payments",
            null
        )

        val data = StringBuilder()

        if (cursor.count == 0) {

            data.append("No payment records yet")

        } else {

            while (cursor.moveToNext()) {

                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val amount = cursor.getString(2)
                val status = cursor.getString(3)

                data.append(
                    "Payment ID: $id\n" +
                            "Member: $name\n" +
                            "Amount: ₹$amount\n" +
                            "Status: $status\n\n"
                )
            }
        }

        cursor.close()
        db.close()

        return data.toString()
    }

    fun getMemberCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM members",
            null
        )

        cursor.moveToFirst()
        val count = cursor.getInt(0)

        cursor.close()
        db.close()

        return count
    }

    fun getActiveMemberCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM members",
            null
        )

        cursor.moveToFirst()
        val count = cursor.getInt(0)

        cursor.close()
        db.close()

        return count
    }

    fun getAttendanceCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM attendance",
            null
        )

        cursor.moveToFirst()
        val count = cursor.getInt(0)

        cursor.close()
        db.close()

        return count
    }

    fun getPaymentCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM payments",
            null
        )

        cursor.moveToFirst()
        val count = cursor.getInt(0)

        cursor.close()
        db.close()

        return count
    }

    fun addTrainer(
        name: String,
        phone: String,
        email: String,
        specialization: String,
        experience: String
    ): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put("name", name)
        values.put("phone", phone)
        values.put("email", email)
        values.put("specialization", specialization)
        values.put("experience", experience)

        val result = db.insert("trainers", null, values)
        db.close()

        return result != -1L
    }

    fun getAllTrainers(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM trainers", null)

        val data = StringBuilder()

        if (cursor.count == 0) {
            data.append("No trainers added yet")
        } else {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val phone = cursor.getString(2)
                val email = cursor.getString(3)
                val specialization = cursor.getString(4)
                val experience = cursor.getString(5)

                data.append(
                    "Trainer ID: $id\n" +
                            "Name: $name\n" +
                            "Phone: $phone\n" +
                            "Email: $email\n" +
                            "Specialization: $specialization\n" +
                            "Experience: $experience Years\n\n"
                )
            }
        }

        cursor.close()
        db.close()

        return data.toString()
    }

    fun getTrainerCount(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM trainers", null)

        cursor.moveToFirst()
        val count = cursor.getInt(0)

        cursor.close()
        db.close()

        return count
    }
}