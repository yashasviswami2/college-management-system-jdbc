package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_management",
                    "root",
                    "Y@shasvi@150221"
            );

            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}