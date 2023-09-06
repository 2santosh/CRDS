package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.mysql.cj.jdbc.Driver;

public class DatabaseConnector {
    public static void main(String[] args) {
        testDatabaseConnection();
    }

    public static void testDatabaseConnection() {
        String url = "jdbc:mysql://localhost:3306/cdrs"; // Adjust the URL
        String username = "root"; // Adjust the username
        String password = "S@nt0sh143"; // Adjust the password

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connection established successfully!");

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Unable to connect to the database.");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            
            if (e.getMessage().toLowerCase().contains("access denied")) {
                System.out.println("Error: Access denied. Please check your username and password.");
            } else if (e.getMessage().toLowerCase().contains("unknown database")) {
                System.out.println("Error: Database not found. Please check your database name.");
            } else {
                System.out.println("Error Message: " + e.getMessage());
            }
            
            e.printStackTrace();
        }
    }
}
