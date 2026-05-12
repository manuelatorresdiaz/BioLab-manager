package main;

import interfaces.TestManager;
import jdbc.ConnectionManager;
import jdbc.JDBCTestManager;
import bioLabUI.TestMenuUI;
/**
 * Isolated Test Launcher for the Test Catalog Module.
 * This class ensures that the laboratory's master list of medical tests 
 * is correctly managed, including names, types, and units of measure.
 */
public class TestTest {

    public static void main(String[] args) {
    	// 1. Initialize the centralized database connection manager
        ConnectionManager cm = new ConnectionManager();
        // 2. Instantiate the manager via the TestManager interface.
        // This abstraction allows for swapping persistence implementations 
        // without affecting the UI logic.
        TestManager manager = new JDBCTestManager(cm);
        // 3. Inject the manager into the specialized Test Catalog UI
        TestMenuUI ui = new TestMenuUI(manager);
        // 4. Launch the menu to add, edit, or delete items from the test menu
        ui.showMenu();
    }
}