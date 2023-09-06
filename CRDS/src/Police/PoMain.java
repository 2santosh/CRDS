package Police;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ProfileDashboard.UserProfilePanel;
import ProfileDashboard.changePassword;
import ProfileDashboard.logout;

public class PoMain {
	private String Username;
	
    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(resizedImage);
    }

    public void createAndShowMainFrame(String username) {
    	this.Username = username;
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
        createMenuItem(menuPanel, "Police Station", "Add Police Station", "Manage Police Station");
        createMenuItem(menuPanel, "Police", "Add Police", "Manage Police");
        createMenuItem(menuPanel, "Criminal", "Add Criminal", "Manage Criminal");
        createMenuItem(menuPanel, "FIR", "New FIR", "Approved/Accepted FIR", "Cancelled/Rejected FIR", "All FIR");
        createMenuItem(menuPanel, "Charge Sheet", "New Charge Sheet", "Completed Charge Sheet");
        createMenuItem(menuPanel, "Report", "b/w dates report of Criminals", "b/w dates report of FIR");
        createMenuItem(menuPanel, "Search", "Search Criminals", "Search FIR");

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

        ImageIcon userLogoImage = new ImageIcon("C:\\Users\\Santosh\\Desktop\\2.png"); // Replace with the actual path
        ImageIcon resizedUserLogoImage = resizeImageIcon(userLogoImage, 40, 40); // Adjust the size as needed
        JLabel userLogoLabel = new JLabel(resizedUserLogoImage);

        JLabel userNameLabel = new JLabel(Username); // Replace with the actual user name

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

        
        viewProfileMenuItem.addActionListener(e -> {
            // Implement the action for View Profile button here
            // You can open a new window or dialog to display the user's profile
        	 UserProfilePanel userProfilePanel = new UserProfilePanel();

             // Call the createAndShowMainFrame method
             userProfilePanel.createAndShowMainFrame(Username);
        });
        
        changePasswordMenuItem.addActionListener(e -> {
        	// Assuming you have a login form where the user enters their username
        	String username1 = Username; // Replace this with your actual code

        	// Pass the username to the UserProfilePanel
        	changePassword changepassword = new changePassword();
        	changepassword.createAndShowMainFrame();

        });
        
        logoutMenuItem.addActionListener(e -> {
        	SwingUtilities.invokeLater(() -> {
                new logout().createAndShowGUI();
            });
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

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdrs","root","S@nt0sh143")) {
            // Query the database to fetch the desired data
            String criminalsQuery = "SELECT COUNT(*) FROM criminals";
            String policeQuery = "SELECT COUNT(*) FROM police";
            String crimeCategoriesQuery = "SELECT COUNT(*) FROM crime_categories";
            String policeStationsQuery = "SELECT COUNT(*) FROM police_stations";
            String firQuery = "SELECT COUNT(*) FROM fir";

            int totalCriminals = fetchDataFromDatabase(connection, criminalsQuery);
            int totalPolice = fetchDataFromDatabase(connection, policeQuery);
            int totalCrimeCategories = fetchDataFromDatabase(connection, crimeCategoriesQuery);
            int totalPoliceStations = fetchDataFromDatabase(connection, policeStationsQuery);
            int totalFIRs = fetchDataFromDatabase(connection, firQuery);

            // Call createDataBox to display the data
            createDataBox(centerPanel, "Total Criminals", String.valueOf(totalCriminals));
            createDataBox(centerPanel, "Total Police", String.valueOf(totalPolice));
            createDataBox(centerPanel, "Total Crime Categories", String.valueOf(totalCrimeCategories));
            createDataBox(centerPanel, "Total Police Stations", String.valueOf(totalPoliceStations));
            createDataBox(centerPanel, "Total FIRs", String.valueOf(totalFIRs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.WEST);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    private int fetchDataFromDatabase(Connection connection, String criminalsQuery) {
		// TODO Auto-generated method stub
		return 0;
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

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSubNavPanel != null && currentSubNavPanel != subNavPanel) {
                    currentSubNavPanel.setVisible(false); // Close the previously open sub-nav
                }
                toggleSubNav(subNavPanel);
                currentSubNavPanel = subNavPanel; // Update the currently open sub-nav
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
    public static JFrame getFrame() {
		return getFrame();
    }
   
    private void viewAllCriminals() {
        JFrame viewAllCriminalsFrame = new JFrame("View All Criminals");
        viewAllCriminalsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel criminalsPanel = new JPanel();
        criminalsPanel.setLayout(new BoxLayout(criminalsPanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdrs","root","S@nt0sh143")) {
            String query = "SELECT * FROM criminals";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String criminalName = resultSet.getString("name");
                String criminalAge = resultSet.getString("age");
                String criminalAddress = resultSet.getString("address");

                JLabel criminalLabel = new JLabel("Name: " + criminalName + ", Age: " + criminalAge + ", Address: " + criminalAddress);

                criminalsPanel.add(criminalLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(criminalsPanel);
        viewAllCriminalsFrame.add(scrollPane);

        viewAllCriminalsFrame.setSize(400, 300);
        viewAllCriminalsFrame.setLocationRelativeTo(null);
        viewAllCriminalsFrame.setVisible(true);
    }

    private void viewAllPolice() {
        JFrame viewAllPoliceFrame = new JFrame("View All Police Personnel");
        viewAllPoliceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel policePanel = new JPanel();
        policePanel.setLayout(new BoxLayout(policePanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdrs","root","S@nt0sh143")) {
            String query = "SELECT * FROM police";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String policeName = resultSet.getString("name");
                String policeBadgeNumber = resultSet.getString("badge_number");
                String policeRank = resultSet.getString("rank");
                String policeDepartment = resultSet.getString("department");

                JLabel policeLabel = new JLabel("Name: " + policeName + ", Badge Number: " + policeBadgeNumber + ", Rank: " + policeRank + ", Department: " + policeDepartment);

                policePanel.add(policeLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(policePanel);
        viewAllPoliceFrame.add(scrollPane);

        viewAllPoliceFrame.setSize(400, 300);
        viewAllPoliceFrame.setLocationRelativeTo(null);
        viewAllPoliceFrame.setVisible(true);
    }

    private void viewAllCrimeCategories() {
        JFrame viewAllCrimeCategoriesFrame = new JFrame("View All Crime Categories");
        viewAllCrimeCategoriesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel crimeCategoriesPanel = new JPanel();
        crimeCategoriesPanel.setLayout(new BoxLayout(crimeCategoriesPanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdrs","root","S@nt0sh143")) {
            String query = "SELECT * FROM crime_categories";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                String categoryDescription = resultSet.getString("category_description");

                JLabel categoryLabel = new JLabel("Category Name: " + categoryName + ", Description: " + categoryDescription);

                crimeCategoriesPanel.add(categoryLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(crimeCategoriesPanel);
        viewAllCrimeCategoriesFrame.add(scrollPane);

        viewAllCrimeCategoriesFrame.setSize(400, 300);
        viewAllCrimeCategoriesFrame.setLocationRelativeTo(null);
        viewAllCrimeCategoriesFrame.setVisible(true);
    }

    private void viewAllPoliceStations() {
        JFrame viewAllPoliceStationsFrame = new JFrame("View All Police Stations");
        viewAllPoliceStationsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel policeStationsPanel = new JPanel();
        policeStationsPanel.setLayout(new BoxLayout(policeStationsPanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdrs","root","S@nt0sh143")) {
            String query = "SELECT * FROM police_stations";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String stationName = resultSet.getString("station_name");
                String stationLocation = resultSet.getString("station_location");

                JLabel stationLabel = new JLabel("Station Name: " + stationName + ", Location: " + stationLocation);

                policeStationsPanel.add(stationLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(policeStationsPanel);
        viewAllPoliceStationsFrame.add(scrollPane);

        viewAllPoliceStationsFrame.setSize(400, 300);
        viewAllPoliceStationsFrame.setLocationRelativeTo(null);
        viewAllPoliceStationsFrame.setVisible(true);
    }

    private void viewAllFIRs() {
        JFrame viewAllFIRsFrame = new JFrame("View All FIRs");
        viewAllFIRsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel firsPanel = new JPanel();
        firsPanel.setLayout(new BoxLayout(firsPanel, BoxLayout.Y_AXIS));

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdrs","root","S@nt0sh143")) {
            String query = "SELECT * FROM firs";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firNumber = resultSet.getString("fir_number");
                String reportingDate = resultSet.getString("reporting_date");
                String description = resultSet.getString("description");

                JLabel firLabel = new JLabel("FIR Number: " + firNumber + ", Reporting Date: " + reportingDate + ", Description: " + description);

                firsPanel.add(firLabel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(firsPanel);
        viewAllFIRsFrame.add(scrollPane);

        viewAllFIRsFrame.setSize(400, 300);
        viewAllFIRsFrame.setLocationRelativeTo(null);
        viewAllFIRsFrame.setVisible(true);
    }
}
