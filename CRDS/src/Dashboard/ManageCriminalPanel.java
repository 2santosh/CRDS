package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageCriminalPanel extends JPanel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public ManageCriminalPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel criminalListPanel = new JPanel();
        criminalListPanel.setLayout(new BoxLayout(criminalListPanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT * FROM criminal";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");

                JLabel criminalLabel = new JLabel("Name: " + name + " | Age: " + age
                        + " | Gender: " + gender + " | Address: " + address);

                criminalListPanel.add(criminalLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }

        JScrollPane scrollPane = new JScrollPane(criminalListPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}
