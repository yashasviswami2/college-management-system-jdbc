package dao;

import model.Department;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DepartmentDAO {

    // Add Department
    public void addDepartment(Department department) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO department (department_name) VALUES (?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, department.getDepartmentName());

            ps.executeUpdate();

            System.out.println("Department added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View All Departments
    public void viewAllDepartments() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM department";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Department List -----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("department_id") + " | " +
                                rs.getString("department_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get Department By ID
    public Department getDepartmentById(int id) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM department WHERE department_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}