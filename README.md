# Student Management System

## Project Overview
The Student Management System is a Java-based desktop application designed to manage student information efficiently. It provides an admin login system and allows administrators to perform CRUD operations on student records using a MySQL database.

## Technologies Used
- Java
- Java Swing (GUI)
- JDBC
- MySQL
- IntelliJ IDEA

## Features
- Admin Login with database authentication
- Password hashing using PBKDF2WithHmacSHA256
- Add Student
- View All Students
- Search Student
- Update Student Details
- Delete Student
- Dashboard Statistics
- Student Performance Report
- Grade Calculation
- Pass/Fail Status
- Export Reports to CSV
- Logout

## Database
**Database Name:** `student_management`

**Tables:**
- `students` – Stores student information.
- `admins` – Stores admin usernames and password hashes.

## Project Structure
```text
StudentManagementSystem/
│
├── src/
│   ├── Student.java
│   ├── DBConnection.java
│   ├── StudentDAO.java
│   ├── StudentManager.java
│   ├── Main.java
│   ├── StudentGUI.java
│   ├── Login.java
│   ├── PasswordUtil.java
│   ├── AdminDAO.java
│   └── AdminSetup.java
│
├── screenshots/
│   ├── login.png
│   └── dashboard.png
│
└── README.md
```

## Application Screenshots

### Admin Login
[![Admin Login](login%20page.png)](login%20page.png)

### Student Management Dashboard
[![Student Management](student%20management.png)](student%20management.png)

## How to Run the Project

1. Install JDK 17 or later.
2. Install MySQL.
3. Create the `student_management` database.
4. Create the `students` and `admins` tables.
5. Configure your MySQL username and password in the database configuration file.
6. Add MySQL Connector/J to the project libraries.
7. Run `AdminSetup.java` once to create the initial admin account.
8. Run `Login.java` to launch the application.

## Learning Outcomes
- Understanding Java OOP concepts.
- Working with Java Swing GUI.
- Database connectivity using JDBC.
- Performing CRUD operations.
- Using PreparedStatement for database queries.
- Implementing password hashing.
- Generating CSV reports.

## Author
**Pillilla Vasavilatha**

Final-year B.Tech Computer Science and Engineering Student  
Mohan Babu University, Tirupati
