package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtility {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public String retrieveUserInfo(String username) {
        String fullName = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT full_name FROM police WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fullName = resultSet.getString("full_name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any database-related errors here
        }

        return fullName;
    }

    // Add more methods for other database operations as needed
}
