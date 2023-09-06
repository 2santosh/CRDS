package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class NewChargeSheetPanel extends JPanel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public NewChargeSheetPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("New Charge Sheet");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel caseNumberLabel = new JLabel("Case Number:");
        JTextField caseNumberField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caseNumber = caseNumberField.getText();
                String description = descriptionArea.getText();
                Date currentDate = new Date();

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                    String query = "INSERT INTO charge_sheet (case_number, description, created_date) " +
                            "VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, caseNumber);
                    preparedStatement.setString(2, description);
                    preparedStatement.setDate(3, new java.sql.Date(currentDate.getTime()));

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(NewChargeSheetPanel.this, "Charge Sheet created successfully!");
                    } else {
                        JOptionPane.showMessageDialog(NewChargeSheetPanel.this, "Failed to create Charge Sheet.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NewChargeSheetPanel.this, "Error connecting to the database.");
                }
            }
        });

        formPanel.add(caseNumberLabel);
        formPanel.add(caseNumberField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionScrollPane);
        formPanel.add(createButton);

        add(formPanel, BorderLayout.CENTER);
    }
}
