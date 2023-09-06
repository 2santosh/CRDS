package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class viewFirs {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public viewFirs() {
        initialize();
        populateFirDataFromDatabase(); // Fetch FIR data from the database
    }

    private void initialize() {
        // ... (same as the previous code)

        // Remove the initial dummy data
        model.setRowCount(0);

        // ... (same as the previous code)
    }

    private void populateFirDataFromDatabase() {
        String url = "jdbc:sqlite:/path/to/your/database.db"; // Replace with your database URL
        String query = "SELECT * FROM fir_table"; // Replace with your table name

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int firNumber = resultSet.getInt("fir_number");
                String dateFiled = resultSet.getString("date_filed");
                String complainantName = resultSet.getString("complainant_name");
                String status = resultSet.getString("status");

                // Add the fetched data to the table
                model.addRow(new Object[]{firNumber, dateFiled, complainantName, status, "Edit"});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ... (rest of the code remains the same)

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
                    viewFirs window = new viewFirs();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

    // ... (ButtonRenderer and ButtonEditor classes remain the same)
}
