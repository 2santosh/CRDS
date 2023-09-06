package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MangeChargeSheet extends JPanel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public MangeChargeSheet() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Add New Charge Sheet");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel caseNumberLabel = new JLabel("Case Number:");
        JTextField caseNumberField = new JTextField();

        JLabel suspectLabel = new JLabel("Suspect:");
        JTextField suspectField = new JTextField();

        JLabel crimeLabel = new JLabel("Crime:");
        JTextField crimeField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JButton addChargeSheetButton = new JButton("Add Charge Sheet");
        addChargeSheetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caseNumber = caseNumberField.getText();
                String suspect = suspectField.getText();
                String crime = crimeField.getText();
                String description = descriptionArea.getText();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String chargeDate = dateFormat.format(currentDate);

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                    String query = "INSERT INTO charge_sheets (case_number, suspect, crime, description, charge_date) " +
                                   "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, caseNumber);
                    preparedStatement.setString(2, suspect);
                    preparedStatement.setString(3, crime);
                    preparedStatement.setString(4, description);
                    preparedStatement.setString(5, chargeDate);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(MangeChargeSheet.this, "Charge sheet added successfully!");
                        // Clear input fields after successful addition
                        caseNumberField.setText("");
                        suspectField.setText("");
                        crimeField.setText("");
                        descriptionArea.setText("");
                    } else {
                        JOptionPane.showMessageDialog(MangeChargeSheet.this, "Failed to add charge sheet.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(MangeChargeSheet.this, "Error connecting to the database.");
                }
            }
        });

        formPanel.add(caseNumberLabel);
        formPanel.add(caseNumberField);
        formPanel.add(suspectLabel);
        formPanel.add(suspectField);
        formPanel.add(crimeLabel);
        formPanel.add(crimeField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);
        formPanel.add(addChargeSheetButton);

        add(formPanel, BorderLayout.CENTER);
    }
}