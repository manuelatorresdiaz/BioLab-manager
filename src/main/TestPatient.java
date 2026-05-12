package main;

import interfaces.PatientManager;
import jdbc.ConnectionManager;
import jdbc.JDBCPatientManager;
import bioLabUI.PatientUI;
/**
 * Isolated Test Launcher for the Patient Management Module.
 * Used to verify the persistence of demographic data and BLOB handling 
 * (patient photos/ID scans) without launching the full application.
 */
public class TestPatient {
    public static void main(String[] args) {
    	// Initialize the shared database connection manager
        ConnectionManager cm = new ConnectionManager();
        // Strategy Pattern: Using the PatientManager interface to interact 
        // with the specific JDBC implementation.
        PatientManager manager = new JDBCPatientManager(cm);
        // Component Injection: Passing the manager to the UI for user interaction
        PatientUI ui = new PatientUI(manager);
        // Launch the specialized menu for patient registration, search, and updates
        ui.showMenu();
    }
}