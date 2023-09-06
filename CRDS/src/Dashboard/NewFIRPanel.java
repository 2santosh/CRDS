package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewFIRPanel extends JPanel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public NewFIRPanel() {
        setLayout(new GridLayout(0, 2));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Complainant Name:");
        JTextField nameField = new JTextField();

        JLabel crimeDescriptionLabel = new JLabel("Crime Description:");
        JTextArea crimeDescriptionArea = new JTextArea();
        crimeDescriptionArea.setLineWrap(true);
        crimeDescriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(crimeDescriptionArea);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String crimeDescription = crimeDescriptionArea.getText();

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                String query = "INSERT INTO fir (complainant_name, crime_description) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, crimeDescription);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "FIR registered successfully!");
                    clearFields(nameField, crimeDescriptionArea);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register FIR.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            }
        });

        add(nameLabel);
        add(nameField);
        add(crimeDescriptionLabel);
        add(scrollPane);
        add(submitButton);
    }

    private void clearFields(JTextField nameField, JTextArea crimeDescriptionArea) {
        nameField.setText("");
        crimeDescriptionArea.setText("");
    }
}
