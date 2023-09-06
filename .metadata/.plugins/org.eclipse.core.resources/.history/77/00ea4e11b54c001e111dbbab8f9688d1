package Add;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPolicePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public AddPolicePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel badgeNumberLabel = new JLabel("Badge Number:");
        JTextField badgeNumberField = new JTextField();

        JLabel rankLabel = new JLabel("Rank:");
        JTextField rankField = new JTextField();

        JLabel policeStationLabel = new JLabel("Police Station:");
        JTextField policeStationField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(badgeNumberLabel);
        formPanel.add(badgeNumberField);
        formPanel.add(rankLabel);
        formPanel.add(rankField);
        formPanel.add(policeStationLabel);
        formPanel.add(policeStationField);

        JButton addButton = new JButton("Add Police");
        addButton.addActionListener(e -> addPolice(nameField.getText(), badgeNumberField.getText(),
                rankField.getText(), policeStationField.getText()));

        add(formPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    private void addPolice(String name, String badgeNumber, String rank, String policeStation) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "INSERT INTO police (name, badge_number, rank, police_station) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, badgeNumber);
            preparedStatement.setString(3, rank);
            preparedStatement.setString(4, policeStation);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Police officer added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add police officer.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }
    }
}
