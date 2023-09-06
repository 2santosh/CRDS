package ProfileDashboard;
import javax.swing.*;

import Police.PoMain;
import Main.MainPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logout {
    private JFrame frame;
    private JPanel contentPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new logout().createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Logout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JButton logoutButton = new JButton("Logout");
        contentPane.add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                frame.dispose();
                
                // Open the main page
                SwingUtilities.invokeLater(() -> {
                    MainPage.createAndShowUI();
                });

                // Close the PoMain window if it's open
                if (PoMain.getFrame() != null) {
                    PoMain.getFrame().dispose();
                }
            }
        });

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
