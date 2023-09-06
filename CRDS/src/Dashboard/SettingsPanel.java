package Dashboard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton settingsButton;
    

    public SettingsPanel() {
        setLayout(new BorderLayout());
        
        settingsButton = new JButton("Settings");
        add(settingsButton, BorderLayout.WEST);

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSettingsMenu();
            }
        });
    }

    private void showSettingsMenu() {
        JPopupMenu settingsMenu = new JPopupMenu();

        JMenuItem changePasswordItem = new JMenuItem("Change Password");
        JMenuItem viewProfileItem = new JMenuItem("View Profile");
        JMenuItem changeBackgroundColorItem = new JMenuItem("Change Background Color");
        JMenuItem helpAndSupportItem = new JMenuItem("Help and Support");
        JMenuItem logoutItem = new JMenuItem("Logout");

        settingsMenu.add(changePasswordItem);
        settingsMenu.add(viewProfileItem);
        settingsMenu.add(changeBackgroundColorItem);
        settingsMenu.add(helpAndSupportItem);
        settingsMenu.addSeparator();
        settingsMenu.add(logoutItem);

        // ActionListeners for the menu items
        changePasswordItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action for changing password
                JOptionPane.showMessageDialog(SettingsPanel.this, "Change Password clicked");
            }
        });

        viewProfileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action for viewing profile
                JOptionPane.showMessageDialog(SettingsPanel.this, "View Profile clicked");
            }
        });

        changeBackgroundColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action for changing background color
                Color newColor = JColorChooser.showDialog(SettingsPanel.this, "Choose Background Color", getBackground());
                if (newColor != null) {
                    setBackground(newColor);
                }
            }
        });

        helpAndSupportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action for help and support
                JOptionPane.showMessageDialog(SettingsPanel.this, "Help and Support clicked");
            }
        });

        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action for logging out
                JOptionPane.showMessageDialog(SettingsPanel.this, "Logout clicked");
            }
        });

        settingsMenu.show(settingsButton, 0, settingsButton.getHeight());
    }
}
