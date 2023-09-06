package Police;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PoRegister {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/crds";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "S@nt0sh143";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowUI();
        });
    }

    private static void createAndShowUI() {
        JFrame frame = new JFrame("Personal Details Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField firstNameField = new JTextField(20);
        JTextField middleNameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        addEnterKeyListener(firstNameField, middleNameField);

        panel.add(new JLabel("Middle Name (Optional):"));
        panel.add(middleNameField);
        addEnterKeyListener(middleNameField, lastNameField);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);

        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Date of Birth:"));
        String[] years = {"Year", "1990", "1991", "1992"};
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{"Month", "01", "02", "03"});
        JComboBox<String> dayComboBox = new JComboBox<>(new String[]{"Day", "01", "02", "03"});
        datePanel.add(yearComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(dayComboBox);
        panel.add(datePanel);

        // ... Other components ...
        panel.add(new JLabel("Gender:"));
        JRadioButton maleRadioButton = new JRadioButton("Male");
        JRadioButton femaleRadioButton = new JRadioButton("Female");
        JRadioButton otherRadioButton = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.add(otherRadioButton);
        panel.add(genderPanel);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText();
                String lastName = lastNameField.getText();
                String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : "Other");                String year = (String) yearComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                String day = (String) dayComboBox.getSelectedItem();

                if (firstName.isEmpty() || lastName.isEmpty() || year.equals("Year") || month.equals("Month") || day.equals("Day")) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                    return;
                }

                String dob = year + "-" + month + "-" + day;
                String fullName = middleName.isEmpty() ? firstName + " " + lastName : firstName + " " + middleName + " " + lastName;

                try {
                    Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    String insertQuery = "INSERT INTO police (first_name, middle_name, last_name, dob, gender) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, middleName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, dob);
                    preparedStatement.setString(5, gender);
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Form submitted successfully!\nFull Name: " + fullName +
                            "\nDate of Birth: " + dob + "\nGender: " + gender);

                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error submitting form.");
                }
            }
        });

        panel.add(new JLabel()); // Empty label for spacing
        panel.add(submitButton);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addEnterKeyListener(JTextField sourceField, JTextField targetField) {
        sourceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    targetField.requestFocus();
                }
            }
        });
    }
}
