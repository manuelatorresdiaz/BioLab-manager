package main;

import java.util.Scanner;

import bioLabUI.PatientUI;
import bioLabUI.PhysicianMenuUI;
import bioLabUI.OrderMenuUI;
import bioLabUI.TestMenuUI;
import bioLabUI.OrderTestMenuUI;
import XML.XMLManager;
import XML.XMLDataBase;

import jdbc.ConnectionManager;
import jdbc.JDBCPatientManager;
import jdbc.JDBCPhysicianManager;
import jdbc.JDBCLaboratoryOrderManager;
import jdbc.JDBCTestManager;
import jdbc.JDBCOrderTestManager;
import jdbc.JDBCReferenceRangeManager;
/**
 * Main Entry Point for the BioLab LIS.
 * Orchestrates Security (JPA), Clinical Data (JDBC), and Data Portability (XML).
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        XMLManager xmlManager = new XMLManager();
        
     // --- SYSTEM INITIALIZATION (BOOTSTRAPPING) ---
        // We use JPA to check if default users exist, ensuring the system is never locked.
        JPA.JPAUserManager authInit = new JPA.JPAUserManager();
     // Initialize Admin account
        if (authInit.findUserByUsername("admin") == null) {
            bioLabPOJOS.Role role = new bioLabPOJOS.Role("ADMIN");
            bioLabPOJOS.User admin = new bioLabPOJOS.User("admin", "1234");
            admin.setRole(role);
            authInit.createUser(admin);
        }
     // Initialize a Test Patient linked to Patient ID 1 in JDBC
        if (authInit.findUserByUsername("patient1") == null) {
            bioLabPOJOS.Role role = new bioLabPOJOS.Role("PATIENT");

            bioLabPOJOS.User user = new bioLabPOJOS.User("patient1", "1234");
            user.setRole(role);
            user.setPatientId(1); // conecta con Patient ID 1

            authInit.createUser(user);
        }
     // Initialize a Test Physician linked to Physician ID 1 in JDBC
        if (authInit.findUserByUsername("physician1") == null) {
            bioLabPOJOS.Role role = new bioLabPOJOS.Role("PHYSICIAN");

            bioLabPOJOS.User user = new bioLabPOJOS.User("physician1", "1234");
            user.setRole(role);
            user.setPhysicianId(1); // conecta con Physician ID 1

            authInit.createUser(user);
        }
     // Initialize a Lab Technician for operational tasks
        if (authInit.findUserByUsername("tech1") == null) {
            bioLabPOJOS.Role role = new bioLabPOJOS.Role("LAB_TECHNICIAN");
            bioLabPOJOS.User user = new bioLabPOJOS.User("tech1", "1234");
            user.setRole(role);
            authInit.createUser(user);
        }
     // --- MAIN APPLICATION LOOP ---
        while (!exit) {

            System.out.println("\n=================================");
            System.out.println("       BIOLAB MANAGER");
            System.out.println("=================================");
            System.out.println("1. Enter system");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");

            String option = sc.nextLine();

            switch (option) {

                case "1":
                	// --- AUTHENTICATION LAYER (JPA) ---
                    System.out.print("Username: ");
                    String username = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    JPA.JPAUserManager auth = new JPA.JPAUserManager();
                    bioLabPOJOS.User loggedUser = auth.loginAndReturnUser(username, password);

                    if (loggedUser != null) {
                    	// Retrieve the role and identify the user's permissions
                        String roleName = loggedUser.getRole() != null
                                ? loggedUser.getRole().getRoleName()
                                : "UNKNOWN";

                        System.out.println("Logged in as: " + roleName);
                     // --- DATA PERSISTENCE LAYER (JDBC) ---
                        // Once authenticated, we initialize JDBC managers for clinical operations
                        ConnectionManager cm = new ConnectionManager();
                        JDBCPatientManager patientManager = new JDBCPatientManager(cm);
                        JDBCPhysicianManager physicianManager = new JDBCPhysicianManager(cm);
                        JDBCLaboratoryOrderManager labOrderManager = new JDBCLaboratoryOrderManager(cm);
                        JDBCTestManager testManager = new JDBCTestManager(cm);
                        JDBCOrderTestManager orderTestManager = new JDBCOrderTestManager(cm);
                        JDBCReferenceRangeManager rangeManager = new JDBCReferenceRangeManager(cm);

                        boolean logout = false;

                        while (!logout) {
                        	// ROLE-BASED ACCESS CONTROL (RBAC) LOGIC
                            
                            // ADMIN: Full system access and XML/HTML utilities
                            if (roleName.equalsIgnoreCase("ADMIN")) {

                                System.out.println("\n===== ADMIN MENU =====");
                                System.out.println("1. Patients");
                                System.out.println("2. Physicians");
                                System.out.println("3. Laboratory Orders");
                                System.out.println("4. Tests");
                                System.out.println("5. Reports / Analytics");
                               
                                System.out.println("6. Export XML");
                                System.out.println("7. Import XML");
                                System.out.println("8. Generate HTML");
                                System.out.println("0. Logout");
                                System.out.print("Choose option: ");

                                String opt = sc.nextLine();

                                switch (opt) {

                                    case "1":
                                        new PatientUI(patientManager).showMenu();
                                        break;

                                    case "2":
                                        new PhysicianMenuUI(physicianManager).showMenu();
                                        break;

                                    case "3":
                                        new OrderMenuUI(labOrderManager).showMenu();
                                        break;

                                    case "4":
                                        new TestMenuUI(testManager).showMenu();
                                        break;

                                    case "5":
                                        new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu();
                                        break;


                                    case "6":
                                        XMLDataBase db = new XMLDataBase();
                                        xmlManager.databaseToXML(db, "export.xml");
                                        break;

                                    case "7":
                                        xmlManager.xmlToDatabase("export.xml");
                                        break;

                                    case "8":
                                        xmlManager.xmlToHtml("export.xml", "biolab.xsl", "report.html");
                                        break;
                                        
                                    case "0":
                                        logout = true;
                                        break;
                                }
                            }
                         // PATIENT: Limited to personal clinical history (Read-only)
                            else if (roleName.equalsIgnoreCase("PATIENT")) {

                                System.out.println("\n===== PATIENT MENU =====");
                                System.out.println("1. View my orders");
                                System.out.println("2. View my results");
                                System.out.println("3. Logout");
                                System.out.print("Choose option: ");

                                String opt = sc.nextLine();

                                switch (opt) {
	                                case "1":
	                                	// Data filtering: Patient only sees their own data via JDBC
	                                    if (loggedUser.getPatientId() != null) {
	                                        java.util.List<bioLabPOJOS.LaboratoryOrder> myOrders =
	                                                labOrderManager.getOrdersByPatientId(loggedUser.getPatientId());
	
	                                        if (myOrders.isEmpty()) {
	                                            System.out.println("No orders found for this patient.");
	                                        } else {
	                                            System.out.println("--- MY LABORATORY ORDERS ---");
	
	                                            for (bioLabPOJOS.LaboratoryOrder order : myOrders) {
	                                                System.out.println("Order ID: " + order.getOrderId());
	                                                System.out.println("Patient ID: " + order.getPatient().getPatientId());
	                                                System.out.println("Physician ID: " + order.getPhysician().getPhysicianId());
	                                                System.out.println("Date: " + order.getOrderDate());
	                                                System.out.println("Status: " + order.getStatus());
	                                                System.out.println("--------------------------");
	                                            }
	                                        }
	
	                                    } else {
	                                        System.out.println("This user is not linked to a patient.");
	                                    }
	                                    break;
	
	                                case "2":
	                                    if (loggedUser.getPatientId() != null) {
	                                        java.util.List<bioLabPOJOS.OrderTest> myResults =
	                                                orderTestManager.getResultsByPatientId(loggedUser.getPatientId());
	
	                                        if (myResults.isEmpty()) {
	                                            System.out.println("No test results found for this patient.");
	                                        } else {
	                                            System.out.println("--- MY TEST RESULTS ---");
	
	                                            for (bioLabPOJOS.OrderTest result : myResults) {
	                                                System.out.println("Order ID: " + result.getOrderId());
	                                                System.out.println("Test ID: " + result.getTestId());
	                                                System.out.println("Result value: " + result.getResultValue());
	                                                System.out.println("Status: " + result.getResultStatus());
	                                                System.out.println("--------------------------");
	                                            }
	                                        }
	
	                                    } else {
	                                        System.out.println("This user is not linked to a patient.");
	                                    }
	                                    break;

                                    case "3":
                                        logout = true;
                                        break;
                                }
                            }
                         // PHYSICIAN: Access to assigned patients and analytics
                            else if (roleName.equalsIgnoreCase("PHYSICIAN")) {

                                System.out.println("\n===== PHYSICIAN MENU =====");
                                System.out.println("1. Patients");
                                System.out.println("2. Orders");
                                System.out.println("3. Analytics");
                                System.out.println("4. Logout");
                                System.out.print("Choose option: ");

                                String opt = sc.nextLine();

                                switch (opt) {

                                    case "1":
                                        new PatientUI(patientManager).showMenu();
                                        break;

                                    case "2":
                                        if (loggedUser.getPhysicianId() != null) {
                                            java.util.List<bioLabPOJOS.LaboratoryOrder> physicianOrders =
                                                    labOrderManager.getOrdersByPhysicianId(loggedUser.getPhysicianId());

                                            if (physicianOrders.isEmpty()) {
                                                System.out.println("No orders found for this physician.");
                                            } else {
                                                System.out.println("--- MY PHYSICIAN ORDERS ---");

                                                for (bioLabPOJOS.LaboratoryOrder order : physicianOrders) {
                                                    System.out.println("Order ID: " + order.getOrderId());
                                                    System.out.println("Patient ID: " + order.getPatient().getPatientId());
                                                    System.out.println("Physician ID: " + order.getPhysician().getPhysicianId());
                                                    System.out.println("Date: " + order.getOrderDate());
                                                    System.out.println("Status: " + order.getStatus());
                                                    System.out.println("--------------------------");
                                                }
                                            }

                                        } else {
                                            System.out.println("This user is not linked to a physician.");
                                        }
                                        break;

                                    case "3":
                                        new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu();
                                        break;

                                    case "4":
                                        logout = true;
                                        break;
                                }
                            }
                            
                         // TECH: Test lifecycle management and result entry
                            else if (roleName.equalsIgnoreCase("LAB_TECHNICIAN")) {

                                System.out.println("\n===== LAB TECHNICIAN MENU =====");
                                System.out.println("1. Manage Test Catalog");
                                System.out.println("2. Manage Laboratory Orders");
                                System.out.println("3. Enter / Edit Test Results");
                                System.out.println("0. Logout");
                                System.out.print("Choose option: ");

                                String opt = sc.nextLine();

                                switch (opt) {
                                    case "1": new TestMenuUI(testManager).showMenu(); 
                                              break;
                                    case "2": new OrderMenuUI(labOrderManager).showMenu(); 
                                              break;
                                    case "3": new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu(); 
                                              break;
                                    case "0": logout = true; 
                                              break;
                                    default: System.out.println("Invalid option."); break;
                                }
                            }
                        }

                    } else {
                        System.out.println("Invalid credentials.");
                    }

                    break;

                case "2":
                    exit = true;
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}

// C:\Users\marcg\OneDrive\Imágenes\patients\patient1.png.png