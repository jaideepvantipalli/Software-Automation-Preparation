# Student Management System using Java OOP and File Handling

## 📌 Project Overview

The **Student Management System** is a menu-driven Java console application developed using Object-Oriented Programming (OOP) principles and Java File Handling.

The application manages student records permanently using a text file named `student.txt`. It supports CRUD (Create, Read, Update, Delete) operations through a structured, multi-class architecture.

This project demonstrates the practical implementation of Encapsulation, Abstraction, Classes, Objects, Constructors, and Method Communication while performing file operations using the `java.io` package.

**Key Highlight:** The application uses Java I/O streams for record management without using `ArrayList` or any other Java Collections Framework classes.

## 🎯 Objectives

- Implement Object-Oriented Programming principles in Java.
- Understand class responsibilities and object communication.
- Perform CRUD operations using Java File Handling.
- Separate business logic, file handling, and user interaction.
- Implement record searching using sequential file reading.
- Update and delete records using temporary-file handling.
- Understand object-to-string and string-to-object conversion.

## 🛠️ Technologies Used

| Technology | Description |
|---|---|
| Programming Language | Java |
| File Handling | `java.io` |
| Storage | Text File (`student.txt`) |
| IDE | IntelliJ IDEA |
| Programming Paradigm | Object-Oriented Programming |

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
| Grade | Automatically calculated grade | String |

## 🏗️ Project Architecture

The application is divided into four classes, each having a specific responsibility.

### 1. Student.java – Model Class

Represents an individual student and maintains student-related information.

**Responsibilities:**
- Stores student attributes using private variables.
- Initializes student objects using a parameterized constructor.
- Provides getter methods to access student information.
- Calculates the average marks and grade.
- Converts Student objects into file records using `toFileRecord()`.
- Converts file records into Student objects using `fromFileRecord()`.
- Displays student details using the `display()` method.

### 2. StudentFileHandler.java – File Handling Class

Responsible for interacting with the student data file.

**Responsibilities:**
- Creates and manages `student.txt`.
- Checks duplicate roll numbers.
- Adds student records using `FileWriter`.
- Reads student records using `BufferedReader`.
- Searches records by roll number and name.
- Updates and deletes records using `Temp.txt`.
- Replaces the original file with the updated temporary file.

### 3. StudentService.java – Business Logic Class

Acts as an intermediate layer between the main application and file handling class.

**Responsibilities:**
- Accepts student details from the user.
- Performs input validation.
- Creates Student objects.
- Invokes appropriate file-handling methods.
- Coordinates student-related operations.

### 4. StudentManagementSystem.java – Main Class

Acts as the application entry point.

**Responsibilities:**
- Displays the menu-driven interface.
- Accepts user choices.
- Uses a switch statement to execute the selected operation.
- Communicates with the StudentService class.
- Handles application termination.

## ⚙️ Application Features

The application provides the following operations:

### 1. Add Student Record
- Accepts student information.
- Checks for duplicate roll numbers.
- Calculates the average marks and grade.
- Appends the student record to `student.txt`.

### 2. Read All Student Records
- Reads all records from the file.
- Converts each record into a Student object.
- Displays complete student information.

### 3. Search Student by Roll Number
- Accepts a roll number.
- Performs sequential searching.
- Displays the matching student record.

### 4. Search Student by Name
- Accepts the student's name.
- Performs case-insensitive searching.
- Displays matching student records.

### 5. Delete Student Record
- Searches for a student using the roll number.
- Copies all records except the selected student into `Temp.txt`.
- Replaces the original student file with the temporary file.

### 6. Update Student Record
- Searches for an existing student using the roll number.
- Accepts updated student information.
- Writes the updated record into the temporary file.
- Preserves all other student records.
- Replaces the original file with the updated file.

## 📁 Project Structure

```text
StudentManagementSystem/
│
├── src/
│   ├── Student.java
│   ├── StudentFileHandler.java
│   ├── StudentService.java
│   └── StudentManagementSystem.java
│
├── student.txt
├── Temp.txt
│
└── README.md
```

**Note:** `student.txt` is created automatically when the first student record is added. `Temp.txt` is created during update and delete operations.

## 🔄 Application Workflow

```text
             Main Class
                 |
                 v
         StudentManagementSystem
                 |
                 v
           StudentService
                 |
                 v
          StudentFileHandler
                 |
          +------+------+
          |             |
          v             v
      student.txt     Temp.txt
          |
          v
       Student
       Objects
```

The application follows a layered approach:

1. The main class receives the user's choice.
2. The service class processes the request.
3. The Student class represents the student data.
4. The file handler performs the required file operation.
5. Student records are stored permanently in `student.txt`.

## 📄 File Storage Format

Student records are stored as individual lines using the pipe (`|`) delimiter.

Example:

```text
101|20|Jaideep|85.0|90.0|78.0|88.0|92.0|A+
102|21|Rahul|75.0|80.0|70.0|85.0|90.0|A+
103|20|Kiran|60.0|65.0|72.0|68.0|75.0|B
```

Each line represents one student record.

## 📚 OOP Concepts Implemented

| OOP Concept | Implementation | Description |
|---|---|---|
| Encapsulation | `Student.java` | Uses private attributes and public getter methods. |
| Abstraction | `StudentFileHandler.java` | Hides internal file-handling implementation behind methods. |
| Classes and Objects | All four classes | Organizes the application into reusable components. |
| Constructors | `Student.java` | Initializes student objects. |
| Method Communication | Service and FileHandler | Allows objects to communicate through method calls. |
| Separation of Concerns | Project architecture | Assigns different responsibilities to different classes. |

**Note:** Inheritance and Polymorphism are not required in this implementation. The project focuses on encapsulation, abstraction, and object-oriented application structure.

## 📚 Java Concepts and Classes Used

| Class / Concept | Purpose |
|---|---|
| `File` | Represents student and temporary files |
| `FileReader` | Reads character data |
| `FileWriter` | Writes student records |
| `BufferedReader` | Reads records line by line |
| `BufferedWriter` | Writes records efficiently |
| `InputStreamReader` | Reads keyboard input |
| `String.split()` | Separates record attributes |
| Constructors | Initializes Student objects |
| Getters | Provides controlled access to student data |
| `toFileRecord()` | Converts objects into text records |
| `fromFileRecord()` | Converts text records into objects |
| Exception Handling | Handles file-related errors |
| Try-with-resources | Automatically closes file resources |

## ▶️ How to Run the Project

### Prerequisites
- Java Development Kit (JDK 17 or above).
- IntelliJ IDEA or any Java IDE.

### Steps

**1. Clone the repository**

```bash
git clone <your-repository-url>
```

**2. Open the project in IntelliJ IDEA.**

**3. Ensure that all four Java files are available in the source directory.**

**4. Compile the Java files**

```bash
javac -d out src/*.java
```

**5. Run the application**

```bash
java -cp out StudentManagementSystem
```

**6. Select the required operation from the menu.**

## 📊 Grade Calculation

The application calculates the average marks of five subjects and assigns a grade.

| Average Marks | Grade |
|---------------|-------|
| 91 – 100      | S     |
| 81 – 90       | A     |
| 71 – 80       | B     |
| 61 – 70       | C     |
| 51 – 60       | D     |
| Below 50      | E     |

## 🧪 Test Scenarios

| Test Scenario | Expected Result |
|---|---|
| Add a new student | Student record saved |
| Add duplicate roll number | Duplicate record rejected |
| Display all students | All records displayed |
| Search by existing roll number | Matching record displayed |
| Search by invalid roll number | Student not found |
| Search by student name | Matching records displayed |
| Delete an existing student | Record deleted |
| Update an existing student | Record updated |
| Restart the application | Previously stored records retained |

## 🚀 Learning Outcomes

Through this project, I gained practical experience in:

- Designing Java applications using OOP principles.
- Implementing Encapsulation and Abstraction.
- Creating and managing multiple classes and objects.
- Understanding constructors and getter methods.
- Separating business logic from file-handling logic.
- Performing CRUD operations using Java I/O streams.
- Converting objects into text records and vice versa.
- Managing file updates and deletions using temporary files.
- Building menu-driven console applications.

## 🔮 Future Enhancements

- Implement inheritance and runtime polymorphism.
- Introduce Java Object Serialization.
- Integrate MySQL using JDBC.
- Implement student ranking and result analysis.
- Develop a graphical user interface using Java Swing or JavaFX.

---

**Developed as an Object-Oriented Java File Handling Practice Project.**