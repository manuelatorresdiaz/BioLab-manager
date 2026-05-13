package bioLabUI;

import interfaces.TestManager;
import bioLabPOJOS.Test;

import java.util.List;
import java.util.Scanner;

/**
 * UI component for managing the Laboratory Test Catalog.
 * Defines the types of analysis the laboratory is capable of performing.
 */

public class TestMenuUI {

    private TestManager testManager;
    private Scanner scanner;

    /**
     * Constructor injecting the TestManager implementation.
     * 
     * @param testManager Logic handler for the test catalog.
     */
    
    public TestMenuUI(TestManager testManager) {
        this.testManager = testManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the administrative menu for test catalog management.
     */
    
    public void showMenu() {
        int option;

        do {
            System.out.println("\n=== TEST MENU ===");
            System.out.println("1. Add test");
            System.out.println("2. Show all tests");
            System.out.println("3. Find test by ID");
            System.out.println("4. Update test");
            System.out.println("5. Delete test");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addTest();
                    break;
                case 2:
                    showAllTests();
                    break;
                case 3:
                    findTest();
                    break;
                case 4:
                    updateTest();
                    break;
                case 5:
                    deleteTest();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    /**
     * Registers a new analysis type in the laboratory system.
     */
    
    private void addTest() {
        // Creates a new laboratory test
        System.out.print("Test name: ");
        String testName = scanner.nextLine();

        System.out.print("Unit: ");
        String unit = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        Test test = new Test(0, testName, unit, description);
        testManager.addTest(test);
    }

    /**
     * Lists all test types currently supported by the lab.
     */
    
    private void showAllTests() {
        List<Test> tests = testManager.getAllTests();

        if (tests.isEmpty()) {
            System.out.println("No tests found.");
            return;
        }

        for (Test t : tests) {
            System.out.println("Test ID: " + t.getTestId());
            System.out.println("Test name: " + t.getTestName());
            System.out.println("Unit: " + t.getUnit());
            System.out.println("Description: " + t.getDescription());
            System.out.println("--------------------------");
        }
    }

    /**
     * Searches for a specific test definition by ID.
     */
    
    private void findTest() {
        // Finds one test using its ID
        System.out.print("Test ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Test t = testManager.getTestById(id);

        if (t == null) {
            System.out.println("Test not found.");
        } else {
            System.out.println("Test ID: " + t.getTestId());
            System.out.println("Test name: " + t.getTestName());
            System.out.println("Unit: " + t.getUnit());
            System.out.println("Description: " + t.getDescription());
        }
    }

    /**
     * Modifies the metadata of an existing test type.
     */
    
    private void updateTest() {
        // Updates a test already stored in the database
        System.out.print("Test ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New test name: ");
        String testName = scanner.nextLine();

        System.out.print("New unit: ");
        String unit = scanner.nextLine();

        System.out.print("New description: ");
        String description = scanner.nextLine();

        Test test = new Test(id, testName, unit, description);
        testManager.updateTest(test);
    }

    /**
     * Removes a test type from the catalog.
     */
    
    private void deleteTest() {
        // Deletes a test by its ID
        System.out.print("Test ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        testManager.deleteTest(id);
    }
}