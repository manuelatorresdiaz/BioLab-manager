package bioLabUI;

import interfaces.OrderTestManager;
import bioLabPOJOS.OrderTest;
import bioLabPOJOS.Test;
import bioLabPOJOS.ReferenceRange;

import jdbc.ConnectionManager;
import jdbc.JDBCOrderTestManager;
import jdbc.JDBCTestManager;
import jdbc.JDBCReferenceRangeManager;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderTestMenuUI {

    private OrderTestManager orderTestManager;
    private JDBCTestManager testManager;             // NEW: To get test names
    private JDBCReferenceRangeManager rangeManager;  // NEW: To get automatic ranges
    private Scanner scanner;

    // Updated Constructor to receive all 3 managers
    public OrderTestMenuUI(OrderTestManager orderTestManager, JDBCTestManager testManager, JDBCReferenceRangeManager rangeManager) {
        this.orderTestManager = orderTestManager;
        this.testManager = testManager;
        this.rangeManager = rangeManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option = -1;

        do {
            System.out.println("\n=== ORDER TEST MENU ===");
            System.out.println("1. Add order test");
            System.out.println("2. Show all order tests");
            System.out.println("3. Find order test by IDs");
            System.out.println("4. Update order test");
            System.out.println("5. Delete order test");
            System.out.println("6. View test history");
            System.out.println("7. View most requested tests");
            System.out.println("8. View average test value");
            System.out.println("9. View abnormal results (AUTOMATIC)");
            
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1: addOrderTest(); break;
                    case 2: showAllOrderTests(); break;
                    case 3: findOrderTest(); break;
                    case 4: updateOrderTest(); break;
                    case 5: deleteOrderTest(); break;
                    case 6: viewTestHistory(); break;
                    case 7: viewMostRequestedTests(); break;
                    case 8: viewAverageTestValue(); break;
                    case 9: viewAbnormalResults(); break;
                    case 0: 
                        System.out.println("Exiting system..."); 
                        break;
                    default: 
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

        } while (option != 0);
    }

    // ==========================================================
    // CRUD METHODS
    // ==========================================================
    private void addOrderTest() {
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());
        System.out.print("Result value: ");
        double resultValue = Double.parseDouble(scanner.nextLine());
        System.out.print("Result status: ");
        String resultStatus = scanner.nextLine();

        Date resultDate = new Date();
        OrderTest orderTest = new OrderTest(orderId, testId, resultValue, resultDate, resultStatus);
        orderTestManager.addOrderTest(orderTest);
    }

    private void showAllOrderTests() {
        List<OrderTest> orderTests = orderTestManager.getAllOrderTests();
        if (orderTests.isEmpty()) {
            System.out.println("No order tests found.");
            return;
        }
        for (OrderTest ot : orderTests) {
            System.out.println("Order ID: " + ot.getOrderId() + " | Test ID: " + ot.getTestId() + 
                               " | Result: " + ot.getResultValue() + " | Status: " + ot.getResultStatus());
        }
    }

    private void findOrderTest() {
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());

        OrderTest ot = orderTestManager.getOrderTestById(orderId, testId);
        if (ot == null) {
            System.out.println("OrderTest not found.");
        } else {
            System.out.println("Result value: " + ot.getResultValue() + " | Date: " + ot.getResultDate());
        }
    }

    private void updateOrderTest() {
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());
        System.out.print("New result value: ");
        double resultValue = Double.parseDouble(scanner.nextLine());
        System.out.print("New result status: ");
        String resultStatus = scanner.nextLine();

        Date resultDate = new Date();
        OrderTest orderTest = new OrderTest(orderId, testId, resultValue, resultDate, resultStatus);
        orderTestManager.updateOrderTest(orderTest);
    }

    private void deleteOrderTest() {
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());
        orderTestManager.deleteOrderTest(orderId, testId);
    }

    // ==========================================================
    // 📊 RESULTS & ANALYTICS INTEGRATED METHODS
    // ==========================================================

    private void viewTestHistory() {
        System.out.print("Enter Test ID to view history: ");
        int testId = Integer.parseInt(scanner.nextLine());
        
        // INTEGRATION: Getting real test name
        Test test = testManager.getTestById(testId);
        String testName = (test != null) ? test.getTestName() : "Unknown Test";
        
        List<OrderTest> history = orderTestManager.getTestHistory(testId);
        if (history.isEmpty()) {
            System.out.println("No history found for Test: " + testName);
        } else {
            System.out.println("\n--- HISTORY FOR " + testName.toUpperCase() + " (ID: " + testId + ") ---");
            for (OrderTest ot : history) {
                System.out.println("Date: " + ot.getResultDate() + " | Order ID: " + ot.getOrderId() + 
                                   " | Result: " + ot.getResultValue() + " (" + ot.getResultStatus() + ")");
            }
        }
    }

    private void viewMostRequestedTests() {
        List<Integer> topTests = orderTestManager.getMostRequestedTests();
        if (topTests.isEmpty()) {
            System.out.println("No tests found in the database.");
        } else {
            System.out.println("\n--- MOST REQUESTED TESTS (RANKING) ---");
            int rank = 1;
            for (Integer id : topTests) {
                // INTEGRATION: Getting real test name for the ranking
                Test test = testManager.getTestById(id);
                String testName = (test != null) ? test.getTestName() : "Unknown Test";
                System.out.println("#" + rank + " - " + testName + " (ID: " + id + ")");
                rank++;
            }
        }
    }

    private void viewAverageTestValue() {
        System.out.print("Enter Test ID to calculate average: ");
        int testId = Integer.parseInt(scanner.nextLine());
        
        // INTEGRATION: Getting real test name
        Test test = testManager.getTestById(testId);
        String testName = (test != null) ? test.getTestName() : "Unknown Test";
        
        double average = orderTestManager.getAverageTestValue(testId);
        System.out.printf("Average value for %s (ID: %d) is: %.2f\n", testName, testId, average);
    }

    private void viewAbnormalResults() {
        System.out.print("Enter Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());
        
        Test test = testManager.getTestById(testId);
        String testName = (test != null) ? test.getTestName() : "Unknown Test";

        // INTEGRATION: Getting ranges automatically from the database
        ReferenceRange range = rangeManager.getReferenceRangeById(testId);
        
        if (range == null) {
            System.out.println("ERROR: No Reference Range found in the database for Test ID " + testId);
            System.out.println("Please add a reference range first to calculate abnormalities.");
            return;
        }

        double min = range.getMinValue();
        double max = range.getMaxValue();
        
        System.out.println("\nApplying automatic healthy range for " + testName + ": [" + min + " - " + max + "]");
        
        List<OrderTest> abnormal = orderTestManager.getAbnormalResults(testId, min, max);
        if (abnormal.isEmpty()) {
            System.out.println("All results are within the normal range. No abnormalities detected.");
        } else {
            System.out.println("--- ABNORMAL RESULTS DETECTED ---");
            for (OrderTest ot : abnormal) {
                System.out.println("Order ID: " + ot.getOrderId() + " | Abnormal Value: " + ot.getResultValue());
            }
        }
    }

    // ==========================================================
    // 🚀 INTEGRATED BOOTSTRAP MOTOR (MAIN)
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("Starting Laboratory Analytics System...");

        // 1. Connect to Database
        ConnectionManager cm = new ConnectionManager();
        
        // 2. Prepare ALL the managers needed for integration
        JDBCOrderTestManager orderManager = new JDBCOrderTestManager(cm);
        JDBCTestManager testManager = new JDBCTestManager(cm);
        JDBCReferenceRangeManager rangeManager = new JDBCReferenceRangeManager(cm);
        
        // 3. Prepare the UI and pass all managers
        OrderTestMenuUI menu = new OrderTestMenuUI(orderManager, testManager, rangeManager);
        
        // 4. Show the menu!
        menu.showMenu();
    }
}
        		