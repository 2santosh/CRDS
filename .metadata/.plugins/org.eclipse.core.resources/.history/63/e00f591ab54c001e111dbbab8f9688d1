package Add;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPoliceStationPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public AddPoliceStationPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components for adding police station
        JLabel titleLabel = new JLabel("Add Police Station");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Police Station Name:");
        JTextField nameField = new JTextField(20);

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField(20);

        JButton addButton = new JButton("Add Police Station");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stationName = nameField.getText();
                String stationLocation = locationField.getText();

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                    String query = "INSERT INTO police_stations (name, location) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, stationName);
                    preparedStatement.setString(2, stationLocation);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(AddPoliceStationPanel.this, "Police station added successfully!");
                        // Clear input fields
                        nameField.setText("");
                        locationField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(AddPoliceStationPanel.this, "Failed to add police station.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddPoliceStationPanel.this, "Error connecting to the database.");
                }
            }
        });

        // Add components to the panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(locationLabel);
        formPanel.add(locationField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(addButton);

        add(formPanel, BorderLayout.CENTER);
    }
}
