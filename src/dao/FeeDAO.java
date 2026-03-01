package dao;

import model.Fee;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeeDAO {

    // Add Fee Record
    public void addFee(Fee fee) {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    INSERT INTO fee (student_id, total_amount, paid_amount, due_amount, payment_date)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, fee.getStudentId());
            ps.setDouble(2, fee.getTotalAmount());
            ps.setDouble(3, fee.getPaidAmount());
            ps.setDouble(4, fee.getDueAmount());
            ps.setDate(5, java.sql.Date.valueOf(fee.getPaymentDate()));

            ps.executeUpdate();

            System.out.println("Fee record added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Fee Records
    public void viewFees() {

        try {
            Connection con = DBConnection.getConnection();

            String query = """
                    SELECT s.name, s.erp_number, f.total_amount, f.paid_amount, f.due_amount, f.payment_date
                    FROM fee f
                    JOIN student s ON f.student_id = s.student_id
                    """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- Fee Records -----");

            while (rs.next()) {

                System.out.println(
                        rs.getString("name") + " | " +
                                rs.getString("erp_number") + " | Total: " +
                                rs.getDouble("total_amount") + " | Paid: " +
                                rs.getDouble("paid_amount") + " | Due: " +
                                rs.getDouble("due_amount") + " | Date: " +
                                rs.getDate("payment_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}