package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsRegister extends JFrame {
    public UsRegister() {
        setTitle("Police Panel - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.decode("#333333"));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        createMenuItem(menuPanel, "Police Station", "Add Police Station", "Manage Police Station");
        createMenuItem(menuPanel, "Police", "Add Police", "Manage Police");
        createMenuItem(menuPanel, "Criminal", "Add Criminal", "Manage Criminal");
        createMenuItem(menuPanel, "FIR", "New FIR", "Approved/Accepted FIR", "Cancelled/Rejected FIR", "All FIR");
        createMenuItem(menuPanel, "Charge Sheet", "New Charge Sheet", "Completed Charge Sheet");
        createMenuItem(menuPanel, "Report", "b/w dates report of Criminals", "b/w dates report of FIR");
        createMenuItem(menuPanel, "Search", "Search Criminals", "Search FIR");

        add(menuPanel, BorderLayout.WEST);

        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void createMenuItem(JPanel menuPanel, String itemName, String... subItems) {
        JButton menuItem = new JButton(itemName);
        menuItem.setPreferredSize(new Dimension(200, 40));
        menuItem.setFont(new Font("Arial", Font.PLAIN, 14));
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(Color.decode("#333333"));
        menuItem.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        menuItem.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel subNavPanel = new JPanel();
        subNavPanel.setLayout(new BoxLayout(subNavPanel, BoxLayout.Y_AXIS));
        subNavPanel.setBackground(Color.decode("#333333"));
        subNavPanel.setVisible(false);
        subNavPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSubNav(subNavPanel);
            }
        });

        menuPanel.add(menuItem);
        menuPanel.add(subNavPanel);

        for (String subItem : subItems) {
            JButton subItemButton = new JButton(subItem);
            subItemButton.setPreferredSize(new Dimension(180, 30));
            subItemButton.setFont(new Font("Arial", Font.PLAIN, 12));
            subItemButton.setForeground(Color.WHITE);
            subItemButton.setBackground(Color.decode("#444444"));
            subItemButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
            subNavPanel.add(subItemButton);
        }
    }

    private void toggleSubNav(JPanel subNavPanel) {
        subNavPanel.setVisible(!subNavPanel.isVisible());
    }
}
