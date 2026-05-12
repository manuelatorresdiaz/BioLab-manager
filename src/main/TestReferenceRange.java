package main;

import interfaces.ReferenceRangeManager;
import jdbc.ConnectionManager;
import jdbc.JDBCReferenceRangeManager;
import bioLabUI.ReferenceRangeMenuUI;
/**
 * Isolated Test Launcher for the Reference Range Module.
 * This class validates the management of clinical thresholds (min, max, and critical values),
 * which are essential for the automated interpretation of laboratory results.
 */
public class TestReferenceRange {

    public static void main(String[] args) {
    	// 1. Initialize the shared database connection manager
        ConnectionManager cm = new ConnectionManager();
        // 2. Instantiate the manager using the ReferenceRangeManager interface.
        // This ensures the UI remains agnostic of the underlying database implementation.
        ReferenceRangeManager manager = new JDBCReferenceRangeManager(cm);
        // 3. Inject the manager into the specialized Reference Range UI.
        ReferenceRangeMenuUI ui = new ReferenceRangeMenuUI(manager);
        // 4. Launch the menu to define or modify clinical normalities and panic values.
        ui.showMenu();
    }
}