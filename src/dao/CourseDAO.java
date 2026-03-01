package dao;

import model.Course;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseDAO {

    // Add Course
    public void addCourse(Course course) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO course (course_name, department_id) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getDepartmentId());

            ps.executeUpdate();

            System.out.println("Course added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View All Courses With Department
    public void viewAllCourses() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT c.course_id, c.course_name, d.department_name
                    FROM course c
                    LEFT JOIN department d
                    ON c.department_id = d.department_id
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Course List -----");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("course_id") + " | " +
                                rs.getString("course_name") + " | " +
                                rs.getString("department_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}