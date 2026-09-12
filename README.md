🏋️ Gym Management System
Project Overview

The Gym Management System is an Android application developed to manage the basic operations of a gym. The application provides a simple and user-friendly interface for managing gym members, membership plans, trainers, attendance, and payments.

The project is developed using Android Studio, with Kotlin used for application logic, XML used for the user interface, and SQLite used for local data storage.

Objectives
To develop a simple Android-based gym management application.
To maintain member information digitally.
To manage gym trainers and their details.
To record member attendance.
To maintain payment records.
To provide different membership plans.
To display important gym information through a dashboard.
To store data permanently using SQLite.
Features
User Login
Dashboard
Member Management
Membership Plans
Trainer Management
Attendance Management
Payment Management
SQLite Database
Data Persistence
Input Validation
Activity Navigation
Light and Dark Mode Support
Technologies Used
Android Studio – Development environment
Kotlin – Programming language
XML – User interface design
SQLite – Local database
SQLiteOpenHelper – Database management
Intent – Navigation between activities
Application Modules
1. Login Module

The Login module provides a basic login interface where the user enters an email and password.

The application checks whether the fields are empty. If both fields contain data, the user is redirected to the Dashboard.

2. Dashboard Module

The Dashboard acts as the main screen of the application.

It displays:

Total Members
Active Members
Total Trainers
Attendance Records

It also provides buttons to navigate to all major modules.

The dashboard counts are retrieved dynamically from the SQLite database.

3. Member Management

The Member Management module allows the user to add and view gym members.

Member information includes:

Member ID
Name
Phone Number
Email
Membership Plan

The information is stored in the members table.

4. Membership Plans

The application provides three membership plans:

Monthly – ₹999
Quarterly – ₹2499
Yearly – ₹7999

Users can select a plan from the Membership screen.

5. Trainer Management

The Trainer Management module allows the user to add and view trainer information.

Trainer details include:

Trainer ID
Name
Phone Number
Email
Specialization
Experience

The information is stored in the trainers table.

6. Attendance Management

The Attendance module allows the user to record member attendance.

The available statuses are:

Present
Absent

Attendance records are stored in the attendance table.

7. Payment Management

The Payment module allows the user to record member payments.

Payment information includes:

Payment ID
Member Name
Amount
Payment Status

Payment status can be:

Paid
Pending

The information is stored in the payments table.

Database

The application uses a local SQLite database named GymDatabase.

The database contains four main tables:

Members Table

Stores member information.

id
name
phone
email
membership
Trainers Table

Stores trainer information.

id
name
phone
email
specialization
experience
Attendance Table

Stores attendance records.

id
member_name
status
Payments Table

Stores payment records.

id
member_name
amount
status
DatabaseHelper

DatabaseHelper.kt is responsible for managing the SQLite database.

It extends the Android SQLiteOpenHelper class.

Its responsibilities include:

Creating database tables
Handling database upgrades
Inserting member records
Retrieving member records
Inserting trainer records
Retrieving trainer records
Saving attendance records
Retrieving attendance records
Saving payment records
Retrieving payment records
Calculating dashboard counts
Project Structure
Gym Management System
│
├── MainActivity.kt
├── DashboardActivity.kt
├── MembersActivity.kt
├── MembershipActivity.kt
├── TrainerActivity.kt
├── AttendanceActivity.kt
├── PaymentActivity.kt
├── DatabaseHelper.kt
│
└── res
    └── layout
        ├── activity_main.xml
        ├── activity_dashboard.xml
        ├── activity_members.xml
        ├── activity_membership.xml
        ├── activity_trainer.xml
        ├── activity_attendance.xml
        └── activity_payment.xml
Application Flow
Login
  ↓
Dashboard
  ↓
 ├── Members
 ├── Membership Plans
 ├── Trainers
 ├── Attendance
 └── Payments
Data Flow
User Input
    ↓
Activity
    ↓
DatabaseHelper
    ↓
SQLite Database
    ↓
Retrieve Data
    ↓
Display on Screen
Android Concepts Used

The project demonstrates several important Android concepts:

Activities
Activity Lifecycle
onCreate()
onResume()
XML Layouts
Views and Widgets
Explicit Intents
Toast Messages
Input Validation
SQLite Database
SQLiteOpenHelper
ContentValues
Cursor
SQL Queries
DayNight Theme
Data Persistence

The application uses SQLite for local data persistence. Records added by the user remain stored in the database and can be retrieved when the application is opened again.

Dark Mode

The application supports light and dark mode using a DayNight-compatible theme.

Theme attributes such as colorBackground, textColorPrimary, and textColorSecondary are used to make the interface adapt to the selected device theme.

Testing

The following features were tested successfully:

Login validation
Dashboard navigation
Member addition
Member data retrieval
Trainer addition
Trainer data retrieval
Attendance recording
Payment recording
Membership plan selection
Dashboard count updates
Data persistence
Light/Dark mode
Navigation between activities
Limitations

The current version is a basic academic implementation. It does not currently include:

Real online authentication
Member update and delete operations
Membership expiry tracking
Cloud synchronization
Online payment processing
Role-based authentication
Advanced reports
Future Scope

The application can be further improved by adding:

Firebase or cloud database integration
Secure user authentication
Member update and delete functionality
Membership expiry dates
Automatic renewal reminders
Search and filtering
Online payment integration
Admin and trainer roles
Member profile management
Reports and analytics
Push notifications
Conclusion

The Gym Management System provides a simple solution for managing important gym operations through an Android application. The project demonstrates the practical use of Kotlin, XML, Android Activities, Intents, SQLite, database management, input validation, and Android lifecycle methods.

The application provides a foundation that can be expanded with advanced features such as cloud storage, secure authentication, online payments, and detailed analytics.
