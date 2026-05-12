package main;

import jdbc.ConnectionManager;
import jdbc.JDBCOrderTestManager;
import jdbc.JDBCTestManager;
import jdbc.JDBCReferenceRangeManager;
import bioLabUI.OrderTestMenuUI;
/**
 * Isolated Test Launcher for Laboratory Analytics and Result Management.
 * This class validates the integration between results, test definitions, 
 * and reference ranges.
 */
public class TestOrderTest {

    public static void main(String[] args) {
        System.out.println("Starting Laboratory Analytics System...");
        // 1. Initialize database connection provider
        ConnectionManager cm = new ConnectionManager();
        
        // 2. Initialize all necessary specialized managers
        // We instantiate multiple managers because the OrderTest UI requires 
        // access to Test definitions and Reference Ranges to validate results.
        JDBCOrderTestManager orderManager = new JDBCOrderTestManager(cm);
        JDBCTestManager testManager = new JDBCTestManager(cm);
        JDBCReferenceRangeManager rangeManager = new JDBCReferenceRangeManager(cm);
        
        // 3. Inject managers into the UI (Dependency Injection)
        // This ensures the UI has all the data tools needed for complex clinical logic.
        OrderTestMenuUI menu = new OrderTestMenuUI(orderManager, testManager, rangeManager);
        
        // 4. Launch the specialized analytics menu
        menu.showMenu();
    }
}