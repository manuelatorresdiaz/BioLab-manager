package ui;

import interfaces.PatientManager;
import model.Patient;

import java.util.List;
import java.util.Scanner;

public class PatientUI {

    private PatientManager patientManager;
    private Scanner scanner;

    public PatientUI(PatientManager patientManager) {
        this.patientManager = patientManager;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;

        do {
            System.out.println("\n=== PATIENT MENU ===");
            System.out.println("1. Add patient");
            System.out.println("2. Show all patients");
            System.out.println("3. Find patient by ID");
            System.out.println("4. Update patient");
            System.out.println("5. Delete patient");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    showAllPatients();
                    break;
                case 3:
                    findPatientById();
                    break;
                case 4:
                    updatePatient();
                    break;
                case 5:
                    deletePatient();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void addPatient() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Date of birth (yyyy-mm-dd): ");
        String dob = scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        Patient patient = new Patient(0, firstName, lastName, dob, gender, phone, address);
        patientManager.addPatient(patient);
    }

    private void showAllPatients() {
        List<Patient> patients = patientManager.getAllPatients();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        for (Patient p : patients) {
            System.out.println("ID: " + p.getPatientId());
            System.out.println("Name: " + p.getFirstName() + " " + p.getLastName());
            System.out.println("Date of birth: " + p.getDateOfBirth());
            System.out.println("Gender: " + p.getGender());
            System.out.println("Phone: " + p.getPhone());
            System.out.println("Address: " + p.getAddress());
            System.out.println("--------------------------");
        }
    }

    private void findPatientById() {
        System.out.print("Enter patient ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Patient patient = patientManager.getPatientById(id);

        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println("ID: " + patient.getPatientId());
            System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
            System.out.println("Date of birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Address: " + patient.getAddress());
        }
    }

    private void updatePatient() {
        System.out.print("Enter patient ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New first name: ");
        String firstName = scanner.nextLine();

        System.out.print("New last name: ");
        String lastName = scanner.nextLine();

        System.out.print("New date of birth (yyyy-mm-dd): ");
        String dob = scanner.nextLine();

        System.out.print("New gender: ");
        String gender = scanner.nextLine();

        System.out.print("New phone: ");
        String phone = scanner.nextLine();

        System.out.print("New address: ");
        String address = scanner.nextLine();

        Patient patient = new Patient(id, firstName, lastName, dob, gender, phone, address);
        patientManager.updatePatient(patient);
    }

    private void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        patientManager.deletePatient(id);
    }
}