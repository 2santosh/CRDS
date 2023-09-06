package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    public ReportPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Generate Report");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JLabel fromDateLabel = new JLabel("From Date (YYYY-MM-DD):");
        JTextField fromDateField = new JTextField();

        JLabel toDateLabel = new JLabel("To Date (YYYY-MM-DD):");
        JTextField toDateField = new JTextField();

        JButton generateButton = new JButton("Generate Report");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromDate = fromDateField.getText();
                String toDate = toDateField.getText();

                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                    String query = "SELECT * FROM your_table_name WHERE date BETWEEN ? AND ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, fromDate);
                    preparedStatement.setString(2, toDate);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Process the result set and display the report
                    // For example, you can display the results in a JTable or JTextArea
                    // Here's a simplified example:
                    while (resultSet.next()) {
                        String resultRow = resultSet.getString("column_name1") + " - " +
                                           resultSet.getString("column_name2") + " - " +
                                           resultSet.getDate("date");
                        System.out.println(resultRow);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ReportPanel.this, "Error connecting to the database.");
                }
            }
        });

        formPanel.add(fromDateLabel);
        formPanel.add(fromDateField);
        formPanel.add(toDateLabel);
        formPanel.add(toDateField);
        formPanel.add(generateButton);

        add(formPanel, BorderLayout.CENTER);
    }
}
