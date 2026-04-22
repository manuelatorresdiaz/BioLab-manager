package ui;

import interfaces.PhysicianManager;
import model.Physician;

import java.util.List;
import java.util.Scanner;

public class PhysicianUI {

    private PhysicianManager physicianManager;
    private Scanner scanner;

    public PhysicianUI(PhysicianManager physicianManager) {
        this.physicianManager = physicianManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;

        do {
            System.out.println("\n=== PHYSICIAN MENU ===");
            System.out.println("1. Add physician");
            System.out.println("2. Show all physicians");
            System.out.println("3. Find physician by ID");
            System.out.println("4. Update physician");
            System.out.println("5. Delete physician");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addPhysician();
                    break;
                case 2:
                    showAllPhysicians();
                    break;
                case 3:
                    findPhysicianById();
                    break;
                case 4:
                    updatePhysician();
                    break;
                case 5:
                    deletePhysician();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void addPhysician() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Physician physician = new Physician(0, firstName, lastName, specialty, phone, email);
        physicianManager.addPhysician(physician);
    }

    private void showAllPhysicians() {
        List<Physician> physicians = physicianManager.getAllPhysicians();

        if (physicians.isEmpty()) {
            System.out.println("No physicians found.");
            return;
        }

        for (Physician p : physicians) {
            System.out.println("ID: " + p.getPhysicianId());
            System.out.println("Name: " + p.getFirstName() + " " + p.getLastName());
            System.out.println("Specialty: " + p.getSpecialty());
            System.out.println("Phone: " + p.getPhone());
            System.out.println("Email: " + p.getEmail());
            System.out.println("--------------------------");
        }
    }

    private void findPhysicianById() {
        System.out.print("Enter physician ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Physician physician = physicianManager.getPhysicianById(id);

        if (physician == null) {
            System.out.println("Physician not found.");
        } else {
            System.out.println("ID: " + physician.getPhysicianId());
            System.out.println("Name: " + physician.getFirstName() + " " + physician.getLastName());
            System.out.println("Specialty: " + physician.getSpecialty());
            System.out.println("Phone: " + physician.getPhone());
            System.out.println("Email: " + physician.getEmail());
        }
    }

    private void updatePhysician() {
        System.out.print("Enter physician ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New first name: ");
        String firstName = scanner.nextLine();

        System.out.print("New last name: ");
        String lastName = scanner.nextLine();

        System.out.print("New specialty: ");
        String specialty = scanner.nextLine();

        System.out.print("New phone: ");
        String phone = scanner.nextLine();

        System.out.print("New email: ");
        String email = scanner.nextLine();

        Physician physician = new Physician(id, firstName, lastName, specialty, phone, email);
        physicianManager.updatePhysician(physician);
    }

    private void deletePhysician() {
        System.out.print("Enter physician ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        physicianManager.deletePhysician(id);
    }
}