# HelpDesk JDBC Platform

A mini HelpDesk support ticketing system built using Java and JDBC with MySQL.

---

## 📦 Features

- Add new customers
- Create and assign support tickets
- Record customer feedback
- SLA enforcement
- Uses JDBC for database communication

---

## ⚙️ Tech Stack

- Java (JDK 22)
- JDBC
- MySQL
- VS Code

---

## 📁 Project Structure

HelpDeskJDBC/
├── Main.java
├── model/
├── dao/
├── util/
├── lib/ # MySQL JDBC driver
├── HelpDeskApp.jar # Final .jar file
├── schema.sql # SQL to create database
└── README.md

pgsql
Copy code

---

## 🛠️ MySQL Setup

This project requires a MySQL database named `helpdesk` with several tables.

To make setup easy, use the included `schema.sql` file.

### 📥 How to Import `schema.sql`

#### Option 1: Using MySQL Workbench

1. Open MySQL Workbench  
2. Open a new SQL tab  
3. Copy+paste or drag the `schema.sql` file into the tab  
4. Click **Execute All**

#### Option 2: Using MySQL Terminal

```bash
mysql -u root -p
Then inside MySQL:

sql
Copy code
CREATE DATABASE helpdesk;
USE helpdesk;
SOURCE schema.sql;
The file will automatically:

Create all tables

Insert sample data (agent, category, customer)

🧪 How to Run the App
Step 1: Compile (if needed)
bash
Copy code
javac -cp ".;lib/mysql-connector-j-9.3.0.jar" Main.java model/*.java dao/*.java util/*.java
Step 2: Run
bash
Copy code
java -cp ".;lib/mysql-connector-j-9.3.0.jar" Main
Step 3: Run from .jar
bash
Copy code
java -jar HelpDeskApp.jar
Make sure mysql-connector-j-9.3.0.jar is inside the lib/ folder and included in your classpath.

👤 Author
Name: Parth Dagia
GitHub: parth-dagia

📄 License
This project is for academic purposes only.