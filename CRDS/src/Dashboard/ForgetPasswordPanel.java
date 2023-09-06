package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgetPasswordPanel {
    public static void showForgotPasswordDialog(JFrame parentFrame) {
        JTextField usernameField = new JTextField(20);
        JPasswordField newPasswordField = new JPasswordField(20);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);

        int result = JOptionPane.showConfirmDialog(parentFrame, panel,
                "Forgot Password", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String newPassword = new String(newPasswordField.getPassword());

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Username cannot be empty.",
                        "Password Reset", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String jdbcUrl = "jdbc:mysql://localhost:3306/cdrs";
                String dbUsername = "root";
                String dbPassword = "S@nt0sh143";
                try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword)) {
                    // Update the password in the database
                    String updateQuery = "UPDATE admin SET password = ? WHERE username = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setString(1, newPassword);
                        updateStatement.setString(2, username);
                        int rowsUpdated = updateStatement.executeUpdate();

                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(parentFrame, "Password updated successfully!",
                                    "Password Reset", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(parentFrame, "Username not found.",
                                    "Password Reset", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parentFrame, "Error connecting to the database.",
                        "Password Reset", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
