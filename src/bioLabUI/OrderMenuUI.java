package bioLabUI;

import interfaces.LaboratoryOrderManager;
import bioLabPOJOS.LaboratoryOrder;
import bioLabPOJOS.Patient;
import bioLabPOJOS.Physician;

import java.util.List;
import java.util.Scanner;

/**
 * Provides a text-based User Interface for managing Laboratory Orders.
 * It handles the CRUD operations (Create, Read, Update, Delete) and 
 * specialized filters like pending orders.
 */

public class OrderMenuUI {

    private LaboratoryOrderManager orderManager;
    private Scanner scanner;

    /**
     * Constructor using Dependency Injection.
     * 
     * @param orderManager The implementation of the order management logic.
     */
    
    public OrderMenuUI(LaboratoryOrderManager orderManager) {
        this.orderManager = orderManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main loop for the Laboratory Order menu.
     */
    
    public void showMenu() {
        int option;

        do {
            System.out.println("\n=== LAB ORDER MENU ===");
            System.out.println("1. Add order");
            System.out.println("2. Show all orders");
            System.out.println("3. Find order by ID");
            System.out.println("4. Update order");
            System.out.println("5. Delete order");
            System.out.println("6. View patient orders");
            System.out.println("7. View pending orders");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

         // Using parseInt to avoid common Scanner buffer issues
            
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1: addOrder(); break;
                case 2: showAllOrders(); break;
                case 3: findOrder(); break;
                case 4: updateOrder(); break;
                case 5: deleteOrder(); break;
                case 6:viewPatientOrders(); break;
                case 7:viewPendingOrders(); break;
                case 0: System.out.println("Bye"); break;
                default: System.out.println("Invalid");
            }

        } while (option != 0);
    }

    /**
     * Collects data to create a new laboratory order.
     */
    
    private void addOrder() {
        System.out.print("Patient ID: ");
        int pId = Integer.parseInt(scanner.nextLine());

        System.out.print("Physician ID: ");
        int phId = Integer.parseInt(scanner.nextLine());

        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

     // Creates dummy POJO wrappers to pass IDs to the manager
        
        Patient p = new Patient(pId,"","","","","","");
        Physician ph = new Physician(phId,"","","","","");

        LaboratoryOrder order = new LaboratoryOrder(0,p,ph,date,status);

        orderManager.addOrder(order);
    }

    /**
     * Retrieves and displays all orders in the system.
     */
    
    private void showAllOrders() {
        List<LaboratoryOrder> orders = orderManager.getAllOrders();

        for (LaboratoryOrder o : orders) {
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Patient ID: " + o.getPatient().getPatientId());
            System.out.println("Physician ID: " + o.getPhysician().getPhysicianId());
            System.out.println("Date: " + o.getOrderDate());
            System.out.println("Status: " + o.getStatus());
            System.out.println("------------------");
        }
    }

    /**
     * Searches for a specific order by its unique ID.
     */
    
    private void findOrder() {
        System.out.print("Order ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        LaboratoryOrder o = orderManager.getOrderById(id);

        if (o == null) {
            System.out.println("Not found");
        } else {
            System.out.println("Order ID: " + o.getOrderId());
        }
    }

    /**
     * Updates an existing order's information.
     */
    
    private void updateOrder() {
        System.out.print("Order ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New Patient ID: ");
        int pId = Integer.parseInt(scanner.nextLine());

        System.out.print("New Physician ID: ");
        int phId = Integer.parseInt(scanner.nextLine());

        System.out.print("New Date: ");
        String date = scanner.nextLine();

        System.out.print("New Status: ");
        String status = scanner.nextLine();

        Patient p = new Patient(pId,"","","","","","");
        Physician ph = new Physician(phId,"","","","","");

        LaboratoryOrder order = new LaboratoryOrder(id,p,ph,date,status);

        orderManager.updateOrder(order);
    }

    /**
     * Removes an order from the database.
     */
    
    private void deleteOrder() {
        System.out.print("Order ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        orderManager.deleteOrder(id);
    }
    
    /**
     * Displays all laboratory orders associated with a specific patient.
     */
    
    private void viewPatientOrders() {

        // Displays all orders for a given patient
        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        List<LaboratoryOrder> orders = orderManager.getOrdersByPatientId(patientId);

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (LaboratoryOrder o : orders) {
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Date: " + o.getOrderDate());
            System.out.println("Status: " + o.getStatus());
            System.out.println("------------------");
        }
    }
    
    /**
     * Displays all orders that currently have a 'Pending' status.
     */
    
    private void viewPendingOrders() {

        // Displays all pending orders
        List<LaboratoryOrder> orders = orderManager.getPendingOrders();

        if (orders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        for (LaboratoryOrder o : orders) {
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Patient ID: " + o.getPatient().getPatientId());
            System.out.println("Date: " + o.getOrderDate());
            System.out.println("------------------");
        }
    }
}