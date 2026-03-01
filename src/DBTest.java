import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college_management",
                    "root",
                    "Y@shasvi@150221"
            );

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}