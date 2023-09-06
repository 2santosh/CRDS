package User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import View.ViewPoliceStations;

public class UsMain {
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(resizedImage);
    }

    public static void createAndShowMainFrame() {
    	JFrame frame = new JFrame("Main Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create a left-side menu
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.darkGray);
        menuPanel.setPreferredSize(new Dimension(200, frame.getHeight()));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add menu items to the menuPanel
        createMenuItem(menuPanel, "View Police Station");
        
        createMenuItem(menuPanel, "FIR", "New FIR","All FIR");
        createMenuItem(menuPanel, "Charge Sheet","Running Charge Sheet","Completed Charge Sheet");
        createMenuItem(menuPanel, "Report");
        createMenuItem(menuPanel, "Search","Search FIR");
        

        // Create a top panel for header and user info
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.lightGray);
        int desiredLogoSize = 100;
        ImageIcon logoImage = new ImageIcon("C:\\Users\\Santosh\\Desktop\\Project_IV\\2.png");
        ImageIcon resizedLogoImage = resizeImageIcon(logoImage, desiredLogoSize, desiredLogoSize);
        JLabel logoLabel = new JLabel(resizedLogoImage);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JPanel companyInfoPanel = new JPanel(new BorderLayout());
        JLabel companyNameLabel = new JLabel("CDRS Department");
        JLabel companyAddressLabel = new JLabel("Tinkune, Kathmandu");
        JLabel companyPhoneLabel = new JLabel("9823452423");
        JLabel CompanyEmailLabel = new JLabel("CRDS@gmail.com");
        companyInfoPanel.add(companyNameLabel, BorderLayout.NORTH);
        companyInfoPanel.add(companyAddressLabel, BorderLayout.CENTER);
        companyInfoPanel.add(companyPhoneLabel, BorderLayout.SOUTH);
        companyInfoPanel.add(CompanyEmailLabel, BorderLayout.SOUTH);
        headerPanel.add(companyInfoPanel, BorderLayout.CENTER);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(Color.decode("#f4f4f4"));

        ImageIcon userLogoImage = new ImageIcon("photo//2.png"); // Replace with the actual path
        ImageIcon resizedUserLogoImage = resizeImageIcon(userLogoImage, 40, 40); // Adjust the size as needed
        JLabel userLogoLabel = new JLabel(resizedUserLogoImage);

        JLabel userNameLabel = new JLabel("Admin"); // Replace with the actual user name

        userPanel.add(userNameLabel);
        userPanel.add(userLogoLabel);

        headerPanel.add(userPanel, BorderLayout.EAST);
        topPanel.add(headerPanel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);
        
        JPopupMenu userMenu = new JPopupMenu();
        JMenuItem viewProfileMenuItem = new JMenuItem("View Profile");
        JMenuItem changePasswordMenuItem = new JMenuItem("Change Password");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");

        userMenu.add(viewProfileMenuItem);
        userMenu.add(changePasswordMenuItem);
        userMenu.addSeparator(); // Separator line between menu items
        userMenu.add(logoutMenuItem);

        userLogoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userMenu.show(userLogoLabel, e.getX(), e.getY());
            }
        });

        userPanel.add(userNameLabel);
        userPanel.add(userLogoLabel);

        headerPanel.add(userPanel, BorderLayout.EAST);
        topPanel.add(headerPanel, BorderLayout.CENTER);
        // Create the center area to display menu details
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(2, 3, 20, 20));

        createDataBox(centerPanel, "Total Criminals", "1000");
        createDataBox(centerPanel, "Total Police", "500");
        createDataBox(centerPanel, "Total Crime Categories", "10");
        createDataBox(centerPanel, "Total Police Stations", "20");
        createDataBox(centerPanel, "Total FIRs", "300");

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.WEST);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }


    private static JPanel currentSubNavPanel; // Store the currently open sub-navigation panel

    private static void createMenuItem(JPanel menuPanel, String itemName, String... subItems) {
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

     // Inside the createMenuItem method
        if (itemName.equals("View Police Station")) {
            // ... (rest of the code for menuItem setup)

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentSubNavPanel != null && currentSubNavPanel != subNavPanel) {
                        currentSubNavPanel.setVisible(false);
                    }
                    toggleSubNav(subNavPanel);

                    if (subNavPanel.getComponentCount() == 0) {
                        ViewPoliceStations viewPoliceStations = new ViewPoliceStations();
                        subNavPanel.add(viewPoliceStations);
                    }
                    currentSubNavPanel = subNavPanel;
                }
            });

            menuPanel.add(menuItem);
            menuPanel.add(subNavPanel);
        }
        

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

    private static void toggleSubNav(JPanel subNavPanel) {
        subNavPanel.setVisible(!subNavPanel.isVisible());
    }

    private static void createDataBox(JPanel container, String title, String value) {
        JPanel dataBox = new JPanel();
        dataBox.setBackground(Color.decode("#f4f4f4"));
        dataBox.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        dataBox.setLayout(new BoxLayout(dataBox, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton viewAllButton = new JButton("View All");
        viewAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewAllButton.addActionListener(e -> {
            // Handle view all action here
        });

        dataBox.add(titleLabel);
        dataBox.add(Box.createVerticalStrut(10));
        dataBox.add(valueLabel);
        dataBox.add(Box.createVerticalStrut(10));
        dataBox.add(viewAllButton);

        container.add(dataBox);
    }
}
