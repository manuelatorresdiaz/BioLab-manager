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
        int option;

        do {
            System.out.println("\n=== ORDER TEST MENU ===");
            System.out.println("1. Add order test");
            System.out.println("2. Show all order tests");
            System.out.println("3. Find order test by IDs");
            System.out.println("4. Update order test");
            System.out.println("5. Delete order test");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addOrderTest();
                    break;
                case 2:
                    showAllOrderTests();
                    break;
                case 3:
                    findOrderTest();
                    break;
                case 4:
                    updateOrderTest();
                    break;
                case 5:
                    deleteOrderTest();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void addOrderTest() {
        // Creates a new result linked to an order and a test
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());

        System.out.print("Result value: ");
        double resultValue = Double.parseDouble(scanner.nextLine());

        System.out.print("Result status: ");
        String resultStatus = scanner.nextLine();

        // We use current date for now
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
            System.out.println("Order ID: " + ot.getOrderId());
            System.out.println("Test ID: " + ot.getTestId());
            System.out.println("Result value: " + ot.getResultValue());
            System.out.println("Result date: " + ot.getResultDate());
            System.out.println("Result status: " + ot.getResultStatus());
            System.out.println("--------------------------");
        }
    }

    private void findOrderTest() {
        // Finds one record using the composite key
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());

        OrderTest ot = orderTestManager.getOrderTestById(orderId, testId);

        if (ot == null) {
            System.out.println("OrderTest not found.");
        } else {
            System.out.println("Order ID: " + ot.getOrderId());
            System.out.println("Test ID: " + ot.getTestId());
            System.out.println("Result value: " + ot.getResultValue());
            System.out.println("Result date: " + ot.getResultDate());
            System.out.println("Result status: " + ot.getResultStatus());
        }
    }

    private void updateOrderTest() {
        // Updates the result information
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
        // Deletes a record using orderId and testId
        System.out.print("Order ID: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        System.out.print("Test ID: ");
        int testId = Integer.parseInt(scanner.nextLine());

        orderTestManager.deleteOrderTest(orderId, testId);
    }
}