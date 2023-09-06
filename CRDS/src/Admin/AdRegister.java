package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdRegister {
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

        // Add components for various personal details
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

        // Date of Birth
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Date of Birth:"));
        String[] years = {"Year", "1990", "1991", "1992"}; // Add more years as needed
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        JComboBox<String> monthComboBox = new JComboBox<>(new String[]{"Month", "01", "02", "03"}); // Add more months as needed
        JComboBox<String> dayComboBox = new JComboBox<>(new String[]{"Day", "01", "02", "03"}); // Add more days as needed
        datePanel.add(yearComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(dayComboBox);
        panel.add(datePanel);

        // Gender Selection
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

        // Add more components for other personal details

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields and other components
                String firstName = firstNameField.getText();
                String middleName = middleNameField.getText(); // Optional
                String lastName = lastNameField.getText();
                String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : "Other");
                String year = (String) yearComboBox.getSelectedItem();
                String month = (String) monthComboBox.getSelectedItem();
                String day = (String) dayComboBox.getSelectedItem();

                if (firstName.isEmpty() || lastName.isEmpty() || year.equals("Year") || month.equals("Month") || day.equals("Day")) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                    return;
                }

                String dob = year + "-" + month + "-" + day;
                String fullName = middleName.isEmpty() ? firstName + " " + lastName : firstName + " " + middleName + " " + lastName;

                // ... (Retrieve values and handle them as needed)

                // Show a message to indicate successful submission
                JOptionPane.showMessageDialog(frame, "Form submitted successfully!\nFull Name: " + fullName +
                        "\nDate of Birth: " + dob + "\nGender: " + gender);
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

	public static void createAndShowGUI() {
		// TODO Auto-generated method stub
		
	}
}
