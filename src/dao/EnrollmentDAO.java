package dao;

import model.Enrollment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnrollmentDAO {

    // Enroll student in course
    public void enrollStudent(Enrollment enrollment) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO enrollment (student_id, course_id, semester) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setInt(3, enrollment.getSemester());

            ps.executeUpdate();

            System.out.println("Student enrolled successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Enrollment Details
    public void viewEnrollments() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT s.name, s.erp_number, c.course_name, e.semester
                    FROM enrollment e
                    JOIN student s ON e.student_id = s.student_id
                    JOIN course c ON e.course_id = c.course_id
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Enrollment Records -----");

            while (rs.next()) {

                System.out.println(
                        rs.getString("name") + " | " +
                                rs.getString("erp_number") + " | " +
                                rs.getString("course_name") + " | Semester: " +
                                rs.getInt("semester")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}