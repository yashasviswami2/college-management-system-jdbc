package dao;

import model.Attendance;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttendanceDAO {

    // Mark Attendance
    public void markAttendance(Attendance attendance) {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    INSERT INTO attendance (student_id, course_id, attendance_date, status)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, attendance.getStudentId());
            ps.setInt(2, attendance.getCourseId());
            ps.setDate(3, java.sql.Date.valueOf(attendance.getAttendanceDate()));
            ps.setString(4, attendance.getStatus());

            ps.executeUpdate();

            System.out.println("Attendance marked successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Attendance
    public void viewAttendance() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT s.name, c.course_name, a.attendance_date, a.status
                    FROM attendance a
                    JOIN student s ON a.student_id = s.student_id
                    JOIN course c ON a.course_id = c.course_id
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Attendance Records -----");

            while (rs.next()) {

                System.out.println(
                        rs.getString("name") + " | " +
                                rs.getString("course_name") + " | " +
                                rs.getDate("attendance_date") + " | " +
                                rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}