package Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagePoliceStationsPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cdrs";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "santosh";

    private JList<String> policeStationList;
    private DefaultListModel<String> listModel;

    public ManagePoliceStationsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        listModel = new DefaultListModel<>();
        policeStationList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(policeStationList);
        add(scrollPane, BorderLayout.CENTER);

        updatePoliceStationList();

        policeStationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> deleteSelectedPoliceStation());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updatePoliceStationList() {
        listModel.clear();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT name FROM police_stations";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listModel.addElement(resultSet.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the database.");
        }
    }

    private void deleteSelectedPoliceStation() {
        int selectedIndex = policeStationList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedStationName = listModel.getElementAt(selectedIndex);

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                String query = "DELETE FROM police_stations WHERE name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, selectedStationName);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Police station deleted successfully.");
                    updatePoliceStationList();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete police station.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to the database.");
            }
        }
    }
}
