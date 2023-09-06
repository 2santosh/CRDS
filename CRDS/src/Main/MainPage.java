package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Admin.AdLogin;
import Police.PoLogin;
import User.UsLogin;

import Admin.AdRegister;

public class MainPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowUI();
        });
    }
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        
        return new ImageIcon(resizedImage);
    }
    
    private static void addLogoToPanel(JPanel panel) {
        ImageIcon logoImage = new ImageIcon("photo//2.png");
        int desiredImageWidth = 200;
        int desiredImageHeight = 200;
        ImageIcon resizedLogoImage = null;
        try {
            resizedLogoImage = resizeImageIcon(logoImage, desiredImageWidth, desiredImageHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel logoLabel = new JLabel(resizedLogoImage);
        
        panel.add(logoLabel);
    }


    public static void createAndShowUI() {
        JFrame frame = new JFrame("Company Info Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.black);
        panel3.setBackground(Color.black);
        panel4.setBackground(Color.black);
        panel5.setBackground(Color.lightGray);

        panel1.setPreferredSize(new Dimension(20, 20));
        panel2.setPreferredSize(new Dimension(5, 5));
        panel3.setPreferredSize(new Dimension(5, 5));
        panel4.setPreferredSize(new Dimension(5, 5));
        panel5.setPreferredSize(new Dimension(100, 100));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);

         // Create a new panel for buttons
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Logo Panel

        gbc.gridy = 0;
        gbc.weighty = 100.0;
        JPanel imagePanel = new JPanel(new GridBagLayout());
        addLogoToPanel(imagePanel);
        mainPanel.add(imagePanel, gbc);
        
        frame.add(mainPanel);
        gbc.gridy = 1;
        JPanel companyInfoPanel = new JPanel(new GridLayout(4, 1));
        JLabel companyNameLabel = new JLabel("CDRS Department");
        JLabel companyAddressLabel = new JLabel("Tinkune, Kathmandu, Nepal");
        JLabel gmailLabel = new JLabel("Gamil: CDRS@gmail.com.np");
        JLabel phoneLabel = new JLabel("Phone: 98011212");
        companyInfoPanel.add(companyNameLabel);
        companyInfoPanel.add(companyAddressLabel);
        companyInfoPanel.add(gmailLabel);
        companyInfoPanel.add(phoneLabel);
        mainPanel.add(companyInfoPanel, gbc);

        // Buttons Panel
        gbc.gridy = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        mainPanel.add(buttonPanel, gbc);

        frame.add(mainPanel);
        

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Admin", "Police", "User"};
                int choice = JOptionPane.showOptionDialog(frame, "Select user type:", "Login",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);

                if (choice == 0) {
                	mainPanel.removeAll();
                    JPanel logoAndLoginPanel = new JPanel(new BorderLayout());
                    logoAndLoginPanel.add(imagePanel, BorderLayout.NORTH);
                    JPanel loginPanel = AdLogin.createLoginPanel(frame);
                    logoAndLoginPanel.add(loginPanel, BorderLayout.CENTER);
                    loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    mainPanel.add(logoAndLoginPanel, gbc);
                    frame.revalidate();
                    frame.repaint();
                 } else if (choice == 1) {
                	 mainPanel.removeAll();
                     JPanel logoAndLoginPanel = new JPanel(new BorderLayout());
                     logoAndLoginPanel.add(imagePanel, BorderLayout.NORTH);
                     JPanel loginPanel = PoLogin.createLoginPanel(frame);
                     logoAndLoginPanel.add(loginPanel, BorderLayout.CENTER);
                     loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                     mainPanel.add(logoAndLoginPanel, gbc);
                     frame.revalidate();
                     frame.repaint();
                } else if(choice == 2) {
                	mainPanel.removeAll();
                    JPanel logoAndLoginPanel = new JPanel(new BorderLayout());
                    logoAndLoginPanel.add(imagePanel, BorderLayout.NORTH);
                    JPanel loginPanel = UsLogin.createLoginPanel(frame);
                    logoAndLoginPanel.add(loginPanel, BorderLayout.CENTER);
                    loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
                    mainPanel.add(logoAndLoginPanel, gbc);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        // Add ActionListener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Admin", "Police", "User"};
                int choice = JOptionPane.showOptionDialog(frame, "Select user type:", "Register",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);

                if (choice == 0) {
                    JOptionPane.showMessageDialog(frame, "Admin registration selected!");
                    AdRegister.createAndShowGUI(); // Calling the static method directly
                } else if (choice == 1) {
                    JOptionPane.showMessageDialog(frame, "Police registration selected!");
                //    PoRegister.createAndShowGUI(); // Calling the static method directly
                } else if (choice == 2) {
                    JOptionPane.showMessageDialog(frame, "User registration selected!");
                    //UsRegister.createAndShowGUI(); // Calling the static method directly
                }
            }
        });

        // Add ActionListener for the login button
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

    }
}
