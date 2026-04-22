package ui;

import java.util.List;
import java.util.Scanner;

import model.Patient;
import service.PatientService;

public class PatientUI {

    private PatientService patientService;
    private Scanner scanner;

    public PatientUI(PatientService patientService) {
        this.patientService = patientService;
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
                    System.out.println("Exiting patient menu...");
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

        System.out.print("Date of birth: ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        Patient patient = new Patient(0, firstName, lastName, dateOfBirth, gender, phone, address);
        patientService.addPatient(patient);

        System.out.println("Patient added successfully.");
    }

    private void showAllPatients() {
        List<Patient> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        for (Patient patient : patients) {
            System.out.println("ID: " + patient.getPatientId());
            System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
            System.out.println("Date of birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Address: " + patient.getAddress());
            System.out.println("--------------------------");
        }
    }

    private void findPatientById() {
        System.out.print("Enter patient ID: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        Patient patient = patientService.getPatientById(patientId);

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
        int patientId = Integer.parseInt(scanner.nextLine());

        Patient existingPatient = patientService.getPatientById(patientId);

        if (existingPatient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("New first name: ");
        String firstName = scanner.nextLine();

        System.out.print("New last name: ");
        String lastName = scanner.nextLine();

        System.out.print("New date of birth: ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("New gender: ");
        String gender = scanner.nextLine();

        System.out.print("New phone: ");
        String phone = scanner.nextLine();

        System.out.print("New address: ");
        String address = scanner.nextLine();

        Patient updatedPatient = new Patient(patientId, firstName, lastName, dateOfBirth, gender, phone, address);
        patientService.updatePatient(updatedPatient);

        System.out.println("Patient updated successfully.");
    }

    private void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        int patientId = Integer.parseInt(scanner.nextLine());

        Patient existingPatient = patientService.getPatientById(patientId);

        if (existingPatient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patientService.deletePatient(patientId);
        System.out.println("Patient deleted successfully.");
    }
}