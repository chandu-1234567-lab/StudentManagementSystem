import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    // Replace with your local MySQL password. Do not commit real passwords.
    private static final String PASSWORD = "your_password";
    private static final Scanner sc = new Scanner(System.in);

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addStudent() {
        String sql = "INSERT INTO students (name, age, course, email) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Enter course: ");
            String course = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setString(4, email);
            if (ps.executeUpdate() > 0) System.out.println("Student added successfully!");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewStudents() {
        String sql = "SELECT * FROM students ORDER BY id";
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- Student Records ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("ID: %d | Name: %s | Age: %d | Course: %s | Email: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
                        rs.getString("course"), rs.getString("email"));
            }
            if (!found) System.out.println("No student records found.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void searchStudent() {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(sc.nextLine());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("ID: %d%nName: %s%nAge: %d%nCourse: %s%nEmail: %s%n",
                            rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
                            rs.getString("course"), rs.getString("email"));
                } else System.out.println("Student not found!");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateStudent() {
        String sql = "UPDATE students SET name = ?, age = ?, course = ?, email = ? WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter student ID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Enter new course: ");
            String course = sc.nextLine();
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setString(4, email);
            ps.setInt(5, id);
            if (ps.executeUpdate() > 0) System.out.println("Student updated successfully!");
            else System.out.println("Student ID not found!");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteStudent() {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.print("Enter student ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) System.out.println("Student deleted successfully!");
            else System.out.println("Student ID not found!");
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student\n2. View Students\n3. Search Student\n4. Update Student\n5. Delete Student\n6. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewStudents();
                case "3" -> searchStudent();
                case "4" -> updateStudent();
                case "5" -> deleteStudent();
                case "6" -> { System.out.println("Application closed."); sc.close(); return; }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
