package Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class AdMain {
    private String loggedInUsername; // Store the logged-in username
    private JFrame frame; // The main frame

    private static ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return new ImageIcon(resizedImage);
    }

    public AdMain() {
        initializeMainFrame();
    }

    private void initializeMainFrame() {
        frame = new JFrame("Main Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create a left-side menu (unchanged)
        JPanel menuPanel = new JPanel();
        // ... (Menu creation code)

        // Create a top panel for header and user info (unchanged)
        JPanel topPanel = new JPanel(new BorderLayout());
        // ... (Header creation code)

        // Create the center area to display menu details (unchanged)
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridLayout(2, 3, 20, 20));
        // ... (Data boxes creation code)

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // Add a method to set the logged-in username
    public void setLoggedInUsername(String username) {
        loggedInUsername = username;

        // After setting the username, update the UI to display it
        updateUsernameLabel();
    }

    // Add a method to update the username label in the UI
    private void updateUsernameLabel() {
        if (frame != null) {
            JPanel topPanel = (JPanel) frame.getContentPane().getComponent(0); // Get the top panel
            JPanel headerPanel = (JPanel) topPanel.getComponent(0); // Get the header panel
            JPanel userPanel = (JPanel) headerPanel.getComponent(2); // Get the user panel
            JLabel userNameLabel = (JLabel) userPanel.getComponent(0); // Get the username label

            if (loggedInUsername != null) {
                userNameLabel.setText("Username: " + loggedInUsername);
            } else {
                userNameLabel.setText("Username: "); // Clear the username label
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create an instance of AdMain
            AdMain adMain = new AdMain();

            // Simulate user login and set the logged-in username
            adMain.setLoggedInUsername("Admin");

            // Now the username is available for the AdMain instance

            // Initialize the main frame
            adMain.initializeMainFrame();
        });
    }
}
