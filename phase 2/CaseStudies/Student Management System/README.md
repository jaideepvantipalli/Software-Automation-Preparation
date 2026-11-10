# Student Management System using Java File Handling

## 📌 Project Overview

The **Student Management System** is a menu-driven Java application developed to manage student records using file handling concepts from the `java.io` package.

The application stores student information permanently in a text file named `student.txt` and supports essential CRUD (Create, Read, Update, Delete) operations.

This project demonstrates how Java File I/O can be used to perform record management without using databases or the Collections Framework.

## 🎯 Objectives

- Understand Java File Handling using the `java.io` package.
- Perform CRUD operations on a text file.
- Read and write student records using character streams.
- Search student records based on roll number and name.
- Update and delete specific records using temporary-file handling.
- Develop a menu-driven console application.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Package:** `java.io`
- **Storage:** Text File (`student.txt`)
- **IDE:** IntelliJ IDEA

## 📋 Student Record Structure

Each student record contains the following attributes:

| Attribute | Description | Data Type |
|---|---|---|
| Roll Number | Unique student identification number | int |
| Age | Student age | int |
| Name | Student name | String |
| M1 | Subject 1 marks | double |
| M2 | Subject 2 marks | double |
| M3 | Subject 3 marks | double |
| M4 | Subject 4 marks | double |
| M5 | Subject 5 marks | double |
| Grade | Calculated grade | String |

## ⚙️ Features

The application provides the following menu-driven operations:

### 1. Add Student Record
- Accepts student details from the user.
- Checks whether the roll number already exists.
- Calculates the student's average marks and grade.
- Appends the new record to `student.txt`.

### 2. Display All Student Records
- Reads all records from the file.
- Displays complete student information.
- Handles empty or non-existing files.

### 3. Search Student by Roll Number
- Accepts a roll number from the user.
- Searches the file sequentially.
- Displays the matching student record.

### 4. Search Student by Name
- Accepts a student name.
- Performs a case-insensitive name search.
- Displays matching student records.

### 5. Delete Student Record
- Searches for the student using the roll number.
- Uses temporary-file handling to preserve all records except the selected record.
- Replaces the original file with the updated records.

### 6. Update Student Record
- Searches for an existing student using the roll number.
- Accepts updated student details.
- Writes the modified record while preserving other student records.

## 📁 Project Structure

```text
StudentManagementSystem/
│
├── src/
│   └── StudentManagementSystem.java
│
├── student.txt
│
└── README.md
```

*Note: `student.txt` is created automatically when the application adds its first student record. Its actual location depends on the application's working directory.*

## 📄 File Storage Format

Student records are stored in `student.txt` using the pipe (`|`) delimiter.

Example:

```text
101|20|Jaideep|85.0|90.0|78.0|88.0|92.0|A+
102|21|Rahul|75.0|80.0|70.0|85.0|90.0|A+
103|20|Kiran|60.0|65.0|72.0|68.0|75.0|B
```

Each line represents one student record.

## Java Concepts Implemented

| Java Class / Concept | Purpose |
|---|---|
| `File` | Represents the student data file |
| `FileReader` | Reads character data from the file |
| `FileWriter` | Writes student records into the file |
| `BufferedReader` | Reads records efficiently, line by line |
| `BufferedWriter` | Writes records efficiently |
| `InputStreamReader` | Reads keyboard input as character data |
| `String.split()` | Separates individual record attributes |
| Try-with-resources | Automatically closes file resources |
| Exception Handling | Handles file-related exceptions |
| Temporary File Handling | Supports update and delete operations |

**Note:** The implementation uses file-based record processing rather than `ArrayList` or other Java Collection Framework classes.

## ▶️ How to Run the Project

1. Clone this repository:

```bash
git clone <your-repository-url>
```

2. Open the project in IntelliJ IDEA or any Java IDE.

3. Ensure that JDK is installed and configured.

4. Open `StudentManagementSystem.java`.

5. Compile and execute the program:

```bash
javac StudentManagementSystem.java
java StudentManagementSystem
```

6. Select the required operation from the menu.

## Grade Calculation

The application calculates the average marks of five subjects and assigns a grade.

| Average Marks | Grade |
|---|---|
| 91 – 100 | S |
| 81 – 90 | A |
| 71 – 80 | B |
| 61 – 70 | C |
| 51 – 60 | D |
| Below 50 | E |

## Learning Outcomes

Through this project, I gained practical experience in:

- Understanding Java character streams.
- Working with `FileReader`, `FileWriter`, `BufferedReader`, and `BufferedWriter`.
- Performing CRUD operations using text files.
- Implementing sequential searching.
- Managing file records through temporary-file operations.
- Handling exceptions during file operations.
- Developing menu-driven Java applications.
---

**Developed as a Java File Handling Practice Project.**
