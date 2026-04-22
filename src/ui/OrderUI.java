package ui;

import java.util.Scanner;

import model.*;
import repository.*;
import service.*;

public class OrderUI {

    private Scanner sc = new Scanner(System.in);

    private OrderRepository repo = new OrderRepositoryDB();
    private OrderService service = new OrderService(repo);

    public void menu() {

        int option;

        do {
            System.out.println("\n--- ORDER MENU ---");
            System.out.println("1. Add Order");
            System.out.println("2. Delete Order");
            System.out.println("0. Back");

            option = sc.nextInt();

            switch (option) {

                case 1:
                    addOrder();
                    break;

                case 2:
                    deleteOrder();
                    break;
            }

        } while (option != 0);
    }

    private void addOrder() {

        System.out.print("Patient ID: ");
        int patientId = sc.nextInt();

        System.out.print("Physician ID: ");
        int physicianId = sc.nextInt();

        sc.nextLine();

        System.out.print("Order Date: ");
        String date = sc.nextLine();

        System.out.print("Status: ");
        String status = sc.nextLine();

        Patient patient = new Patient();
        patient.setPatientId(patientId);

        Physician physician = new Physician();
        physician.setPhysicianId(physicianId);

        LaboratoryOrder order = new LaboratoryOrder(
                0,
                patient,
                physician,
                date,
                status
        );

        service.addOrder(order);
    }

    private void deleteOrder() {

        System.out.print("Order ID: ");
        int id = sc.nextInt();

        service.deleteOrder(id);
    }
}