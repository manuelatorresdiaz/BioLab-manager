package bioLabUI;

import interfaces.OrderTestManager;
import bioLabPOJOS.OrderTest;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderTestMenuUI {

    private OrderTestManager orderTestManager;
    private Scanner scanner;

    public OrderTestMenuUI(OrderTestManager orderTestManager) {
        this.orderTestManager = orderTestManager;
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
            // --- TUS NUEVAS OPCIONES DE ANALÍTICA ---
            System.out.println("6. View test history");
            System.out.println("7. View most requested tests");
            System.out.println("8. View average test value");
            System.out.println("9. View abnormal results");
            
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
                    // --- LLAMADAS A TUS MÉTODOS ---
                    case 6: viewTestHistory(); break;
                    case 7: viewMostRequestedTests(); break;
                    case 8: viewAverageTestValue(); break;
                    case 9: viewAbnormalResults(); break;
                    case 0: 
                        System.out.println("Exiting..."); 
                        break;
                    default: 
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

        } while (option != 0);
    }

    // ==========================================================
    // MÉTODOS CRUD (De tu equipo)
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
    // 📊 TUS MÉTODOS DE ANALÍTICA (RESULTS & ANALYTICS)
    // ==========================================================

    private void viewTestHistory() {
        System.out.print("Enter Test ID to view history: ");
        int testId = Integer.parseInt(scanner.nextLine());
        
        List<OrderTest> history = orderTestManager.getTestHistory(testId);
        if (history.isEmpty()) {
            System.out.println("No history found for Test ID " + testId);
        } else {
            System.out.println("\n--- HISTORY FOR TEST ID " + testId + " ---");
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
                System.out.println("#" + rank + " - Test ID: " + id);
                rank++;
            }
        }
    }

    private void viewAverageTestValue() {
        System.out.print("Enter Test ID to calculate average: ");
        int testId = Integer.parseInt(scanner.nextLine());
        
        double average = orderTestManager.getAverageTestValue(testId);
        System.out.printf("Average value for Test ID %d is: %.2f\n", testId, average);
    }

    private void viewAbnormalResults() {
        System.out.print("Enter Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Normal Minimum Value: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Normal Maximum Value: ");
        double max = Double.parseDouble(scanner.nextLine());
        
        List<OrderTest> abnormal = orderTestManager.getAbnormalResults(testId, min, max);
        if (abnormal.isEmpty()) {
            System.out.println("All results are within the normal range.");
        } else {
            System.out.println("\n--- ABNORMAL RESULTS DETECTED ---");
            for (OrderTest ot : abnormal) {
                System.out.println("Order ID: " + ot.getOrderId() + " | Abnormal Value: " + ot.getResultValue());
            }
        }
    }
 // ==========================================================
    // 🚀 MOTOR DE ARRANQUE INTEGRADO
    // ==========================================================
    public static void main(String[] args) {
        System.out.println("Arrancando el sistema...");

        // 1. Conectamos a la base de datos
        jdbc.ConnectionManager cm = new jdbc.ConnectionManager();
        
        // 2. Preparamos tu lógica
        jdbc.JDBCOrderTestManager manager = new jdbc.JDBCOrderTestManager(cm);
        
        // 3. Preparamos la pantalla visual y le pasamos tu lógica
        OrderTestMenuUI menu = new OrderTestMenuUI(manager);
        
        // 4. ¡Mostramos el menú en la consola!
        menu.showMenu();
    }
}