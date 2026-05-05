package bioLabUI;

import interfaces.ReferenceRangeManager;
import bioLabPOJOS.ReferenceRange;

import java.util.List;
import java.util.Scanner;

/**
 * UI component for defining clinical reference thresholds.
 * These ranges are used by the system to automatically validate patient test results.
 */

public class ReferenceRangeMenuUI {

    private ReferenceRangeManager referenceRangeManager;
    private Scanner scanner;

    /**
     * Constructor injecting the logic layer for reference ranges.
     * 
     * @param referenceRangeManager Logic handler for clinical thresholds.
     */
    
    public ReferenceRangeMenuUI(ReferenceRangeManager referenceRangeManager) {
        this.referenceRangeManager = referenceRangeManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the administrative menu for clinical range configuration.
     */
    
    public void showMenu() {
        int option;

        do {
            System.out.println("\n=== REFERENCE RANGE MENU ===");
            System.out.println("1. Add reference range");
            System.out.println("2. Show all reference ranges");
            System.out.println("3. Find reference range by ID");
            System.out.println("4. Update reference range");
            System.out.println("5. Delete reference range");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addReferenceRange();
                    break;
                case 2:
                    showAllReferenceRanges();
                    break;
                case 3:
                    findReferenceRange();
                    break;
                case 4:
                    updateReferenceRange();
                    break;
                case 5:
                    deleteReferenceRange();
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
     * Registers a new set of clinical thresholds (Normal and Critical).
     */
    
    private void addReferenceRange() {
        // Creates a new reference range
        System.out.print("Min value: ");
        double minValue = Double.parseDouble(scanner.nextLine());

        System.out.print("Max value: ");
        double maxValue = Double.parseDouble(scanner.nextLine());

        System.out.print("Critical min: ");
        double criticalMin = Double.parseDouble(scanner.nextLine());

        System.out.print("Critical max: ");
        double criticalMax = Double.parseDouble(scanner.nextLine());

        System.out.print("Units: ");
        String units = scanner.nextLine();

     // ID 0 for auto-increment in DB
        
        ReferenceRange referenceRange = new ReferenceRange(
                0, minValue, maxValue, criticalMin, criticalMax, units
        );

        referenceRangeManager.addReferenceRange(referenceRange);
    }

    /**
     * Displays all configured clinical ranges.
     */
    
    private void showAllReferenceRanges() {
        List<ReferenceRange> referenceRanges = referenceRangeManager.getAllReferenceRanges();

        if (referenceRanges.isEmpty()) {
            System.out.println("No reference ranges found.");
            return;
        }

        for (ReferenceRange rr : referenceRanges) {
            System.out.println("Reference ID: " + rr.getReferenceId());
            System.out.println("Min value: " + rr.getMinValue());
            System.out.println("Max value: " + rr.getMaxValue());
            System.out.println("Critical min: " + rr.getCriticalMin());
            System.out.println("Critical max: " + rr.getCriticalMax());
            System.out.println("Units: " + rr.getUnits());
            System.out.println("--------------------------");
        }
    }

    /**
     * Retrieves specific range data by ID.
     */
    
    private void findReferenceRange() {
        // Finds one reference range by ID
        System.out.print("Reference ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        ReferenceRange rr = referenceRangeManager.getReferenceRangeById(id);

        if (rr == null) {
            System.out.println("Reference range not found.");
        } else {
            System.out.println("Reference ID: " + rr.getReferenceId());
            System.out.println("Min value: " + rr.getMinValue());
            System.out.println("Max value: " + rr.getMaxValue());
            System.out.println("Critical min: " + rr.getCriticalMin());
            System.out.println("Critical max: " + rr.getCriticalMax());
            System.out.println("Units: " + rr.getUnits());
        }
    }

    /**
     * Updates an existing clinical threshold set.
     */
    
    private void updateReferenceRange() {
        // Updates a stored reference range
        System.out.print("Reference ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New min value: ");
        double minValue = Double.parseDouble(scanner.nextLine());

        System.out.print("New max value: ");
        double maxValue = Double.parseDouble(scanner.nextLine());

        System.out.print("New critical min: ");
        double criticalMin = Double.parseDouble(scanner.nextLine());

        System.out.print("New critical max: ");
        double criticalMax = Double.parseDouble(scanner.nextLine());

        System.out.print("New units: ");
        String units = scanner.nextLine();

        ReferenceRange referenceRange = new ReferenceRange(
                id, minValue, maxValue, criticalMin, criticalMax, units
        );

        referenceRangeManager.updateReferenceRange(referenceRange);
    }

    /**
     * Removes a clinical threshold set.
     */
    
    private void deleteReferenceRange() {
        // Deletes a reference range by ID
        System.out.print("Reference ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        referenceRangeManager.deleteReferenceRange(id);
    }
}