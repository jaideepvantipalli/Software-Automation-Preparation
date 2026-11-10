# Student Management System (Java File Handling)

A menu-driven console application built using **Core Java** and the
**`java.io` package** to manage student records stored in a text file
(`student.txt`).

The application supports adding, viewing, searching, updating, and
deleting student records without using a database.

## Features

-   **Add student:** Store a new student record in `student.txt`.
-   **View all students:** Read and display every saved student record.
-   **Search by roll number:** Find a student using their unique roll
    number.
-   **Search by name:** Find student records by name (case-insensitive
    exact match).
-   **Delete student:** Remove a record using the student's roll number.
-   **Update student:** Modify a student's age, name, and subject marks
    using the roll number.
-   **Automatic grade calculation:** Calculate the average of five
    subjects and assign a grade.

## Student Record Structure

Each student record contains:

  Field         Description                         Type
  ------------- ----------------------------------- ----------
  Roll Number   Unique student identifier           `int`
  Age           Student age                         `int`
  Name          Student name                        `String`
  M1--M5        Marks in five subjects (0--100)     `double`
  Grade         Grade calculated from the average   `String`

### Grade Criteria

The application uses the following sample grading scale. Change the
thresholds in `calculateGrade()` if your institution follows a different
grading system.

      Average Grade
  ----------- -------
      90--100 O
    80--89.99 A+
    70--79.99 A
    60--69.99 B
    50--59.99 C
     Below 50 F

## Technologies and Concepts

-   Java
-   `java.io.File`
-   `java.io.FileReader`
-   `java.io.FileWriter`
-   `java.io.BufferedReader`
-   `java.io.BufferedWriter`
-   `java.io.InputStreamReader`
-   `ArrayList` for temporary in-memory record handling
-   Exception handling and try-with-resources
-   Menu-driven programming and CRUD operations

## Project Structure

``` text
StudentManagementSystem/
├── src/
│   └── StudentManagementSystem.java
└── student.txt
```

`student.txt` is created automatically in the application's working
directory when the first student record is added. If your IDE uses a
different working directory, the file may appear there instead.

## How to Run

### Prerequisites

-   JDK installed (Java 8 or later)
-   IntelliJ IDEA, Eclipse, VS Code, or a terminal

### Run from the terminal

1.  Clone this repository:

    ``` bash
    git clone <YOUR_REPOSITORY_URL>
    ```

2.  Move into the project directory:

    ``` bash
    cd StudentManagementSystem
    ```

3.  Compile the source file:

    ``` bash
    javac StudentManagementSystem.java
    ```

4.  Run the application:

    ``` bash
    java StudentManagementSystem
    ```

If your source file is inside a package or `src` directory, compile and
run it using the corresponding package/class path.

## Application Menu

``` text
================================
   STUDENT MANAGEMENT SYSTEM
================================
1. Add Student Record
2. Read All Student Records
3. Search Student By Roll Number
4. Search Student By Name
5. Delete Student By Roll Number
6. Update Student By Roll Number
0. Exit
================================
Enter Your Choice:
```

## File Storage Format

Records are stored as plain text, with one student per line. The pipe
(`|`) character separates fields.

Example:

``` text
101|20|Jaideep|85.0|90.0|78.0|88.0|92.0|A+
102|21|Rahul|75.0|80.0|70.0|85.0|90.0|A
```

The fields are stored in this order:

``` text
rollNumber|age|name|M1|M2|M3|M4|M5|grade
```

## How CRUD Works

  -----------------------------------------------------------------------
  Operation                           Implementation
  ----------------------------------- -----------------------------------
  Create                              `FileWriter` in append mode adds a
                                      new line without replacing existing
                                      records.

  Read                                `BufferedReader.readLine()` reads
                                      records one line at a time.

  Search                              Each line is split using
                                      `String.split("\\|")`, then the
                                      required field is compared.

  Update                              Records are loaded into an
                                      `ArrayList`, the matching entry is
                                      replaced, and the file is
                                      rewritten.

  Delete                              Records are loaded into an
                                      `ArrayList`, the matching entry is
                                      removed, and the remaining records
                                      are written back.
  -----------------------------------------------------------------------

### Important Implementation Details

-   `new FileWriter(FILE_NAME, true)` opens the file in append mode.
-   `new FileWriter(FILE_NAME, false)` overwrites the existing file
    contents.
-   `BufferedReader` provides convenient line-by-line reading.
-   `BufferedWriter.newLine()` writes a platform-appropriate line
    separator.
-   Try-with-resources closes file streams automatically.
-   A roll number is checked before adding a record to prevent
    duplicates.

## Limitations

-   This is an educational file-handling project, not a production-grade
    record management system.
-   Update and delete operations rewrite the complete file.
-   Records are temporarily loaded into memory for update and delete
    operations.
-   The pipe character (`|`) is reserved as a field delimiter and is not
    allowed in student names.
-   The application does not provide concurrent-user or transaction
    support.
-   The sample grade thresholds are illustrative and should be adjusted
    to institutional rules.

## Possible Enhancements

-   Add sorting by roll number, name, or average marks.
-   Add partial-name search.
-   Add validation for age and roll number.
-   Add a confirmation step before deletion.
-   Use a temporary file and safe replacement strategy for more reliable
    updates.
-   Separate the `Student` model, file operations, and menu logic into
    different classes.
-   Add unit tests for CRUD operations and grade calculation.

## Learning Outcomes

By completing this project, you can practise:

-   Reading and writing text files in Java.
-   Using byte/character-oriented file APIs and buffered character
    streams.
-   Implementing CRUD operations without a database.
-   Handling exceptions and closing resources safely.
-   Searching and modifying collections of records.

## Author

**Jaideep**

------------------------------------------------------------------------

If you find this project useful, feel free to fork the repository and
extend it.
