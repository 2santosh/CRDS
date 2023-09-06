package ProfileDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Database.DatabaseUtility; // Import your database utility class

public class changePassword extends JPanel {

    private static final long serialVersionUID = 1L;
    private String username;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            changePassword changepassword = new changePassword();
            changepassword.createAndShowMainFrame();
        });
    }

    public void createAndShowMainFrame() {
        JFrame frame = new JFrame("Change Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        username = "santosh123"; 
        
        DatabaseUtility databaseUtility = new DatabaseUtility();
        String fullName = databaseUtility.retrieveUserInfo(username);

        if (fullName != null) {
            JLabel nameLabel = new JLabel("Name: " + fullName);
            add(nameLabel);
        }

        // Add a password change section
        JLabel changePasswordLabel = new JLabel("Change Password:");
        JPasswordField oldPasswordField = new JPasswordField(20);
        JPasswordField newPasswordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JButton changePasswordButton = new JButton("Change Password");

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle password change logic here
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
/*
                if (databaseUtility.checkPassword(username, oldPassword)) {
                    // Check if the new password and confirm password match
                    if (newPassword.equals(confirmPassword)) {
                        // Update the password in the database
                        if (databaseUtility.updatePassword(username, newPassword)) {
                            JOptionPane.showMessageDialog(frame, "Password changed successfully!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Password change failed.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "New password and confirm password do not match.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect old password.");
                }
                */
            }
        });
        add(changePasswordLabel);
        add(new JLabel("Old Password:"));
        add(oldPasswordField);
        add(new JLabel("New Password:"));
        add(newPasswordField);
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordField);
        add(changePasswordButton);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);

    }
}
