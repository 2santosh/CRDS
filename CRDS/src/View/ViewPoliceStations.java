package View;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewPoliceStations extends JPanel {
	private static final long serialVersionUID = 1L;

	public ViewPoliceStations() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("View Police Stations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea policeStationsTextArea = new JTextArea();
        policeStationsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(policeStationsTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and display police station details
        fetchPoliceStationDetails(policeStationsTextArea);
    }

    private void fetchPoliceStationDetails(JTextArea textArea) {
        // Update with your actual database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/cdrs";
        String username = "root";
        String password = "S@nt0sh143";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM police_stations";
            ResultSet resultSet = statement.executeQuery(query);

            StringBuilder details = new StringBuilder();
            while (resultSet.next()) {
                int stationId = resultSet.getInt("station_id");
                String stationName = resultSet.getString("station_name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                details.append("Station ID: ").append(stationId).append("\n");
                details.append("Station Name: ").append(stationName).append("\n");
                details.append("Address: ").append(address).append("\n");
                details.append("Phone: ").append(phone).append("\n");
                details.append("Email: ").append(email).append("\n\n");
            }

            textArea.setText(details.toString());

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
