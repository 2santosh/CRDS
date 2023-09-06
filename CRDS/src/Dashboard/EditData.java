package Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EditData extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton nextButton;
    private JTextField searchField;
    private int currentIndex = -1;

    public EditData(ArrayList<EditData> personList) {
        setTitle("Person Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        // Create a table model with column names
        String[] columnNames = {"ID", "First Name", "Middle Name", "Last Name", "Username", "Email", "Gender"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the table model with data from the personList
        for (EditData person : personList) {
            Object[] rowData = {person.getID(), person.getFirstName(), person.getMiddleName(),
                    person.getLastName(), person.getUserName(), person.getEmail(), person.getGender()};
            tableModel.addRow(rowData);
        }

        // Create the table and set the table model
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add a mouse listener to the table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 0 && row >= 0) {
                    // Click on the ID column (column index 0)
                    int selectedID = (int) tableModel.getValueAt(row, col);
                    openFullDetails(selectedID); // Fetch full details from the database using the ID
                }
            }
        });

        // Add the table to the JFrame
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel for buttons and search field
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String searchQuery = searchField.getText();
            filterTable(searchQuery);
        });

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            currentIndex++;
            if (currentIndex < tableModel.getRowCount()) {
                loadEditData(currentIndex);
            } else {
                JOptionPane.showMessageDialog(this, "No more rows to edit.");
                currentIndex = tableModel.getRowCount() - 1;
            }
        });

        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(nextButton);

        // Add the button panel to the JFrame
        add(buttonPanel, BorderLayout.NORTH);

        // Set JFrame visible
        setVisible(true);
    }

    private void filterTable(String query) {
      /*  // Reset the currentIndex when filtering the table
        currentIndex = -1;
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(query));
 */   }

    private void loadEditData(int index) {
        // Get the data from the selected row
        int ID = (int) tableModel.getValueAt(index, 0);
        String firstName = (String) tableModel.getValueAt(index, 1);
        String middleName = (String) tableModel.getValueAt(index, 2);
        String lastName = (String) tableModel.getValueAt(index, 3);
        String userName = (String) tableModel.getValueAt(index, 4);
        String email = (String) tableModel.getValueAt(index, 5);
        String gender = (String) tableModel.getValueAt(index, 6);

        // TODO: Load the data into input fields or text areas for editing
        // For example:
        // firstNameTextField.setText(firstName);
        // middleNameTextField.setText(middleName);
        // ...
    }

    private int getID() {
        // TODO: Implement getID()
        return 0;
    }

    private String getFirstName() {
        // TODO: Implement getFirstName()
        return null;
    }

    private String getMiddleName() {
        // TODO: Implement getMiddleName()
        return null;
    }

    private String getLastName() {
        // TODO: Implement getLastName()
        return null;
    }

    private String getUserName() {
        // TODO: Implement getUserName()
        return null;
    }

    private String getEmail() {
        // TODO: Implement getEmail()
        return null;
    }

    private String getGender() {
        // TODO: Implement getGender()
        return null;
    }

    private void openFullDetails(int selectedID) {
        // TODO: Fetch the full details of the person with the selectedID from the database
        // For example:
        // FullDetails fullDetails = fetchFullDetailsFromDatabase(selectedID);

        // TODO: Display the full details in a separate window or dialog
        // For example:
        // FullDetailsDialog fullDetailsDialog = new FullDetailsDialog(fullDetails);
        // fullDetailsDialog.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<EditData> personList = new ArrayList<>();

        // Fetch data from the database and add it to the personList

        SwingUtilities.invokeLater(() -> {
            new EditData(personList);
        });
    }
}

