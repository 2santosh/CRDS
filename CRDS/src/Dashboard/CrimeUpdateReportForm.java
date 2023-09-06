package Dashboard;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CrimeUpdateReportForm {
	  private static final String PAGE_CRIME_DETAILS = "Crime Details";
	    private static final String PAGE_WITNESS_INFO = "Witness Info";
	    private static final String PAGE_ADDITIONAL_INFO = "Additional Info";

	    private CardLayout cardLayout;
	    private JPanel cardPanel;

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            CrimeUpdateReportForm updateCrimeForm = new CrimeUpdateReportForm();
	            updateCrimeForm.createAndUpdateCrimeForm();
	        });
	    }

	    private void createAndUpdateCrimeForm() {
	        JFrame frame = new JFrame("Crime Update Report Form");
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
	        panel5.setBackground(Color.GRAY);

	        panel1.setPreferredSize(new Dimension(20, 20));
	        panel2.setPreferredSize(new Dimension(10, 10));
	        panel3.setPreferredSize(new Dimension(10, 10));
	        panel4.setPreferredSize(new Dimension(10, 10));
	        panel5.setPreferredSize(new Dimension(100, 100));

	        frame.add(panel1, BorderLayout.NORTH);
	        frame.add(panel2, BorderLayout.WEST);
	        frame.add(panel3, BorderLayout.EAST);
	        frame.add(panel4, BorderLayout.SOUTH);
	        frame.add(panel5, BorderLayout.CENTER);

	        // Add style to the frame
	        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));

	        cardPanel = new JPanel();
	        cardLayout = new CardLayout();
	        cardPanel.setLayout(cardLayout);

	        JPanel pageCrimeDetails = createCrimeDetailsPage();
	        JPanel pageWitnessInfo = createWitnessInfoPage();
	        JPanel pageAdditionalInfo = createAdditionalInfoPage();

	        cardPanel.add(pageCrimeDetails, PAGE_CRIME_DETAILS);
	        cardPanel.add(pageWitnessInfo, PAGE_WITNESS_INFO);
	        cardPanel.add(pageAdditionalInfo, PAGE_ADDITIONAL_INFO);

	        frame.add(cardPanel, BorderLayout.CENTER);

	        JButton nextButton = new JButton("Next");
	        nextButton.addActionListener(e -> {
	            cardLayout.next(cardPanel);
	        });

	        JButton submitButton = new JButton("Submit");
	        submitButton.addActionListener(e -> {
	            // Perform submit logic here
	            // You can save the updated crime report or perform other actions
	            JOptionPane.showMessageDialog(frame, "Crime report updated and submitted successfully!");
	        });

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        buttonPanel.add(nextButton);
	        buttonPanel.add(submitButton);

	        frame.add(buttonPanel, BorderLayout.SOUTH);

	        frame.setVisible(true);
	        frame.setLocationRelativeTo(null); // Center the frame on the screen
	    }


    private JPanel createCrimeDetailsPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel crimeTypeLabel = new JLabel("Crime Type:");
        JTextField crimeTypeField = new JTextField(20);

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField(20);

        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField(15);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JLabel photoLabel = new JLabel("Photo:");
        JTextField photoField = new JTextField(15);
        photoField.setEditable(false);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(panel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                photoField.setText(selectedFile.getAbsolutePath());
            }
        });

        panel.add(crimeTypeLabel, gbc);
        gbc.gridx++;
        panel.add(crimeTypeField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(locationLabel, gbc);
        gbc.gridx++;
        panel.add(locationField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(dateLabel, gbc);
        gbc.gridx++;
        panel.add(dateField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(descriptionLabel, gbc);
        gbc.gridx++;
        panel.add(descriptionScrollPane, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        
        panel.add(photoLabel, gbc);
        gbc.gridx++;
        panel.add(photoField, gbc);
        gbc.gridx++;
        panel.add(browseButton, gbc);

        return panel;
    }

    private JPanel createWitnessInfoPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel witnessNameLabel = new JLabel("Witness Name:");
        JTextField witnessNameField = new JTextField(20);

        JLabel witnessContactLabel = new JLabel("Witness Contact:");
        JTextField witnessContactField = new JTextField(15);

        panel.add(witnessNameLabel, gbc);
        gbc.gridx++;
        panel.add(witnessNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(witnessContactLabel, gbc);
        gbc.gridx++;
        panel.add(witnessContactField, gbc);

        return panel;
    }

    private JPanel createAdditionalInfoPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel officerNameLabel = new JLabel("Officer Name:");
        JTextField officerNameField = new JTextField(20);

        JLabel statusLabel = new JLabel("Status:");
        String[] statusOptions = {"Open", "Under Investigation", "Closed"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);

        panel.add(officerNameLabel, gbc);
        gbc.gridx++;
        panel.add(officerNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(statusLabel, gbc);
        gbc.gridx++;
        panel.add(statusComboBox, gbc);

        return panel;
    }
}
