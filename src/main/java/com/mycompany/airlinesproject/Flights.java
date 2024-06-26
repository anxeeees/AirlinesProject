
package com.mycompany.airlinesproject;

import com.mycompany.airlinesproject.entities.Flight;
import com.mycompany.airlinesproject.repositories.FlightRepository;

import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;



/**
 * GUI class for managing flights information.
 * Provides functionality for displaying, editing, deleting, and saving flight details.
 * Author: Ester
 */
public class Flights extends javax.swing.JFrame {



    private FlightRepository flightRepository;

    /**
     * Creates new form Flights
     */
    public Flights() {
        initComponents();
        flightRepository = new FlightRepository();
        displayFlight();
    }

    /**
     * Handles mouse click event on the back button.
     * Navigates back to the main form and disposes the current frame.
     *
     * @param evt The MouseEvent triggered by the user's mouse actions.
     */

    private void back_buttonMouseClicked(java.awt.event.MouseEvent evt) {
        new MainForm().setVisible(true);
        this.dispose();
    }

    /**
     * Handles mouse click event on the delete button.
     * Deletes the selected flight from the repository and updates the UI.
     * Displays an error message if no flight is selected.
     *
     * @param evt The MouseEvent triggered by the user's mouse actions.
     */
    private void delete_buttonMouseClicked(java.awt.event.MouseEvent evt) {
        if (key == "0") {
            JOptionPane.showMessageDialog(this, "Select a flight");
        } else {
            flightRepository.deleteFlight(key);
            JOptionPane.showMessageDialog(this, "Flight deleted");
            displayFlight();
            clear();
        }
    }

    /**
     * Handle mouse click event on the edit button.
     * Validates input fields and updates the selected flight's information.
     * Displays error message if any required information is missing or if the flight code is incorrect.
     *
     * @param evt The MouseEvent triggered by the user's mouse actions.
     */
    private void edit_buttonMouseClicked(java.awt.event.MouseEvent evt) {
        if (flight_code.getText().isEmpty() || flight_nos.getText().isEmpty()
                || flight_source.getSelectedIndex() == -1 || flight_destination.getSelectedIndex() == -1 || flight_tof.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Missing information");
        } else {
            try {
                int seats = Integer.parseInt(flight_nos.getText());

                // Check if flight code matches key
                if (!flight_code.getText().equals(key)) {
                    JOptionPane.showMessageDialog(this, "Selected flight does not match the entered flight code. Please select the correct flight.");
                } else {
                    // Update flight information in the repository
                    flightRepository.updateFlight(Long.valueOf(flight_code.getText()),
                            flight_source.getSelectedItem().toString(),
                            flight_destination.getSelectedItem().toString(),
                            flight_tof.getDate(),
                            String.valueOf(seats));

                    JOptionPane.showMessageDialog(this, "Flight updated");
                    displayFlight();
                    clear();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Incorrect format for seats number. Please enter a valid integer.");
            }
        }
    }

    /**
     * Displays flights information in the table.
     * Retrieves flights from the repository and populates the table with flight details.
     */

    private void displayFlight() {
        List<Flight> flights = flightRepository.getFlights();

        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("FlightID");
        tableHeaders.add("Code");
        tableHeaders.add("Source");
        tableHeaders.add("Destination");
        tableHeaders.add("Date");
        tableHeaders.add("Seats");

        for (Flight flight : flights) {

            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(flight.getFlightId());
            oneRow.add(flight.getCode());
            oneRow.add(flight.getSource());
            oneRow.add(flight.getDestination());
            oneRow.add(flight.getDate());
            oneRow.add(flight.getSeats());
            tableData.add(oneRow);
        }
        flight_table.setModel(new DefaultTableModel(tableData, tableHeaders));
    }

    private void clear() {
        flight_code.setText("");
        flight_nos.setText("");
        flight_source.setSelectedIndex(0);
        flight_destination.setSelectedIndex(0);
        flight_nos.setText("");
        flight_tof.setDate(null);
    }

    /**
     * Handle mouse click event on the save button.
     * Validates input fields and saves a new flight with the provided information.
     * Displays error message if any required information is missing or if the seats number format is incorrect.
     *
     * @param evt The MouseEvent triggered by the user's mouse actions.
     */
    private void save_buttonMouseClicked(java.awt.event.MouseEvent evt) {
        if (flight_code.getText().isEmpty() || flight_nos.getText().isEmpty()
                || flight_source.getSelectedIndex() == -1 || flight_destination.getSelectedIndex() == -1 || flight_tof.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Missing information");
        } else {
            try {
                int seats = Integer.parseInt(flight_nos.getText());
                Flight flight = new Flight(flight_code.getText(),
                        flight_source.getSelectedItem().toString(),
                        flight_destination.getSelectedItem().toString(),
                        flight_tof.getDate(),
                        seats);
                flightRepository.saveFlight(flight);
                JOptionPane.showMessageDialog(this, "Flight added");
                displayFlight();
                clear();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Incorrect format for seats number. Please enter a valid integer.");
            }
        }
    }

    /**
     * Handles mouse click event on the flight table.
     * Retrieves the selected flight details and populates the form fields for editing.
     *
     * @param evt The MouseEvent triggered by the user's mouse actions.
     */
    String key = "";

    private void flight_tableMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) flight_table.getModel();
        int myIndex = flight_table.getSelectedRow();
        key = model.getValueAt(myIndex,0).toString();
        flight_code.setText(model.getValueAt(myIndex,0).toString());
        flight_source.setSelectedItem(model.getValueAt(myIndex, 2).toString());
        flight_destination.setSelectedItem(model.getValueAt(myIndex, 3).toString());
        flight_tof.setDate((Date) model.getValueAt(myIndex,4));
        flight_nos.setText(model.getValueAt(myIndex, 5).toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ex) {

            }
            Flights flights = new Flights();
            flights.setVisible(true);
        });
    }




    public void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        flight_table = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        flight_destination = new javax.swing.JComboBox<>();
        flight_nos = new com.mycompany.airlinesproject.FTextField();
        flight_code = new com.mycompany.airlinesproject.FTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        flight_source = new javax.swing.JComboBox<>();
        flight_tof = new com.toedter.calendar.JDateChooser();
        back_button = new com.mycompany.airlinesproject.RoundedButton();
        delete_button = new com.mycompany.airlinesproject.RoundedButton();
        edit_button = new com.mycompany.airlinesproject.RoundedButton();
        save_button = new com.mycompany.airlinesproject.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 28, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("  Manage flights");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Flights");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Take of date");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Number of seats");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Source");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("SkyWing Airlines");

        flight_table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {},
                        {},
                        {},
                        {}
                },
                new String[]{

                }
        ));
        flight_table.setRowHeight(25);
        flight_table.setSelectionBackground(new java.awt.Color(102, 102, 102));
        flight_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flight_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(flight_table);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Flight code");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Destination");

        flight_destination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "New York",
                "Los Angeles",
                "London",
                "Paris",
                "Tokyo",
                "Sydney",
                "Dubai",
                "Rome",
                "Hong Kong",
                "Berlin",
                "Singapore",
                "Toronto",
                "Barcelona",
                "Amsterdam",
                "Seoul",
                "Istanbul",
                "Mexico City",
                "Moscow",
                "Bangkok",
                "Cairo",
                "Rio de Janeiro",
                "Mumbai",
                "Athens",
                "Stockholm",
                "San Francisco",
                "Las Vegas",
                "Chicago",
                "Madrid",
                "Dublin",
                "Prague",
                "Venice",
                "Vienna",
                "Budapest",
                "Copenhagen",
                "Helsinki",
                "Oslo",
                "Warsaw",
                "Brussels",
                "Geneva",
                "Munich",
                "Frankfurt",
                "Lisbon",
                "Athens",
                "Istanbul",
                "Beijing",
                "Shanghai",
                "Seoul",
                "Hong Kong",
                "Singapore",
                "Sydney",
                "Melbourne",
                "Auckland",
                "Honolulu",
                "Bali",
                "Bangkok",
                "Mumbai",
                "Delhi",
                "Dubai",
                "Abu Dhabi",
                "Cairo",
                "Johannesburg",
                "Cape Town",
                "Nairobi",
                "Rio de Janeiro",
                "Buenos Aires",
                "Santiago",
                "Mexico City",
                "Toronto",
                "Vancouver",
                "Montreal",
                "New Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "Hyderabad",
                "Bengaluru",
                "Pune",
                "Jaipur",
                "Goa",
                "Kochi",
                "Ahmedabad",
                "Lucknow",
                "Agra",
                "Amritsar",
                "Varanasi",
                "Thiruvananthapuram",
                "Guwahati",
                "Patna",
                "Ranchi",
                "Bhopal",
                "Indore",
                "Nagpur",
                "Visakhapatnam",
                "Chandigarh",
                "Raipur",
                "Jammu",
                "Srinagar",
                "Port Blair",
                "Gangtok",
                "Shillong",
                "Itanagar",
                "Kohima",
                "Imphal",
                "Aizawl",
                "Agartala",
                "Dispur",
                "Bhubaneswar",
                "Jaipur",
                "Dehradun",
                "Shimla",
                "Chandigarh",
                "Panaji",
                "Puducherry",
                "Chandigarh",
                "Bilaspur",
                "Rajkot",
                "Gandhinagar",
                "Gangtok",
                "Aizawl",
                "Kohima",
                "Imphal",
                "Itanagar",
                "Dispur",
                "Shillong",
                "Agartala",
                "Raipur",
                "Naya Raipur",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Mumbai",
                "Hyderabad",
                "Lucknow",
                "Bhopal",
                "Patna",
                "Panaji",
                "Jaipur",
                "Gandhinagar",
                "Shimla",
                "Dehradun",
                "Puducherry",
                "Thiruvananthapuram",
                "Amaravati",
                "Agartala",
                "Shillong",
                "Gangtok",
                "Itanagar",
                "Kohima",
                "Imphal",
                "Aizawl",
                "Chandigarh",
                "Srinagar",
                "Hyderabad",
                "Mumbai",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Guwahati",
                "Agartala",
                "Imphal",
                "Itanagar",
                "Shillong",
                "Kohima",
                "Aizawl",
                "Gangtok",
                "Raipur",
                "Panaji",
                "Thiruvananthapuram",
                "Jaipur",
                "Bhubaneswar",
                "Gandhinagar",
                "Lucknow",
                "Chandigarh",
                "Shimla",
                "Dehradun",
                "Ranchi",
                "Bhopal",
                "Patna",
                "Dispur",
                "Shillong",
                "Agartala",
                "Amaravati",
                "Aizawl",
                "Chandigarh",
                "Gandhinagar",
                "Gangtok",
                "Imphal",
                "Itanagar",
                "Kohima",
                "Lucknow",
                "Panaji",
                "Shimla",
                "Srinagar",
                "Thiruvananthapuram",
                "Agartala",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Mumbai",
                "Shillong",
                "Gangtok",
                "Itanagar",
                "Kohima",
                "Aizawl",
                "Agartala",
                "Imphal",
                "Jaipur",
                "Lucknow",
                "Dehradun",
                "Dispur",
                "Gandhinagar",
                "Patna",
                "Shimla",
                "Amaravati",
                "Chandigarh",
                "Panaji",
                "Raipur",
                "Bhubaneswar",
                "Ranchi",
                "Srinagar",
                "Thiruvananthapuram",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Mumbai",
                "Hyderabad",
                "Lucknow",
                "Bhopal",
                "Patna",
                "Jaipur",
                "Gandhinagar",
                "Shimla",
                "Dehradun",
                "Puducherry",
                "Thiruvananthapuram",
                "Amaravati",
                "Agartala",
                "Shillong",
                "Gangtok",
                "Itanagar",
                "Kohima",
                "Imphal",
                "Aizawl",
                "Chandigarh",
                "Srinagar"
        }));


        jPanel5.setBackground(new java.awt.Color(220, 219, 219));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 14, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(220, 219, 219));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(220, 219, 219));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(220, 219, 219));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(220, 219, 219));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(220, 219, 219));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 13, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1217, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 12, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 421, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 8, Short.MAX_VALUE)
        );

        flight_source.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
                "New York",
                "Los Angeles",
                "London",
                "Paris",
                "Tokyo",
                "Sydney",
                "Dubai",
                "Rome",
                "Hong Kong",
                "Berlin",
                "Singapore",
                "Toronto",
                "Barcelona",
                "Amsterdam",
                "Seoul",
                "Istanbul",
                "Mexico City",
                "Moscow",
                "Bangkok",
                "Cairo",
                "Rio de Janeiro",
                "Mumbai",
                "Athens",
                "Stockholm",
                "San Francisco",
                "Las Vegas",
                "Chicago",
                "Madrid",
                "Dublin",
                "Prague",
                "Venice",
                "Vienna",
                "Budapest",
                "Copenhagen",
                "Helsinki",
                "Oslo",
                "Warsaw",
                "Brussels",
                "Geneva",
                "Munich",
                "Frankfurt",
                "Lisbon",
                "Athens",
                "Istanbul",
                "Beijing",
                "Shanghai",
                "Seoul",
                "Hong Kong",
                "Singapore",
                "Sydney",
                "Melbourne",
                "Auckland",
                "Honolulu",
                "Bali",
                "Bangkok",
                "Mumbai",
                "Delhi",
                "Dubai",
                "Abu Dhabi",
                "Cairo",
                "Johannesburg",
                "Cape Town",
                "Nairobi",
                "Rio de Janeiro",
                "Buenos Aires",
                "Santiago",
                "Mexico City",
                "Toronto",
                "Vancouver",
                "Montreal",
                "New Delhi",
                "Mumbai",
                "Chennai",
                "Kolkata",
                "Hyderabad",
                "Bengaluru",
                "Pune",
                "Jaipur",
                "Goa",
                "Kochi",
                "Ahmedabad",
                "Lucknow",
                "Agra",
                "Amritsar",
                "Varanasi",
                "Thiruvananthapuram",
                "Guwahati",
                "Patna",
                "Ranchi",
                "Bhopal",
                "Indore",
                "Nagpur",
                "Visakhapatnam",
                "Chandigarh",
                "Raipur",
                "Jammu",
                "Srinagar",
                "Port Blair",
                "Gangtok",
                "Shillong",
                "Itanagar",
                "Kohima",
                "Imphal",
                "Aizawl",
                "Agartala",
                "Dispur",
                "Bhubaneswar",
                "Jaipur",
                "Dehradun",
                "Shimla",
                "Chandigarh",
                "Panaji",
                "Puducherry",
                "Chandigarh",
                "Bilaspur",
                "Rajkot",
                "Gandhinagar",
                "Gangtok",
                "Aizawl",
                "Kohima",
                "Imphal",
                "Itanagar",
                "Dispur",
                "Shillong",
                "Agartala",
                "Raipur",
                "Naya Raipur",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Mumbai",
                "Hyderabad",
                "Lucknow",
                "Bhopal",
                "Patna",
                "Panaji",
                "Jaipur",
                "Gandhinagar",
                "Shimla",
                "Dehradun",
                "Puducherry",
                "Thiruvananthapuram",
                "Amaravati",
                "Agartala",
                "Shillong",
                "Gangtok",
                "Itanagar",
                "Kohima",
                "Imphal",
                "Aizawl",
                "Chandigarh",
                "Srinagar",
                "Hyderabad",
                "Mumbai",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Guwahati",
                "Agartala",
                "Imphal",
                "Itanagar",
                "Shillong",
                "Kohima",
                "Aizawl",
                "Gangtok",
                "Raipur",
                "Panaji",
                "Thiruvananthapuram",
                "Jaipur",
                "Bhubaneswar",
                "Gandhinagar",
                "Lucknow",
                "Chandigarh",
                "Shimla",
                "Dehradun",
                "Ranchi",
                "Bhopal",
                "Patna",
                "Dispur",
                "Shillong",
                "Agartala",
                "Amaravati",
                "Aizawl",
                "Chandigarh",
                "Gandhinagar",
                "Gangtok",
                "Imphal",
                "Itanagar",
                "Kohima",
                "Lucknow",
                "Panaji",
                "Shimla",
                "Srinagar",
                "Thiruvananthapuram",
                "Agartala",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Mumbai",
                "Shillong",
                "Gangtok",
                "Itanagar",
                "Kohima",
                "Aizawl",
                "Agartala",
                "Imphal",
                "Jaipur",
                "Lucknow",
                "Dehradun",
                "Dispur",
                "Gandhinagar",
                "Patna",
                "Shimla",
                "Amaravati",
                "Chandigarh",
                "Panaji",
                "Raipur",
                "Bhubaneswar",
                "Ranchi",
                "Srinagar",
                "Thiruvananthapuram",
                "Bengaluru",
                "Chennai",
                "Kolkata",
                "Mumbai",
                "Hyderabad",
                "Lucknow",
                "Bhopal",
                "Patna",
                "Jaipur",
                "Gandhinagar",
                "Shimla",
                "Dehradun",
                "Puducherry",
                "Thiruvananthapuram",
                "Amaravati",
                "Agartala",
                "Shillong",
                "Gangtok",
                "Itanagar",
                "Kohima",
                "Imphal",
                "Aizawl",
                "Chandigarh",
                "Srinagar"
        }));

        back_button.setText("Back");
        back_button.setFillOver(new java.awt.Color(204, 204, 204));
        back_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        back_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_buttonMouseClicked(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.setFillOver(new java.awt.Color(204, 204, 204));
        delete_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        delete_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delete_buttonMouseClicked(evt);
            }
        });

        edit_button.setText("Edit");
        edit_button.setFillOver(new java.awt.Color(204, 204, 204));
        edit_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_buttonMouseClicked(evt);
            }
        });

        save_button.setText("Save");
        save_button.setFillOver(new java.awt.Color(204, 204, 204));
        save_button.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        save_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                save_buttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(47, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(548, 548, 548))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(47, 47, 47)
                                                                                .addComponent(flight_tof, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(flight_code, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel8)
                                                                                .addGap(108, 108, 108)
                                                                                .addComponent(flight_source, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(34, 34, 34)
                                                                                .addComponent(flight_destination, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel6)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(flight_nos, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(41, 41, 41))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(528, 528, 528)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(372, 372, 372)
                                                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(429, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(408, 408, 408)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(17, 17, 17)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(flight_code, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(flight_source, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(flight_destination, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(flight_tof, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(flight_nos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(11, 11, 11)
                                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1))
                                .addContainerGap(178, Short.MAX_VALUE))
        );

        Locale locale = new Locale("en", "US");
        flight_tof.setLocale(locale);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }



    private com.mycompany.airlinesproject.RoundedButton back_button;
    private com.mycompany.airlinesproject.RoundedButton delete_button;
    private com.mycompany.airlinesproject.RoundedButton edit_button;
    private com.mycompany.airlinesproject.FTextField flight_code;
    private javax.swing.JComboBox<String> flight_destination;
    private com.mycompany.airlinesproject.FTextField flight_nos;
    private javax.swing.JComboBox<String> flight_source;
    private javax.swing.JTable flight_table;
    private com.toedter.calendar.JDateChooser flight_tof;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.mycompany.airlinesproject.RoundedButton save_button;

}
