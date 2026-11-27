# Student Management System (Java + MySQL + JDBC)

A menu-driven console application to create, view, search, update, and delete student records stored in MySQL.

## Features

- Add a student
- View all students
- Search by student ID
- Update student details
- Delete a student
- JDBC database connection using `PreparedStatement`

## Requirements

- JDK 17 or newer
- MySQL Server
- Maven
- IntelliJ IDEA, Eclipse, or another Java IDE (optional)

## 1. Set up the database

Open MySQL Workbench or a MySQL terminal and run the SQL in `database.sql`.

This creates the `student_db` database and `students` table.

## 2. Configure database credentials

Open `src/main/java/StudentManagementSystem.java` and update these constants for your MySQL setup:

```java
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

Replace `your_password` with your MySQL password. Do not publish real passwords in a public repository.

## 3. Run with Maven

From the project root directory, run:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=StudentManagementSystem
```

If Maven's exec plugin is not configured automatically, add this plugin inside the `<build><plugins>` section of `pom.xml`:

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>exec-maven-plugin</artifactId>
  <version>3.5.0</version>
</plugin>
```

Then run the commands again.

Alternatively, open the folder as a Maven project in IntelliJ IDEA, wait for dependencies to download, and run the `main()` method in `StudentManagementSystem.java`.

## Menu

```text
1. Add Student
2. View Students
3. Search Student
4. Update Student
5. Delete Student
6. Exit
```

## Troubleshooting

- **Communications link failure:** Make sure MySQL Server is running and port `3306` is correct.
- **Access denied:** Check the MySQL username and password in the Java file.
- **Unknown database:** Run `database.sql` first.
- **No suitable driver:** Make sure Maven downloaded the MySQL Connector/J dependency and the project is opened as a Maven project.
- **Duplicate email:** Each email must be unique because the table has a `UNIQUE` constraint.

## Project structure

```text
StudentManagementSystem/
├── pom.xml
├── README.md
├── database.sql
└── src/
    └── main/
        └── java/
            └── StudentManagementSystem.java
```
