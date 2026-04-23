package bioLabUI;

import interfaces.LaboratoryOrderManager;
import bioLabPOJOS.LaboratoryOrder;
import bioLabPOJOS.Patient;
import bioLabPOJOS.Physician;

import java.util.List;
import java.util.Scanner;

public class OrderMenuUI {

    private LaboratoryOrderManager orderManager;
    private Scanner scanner;

    public OrderMenuUI(LaboratoryOrderManager orderManager) {
        this.orderManager = orderManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;

        do {
            System.out.println("\n=== LAB ORDER MENU ===");
            System.out.println("1. Add order");
            System.out.println("2. Show all orders");
            System.out.println("3. Find order by ID");
            System.out.println("4. Update order");
            System.out.println("5. Delete order");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1: addOrder(); break;
                case 2: showAllOrders(); break;
                case 3: findOrder(); break;
                case 4: updateOrder(); break;
                case 5: deleteOrder(); break;
                case 0: System.out.println("Bye"); break;
                default: System.out.println("Invalid");
            }

        } while (option != 0);
    }

    private void addOrder() {
        System.out.print("Patient ID: ");
        int pId = Integer.parseInt(scanner.nextLine());

        System.out.print("Physician ID: ");
        int phId = Integer.parseInt(scanner.nextLine());

        System.out.print("Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        Patient p = new Patient(pId,"","","","","","");
        Physician ph = new Physician(phId,"","","","","");

        LaboratoryOrder order = new LaboratoryOrder(0,p,ph,date,status);

        orderManager.addOrder(order);
    }

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

    private void deleteOrder() {
        System.out.print("Order ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        orderManager.deleteOrder(id);
    }
}