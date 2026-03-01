package dao;

import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    // ================= ADD STUDENT =================
    public void addStudent(Student student) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO student (name, email, phone, department_id) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setInt(4, student.getDepartmentId());

            ps.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW ALL STUDENTS WITH DEPARTMENT =================
    public void viewAllStudentsWithDepartment() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT s.student_id, s.name, s.email, s.phone, d.department_name
                    FROM student s
                    LEFT JOIN department d
                    ON s.department_id = d.department_id
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Student List (With Department) -----");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("phone") + " | " +
                                rs.getString("department_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE STUDENT =================
    public void updateStudent(int id, String email, String phone) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE student SET email = ?, phone = ? WHERE student_id = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, phone);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE STUDENT =================
    public void deleteStudent(int id) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM student WHERE student_id = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SEARCH STUDENT BY NAME =================
    public void searchStudentByName(String nameKeyword) {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT s.student_id, s.name, s.email, s.phone, d.department_name
                    FROM student s
                    LEFT JOIN department d
                    ON s.department_id = d.department_id
                    WHERE s.name LIKE ?
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + nameKeyword + "%");

            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Search Results -----");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("student_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("phone") + " | " +
                                rs.getString("department_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= STUDENT COUNT PER DEPARTMENT =================
    public void getStudentCountPerDepartment() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT d.department_name, COUNT(s.student_id) AS total_students
                    FROM department d
                    LEFT JOIN student s
                    ON d.department_id = s.department_id
                    GROUP BY d.department_name
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Student Count Per Department -----");

            while (rs.next()) {

                System.out.println(
                        rs.getString("department_name") + " | " +
                                rs.getInt("total_students")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}