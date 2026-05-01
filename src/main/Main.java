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

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        XMLManager xmlManager = new XMLManager();

        while (!exit) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       BIOLAB MANAGER");
            System.out.println("=================================");
            System.out.println("1. Enter system");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");

            String option = sc.nextLine();

            switch (option) {

                case "1":

                    System.out.println("MODO PRUEBA: Acceso concedido automáticamente sin JPA.");

                    ConnectionManager cm = new ConnectionManager();
                    JDBCPatientManager patientManager = new JDBCPatientManager(cm);
                    JDBCPhysicianManager physicianManager = new JDBCPhysicianManager(cm);
                    JDBCLaboratoryOrderManager labOrderManager = new JDBCLaboratoryOrderManager(cm);
                    JDBCTestManager testManager = new JDBCTestManager(cm);
                    JDBCOrderTestManager orderTestManager = new JDBCOrderTestManager(cm);
                    JDBCReferenceRangeManager rangeManager = new JDBCReferenceRangeManager(cm);

                    boolean logout = false;

                    while (!logout) {

                        System.out.println();
                        System.out.println("=================================");
                        System.out.println("         MAIN MENU");
                        System.out.println("=================================");
                        System.out.println("1. Patients");
                        System.out.println("2. Physicians");
                        System.out.println("3. Laboratory Orders");
                        System.out.println("4. Tests");
                        System.out.println("5. Reports / Analytics");
                        System.out.println("6. Logout");
                        System.out.println("7. Export Database to XML");
                        System.out.println("8. Import Database from XML");
                        System.out.println("9. Generate HTML Report (XSLT)ESTE FALTA ");
                        System.out.print("Choose option: ");

                        String menuOption = sc.nextLine();

                        switch (menuOption) {

                            case "1":
                                PatientUI patientUI = new PatientUI(patientManager);
                                patientUI.showMenu();
                                break;

                            case "2":
                                PhysicianMenuUI physicianUI = new PhysicianMenuUI(physicianManager);
                                physicianUI.showMenu();
                                break;

                            case "3":
                                OrderMenuUI orderUI = new OrderMenuUI(labOrderManager);
                                orderUI.showMenu();
                                break;

                            case "4":
                                TestMenuUI testUI = new TestMenuUI(testManager);
                                testUI.showMenu();
                                break;

                            case "5":
                                OrderTestMenuUI analyticsUI = new OrderTestMenuUI(orderTestManager, testManager, rangeManager);
                                analyticsUI.showMenu();
                                break;

                            case "6":
                                logout = true;
                                System.out.println("Logged out successfully.");
                                break;
                                
                            case "7": // EXPORT TEST
                                XMLDataBase dbToExport = new XMLDataBase();
                                
                                // Creamos un rol y un usuario de prueba para ver si el XML se llena
                                bioLabPOJOS.Role testRole = new bioLabPOJOS.Role("ADMIN");
                                testRole.setId(1);
                                
                                bioLabPOJOS.User testUser = new bioLabPOJOS.User("clara_pro", "1234");
                                testUser.setId(10);
                                testUser.setRole(testRole);
                                
                                // Los metemos en las listas de la "caja"
                                dbToExport.getUsers().add(testUser);
                                dbToExport.getRoles().add(testRole);
                                
                                System.out.println("Exporting data with test user...");
                                xmlManager.databaseToXML(dbToExport, "BioLabExport.xml");
                                break;

                            case "8": // IMPORT
                                System.out.println("Reading from XML file...");
                                // Asegúrate de que el nombre 'XMLDataBase' coincida con tu archivo .java
                                XMLDataBase importedDB = xmlManager.xmlToDatabase("BioLabExport.xml");
                                
                                if (importedDB != null) {
                                    System.out.println(">>> Import successful!");
                                    
                                    // Esto demuestra que realmente has leído el contenido:
                                    int userCount = importedDB.getUsers().size();
                                    int roleCount = importedDB.getRoles().size();
                                    
                                    System.out.println("Users recovered: " + userCount);
                                    System.out.println("Roles recovered: " + roleCount);
                                    
                                    // (Opcional) Aquí podrías recorrer la lista e imprimir los nombres:
                                    // for(User u : importedDB.getUsers()) { System.out.println("User: " + u.getUsername()); }
                                    
                                } else {
                                    System.out.println(">>> Error: Could not find or read the XML file.");
                                }
                                break;

                            case "9": // XSLT -> HTML
                                System.out.println("Transforming XML to HTML...");
                                xmlManager.xmlToHtml("BioLabExport.xml", "BioLabStyle.xslt", "BioLabReport.html");
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
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