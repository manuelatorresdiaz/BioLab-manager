package main;

import interfaces.PhysicianManager;
import jdbc.ConnectionManager;
import jdbc.JDBCPhysicianManager;
import bioLabUI.PhysicianMenuUI;
/**
 * Isolated Test Launcher for the Physician Directory Module.
 * This class ensures that the medical staff database is correctly 
 * initialized and that the UI can handle physician-specific attributes 
 * like clinical specialties.
 */
public class TestPhysician {
    public static void main(String[] args) {
    	// Initialize the shared database connection manager
        ConnectionManager cm = new ConnectionManager();
        // Dependency Injection: Utilizing the PhysicianManager interface 
        // to decouple the UI from the specific JDBC implementation.
        PhysicianManager manager = new JDBCPhysicianManager(cm);
        // Component Mapping: Linking the manager to the Physician-specific UI
        PhysicianMenuUI ui = new PhysicianMenuUI(manager);
        // Launch the menu for physician registration, specialty filtering, and directory updates
        ui.showMenu();
    }
}