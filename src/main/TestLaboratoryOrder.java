package main;

import interfaces.LaboratoryOrderManager;
import jdbc.ConnectionManager;
import jdbc.JDBCLaboratoryOrderManager;
import bioLabUI.OrderMenuUI;
/**
 * Isolated Test Launcher for the Laboratory Order Module.
 * This class bypasses the full system login to allow developers to 
 * directly test JDBC CRUD operations and the Order UI.
 */
public class TestLaboratoryOrder {

    public static void main(String[] args) {
    	// Initialize the database connection provider
        ConnectionManager cm = new ConnectionManager();
        // Instantiate the specific manager using the LaboratoryOrderManager interface
        // This follows the Dependency Inversion Principle
        LaboratoryOrderManager manager = new JDBCLaboratoryOrderManager(cm);
        // Link the manager to the User Interface component
        OrderMenuUI ui = new OrderMenuUI(manager);
        // Launch the specialized menu for Orders
        ui.showMenu();
    }
}