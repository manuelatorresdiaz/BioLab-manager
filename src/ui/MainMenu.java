package ui;

import java.util.Scanner;

public class MainMenu {

    private Scanner sc = new Scanner(System.in);

    public void start() {

        int option;

        do {

            System.out.println("\n===== BIOLAB MANAGER =====");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Physicians");
            System.out.println("3. Manage Orders");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            option = sc.nextInt();

            switch(option) {

                case 1:
                    System.out.println("Opening Patients...");
                    new PatientUI();
                    break;

                case 2:
                    System.out.println("Opening Physicians...");
                    new PhysicianUI();
                    break;

                case 3:
                    System.out.println("Opening Orders...");
                    new OrderUI();
                    break;

                case 0:
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while(option != 0);
    }
}