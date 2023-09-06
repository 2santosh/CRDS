package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagePolicePanel extends JPanel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public ManagePolicePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fetch police officers' information from the database
        JPanel policeListPanel = new JPanel();
        policeListPanel.setLayout(new BoxLayout(policeListPanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT * FROM police";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String badgeNumber = resultSet.getString("badge_number");
                String rank = resultSet.getString("rank");
                String policeStation = resultSet.getString("police_station");

                JLabel policeLabel = new JLabel("Name: " + name + " | Badge Number: " + badgeNumber
                        + " | Rank: " + rank + " | Police Station: " + policeStation);

                policeListPanel.add(policeLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }

        JScrollPane scrollPane = new JScrollPane(policeListPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}
