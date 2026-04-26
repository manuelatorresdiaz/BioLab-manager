package main;

import jdbc.ConnectionManager;
import jdbc.JDBCOrderTestManager;
import jdbc.JDBCTestManager;
import jdbc.JDBCReferenceRangeManager;
import bioLabUI.OrderTestMenuUI;

public class TestOrderTest {

    public static void main(String[] args) {
        System.out.println("Starting Laboratory Analytics System...");

        // 1. Initialize connection
        ConnectionManager cm = new ConnectionManager();
        
        // 2. Initialize all necessary managers
        JDBCOrderTestManager orderManager = new JDBCOrderTestManager(cm);
        JDBCTestManager testManager = new JDBCTestManager(cm);
        JDBCReferenceRangeManager rangeManager = new JDBCReferenceRangeManager(cm);
        
        // 3. Inject managers into the UI
        OrderTestMenuUI menu = new OrderTestMenuUI(orderManager, testManager, rangeManager);
        
        // 4. Display the menu
        menu.showMenu();
    }
}