package main;

import java.util.Scanner;

import JPA.JPAUserManager;
import bioLabPOJOS.User;
import bioLabUI.LoginUI;

import bioLabUI.PatientUI;
import bioLabUI.PhysicianMenuUI;
import bioLabUI.OrderMenuUI;
import bioLabUI.TestMenuUI;
import bioLabUI.OrderTestMenuUI;

import jdbc.ConnectionManager;
import jdbc.JDBCPatientManager;
import jdbc.JDBCPhysicianManager;
import jdbc.JDBCLaboratoryOrderManager;
import jdbc.JDBCTestManager;
import jdbc.JDBCOrderTestManager;
import jdbc.JDBCReferenceRangeManager;


public class Main {

    public static void main(String[] args) {

        JPAUserManager userManager = new JPAUserManager();

        // Crear usuario admin por defecto si no existe
        if (userManager.findUserByUsername("admin") == null) {

            User admin = new User("admin", "1234");
            userManager.createUser(admin);

            System.out.println("Default user created:");
            System.out.println("Username: admin");
            System.out.println("Password: 1234");
        }
        
        Scanner sc = new Scanner(System.in);
        LoginUI loginUI = new LoginUI();

        boolean exit = false;

        while (!exit) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       BIOLAB MANAGER");
            System.out.println("=================================");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");

            String option = sc.nextLine();

            switch (option) {

                case "1":
                	
                	// ⚠️ BYPASS: Apagamos el login real temporalmente
                    // boolean logged = loginUI.showLogin(); 
                    
                    // Forzamos el acceso para poder probar los menús
                    boolean logged = true; 
                    System.out.println("⚠️ MODO PRUEBA: Acceso concedido automáticamente sin JPA.");

                    if (logged) {
                        
                        // --- PREPARAMOS TUS MOTORES JDBC (Los que sí funcionan) ---
                        jdbc.ConnectionManager cm = new jdbc.ConnectionManager();
                        jdbc.JDBCPatientManager patientManager = new jdbc.JDBCPatientManager(cm);
                        jdbc.JDBCPhysicianManager physicianManager = new jdbc.JDBCPhysicianManager(cm);
                        jdbc.JDBCLaboratoryOrderManager labOrderManager = new jdbc.JDBCLaboratoryOrderManager(cm);
                        jdbc.JDBCTestManager testManager = new jdbc.JDBCTestManager(cm);
                        jdbc.JDBCOrderTestManager orderTestManager = new jdbc.JDBCOrderTestManager(cm);
                        jdbc.JDBCReferenceRangeManager rangeManager = new jdbc.JDBCReferenceRangeManager(cm);

                        boolean logout = false;

                    boolean logged = loginUI.showLogin();

                    if (logged) {
                        
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
                            System.out.print("Choose option: ");

                            String menuOption = sc.nextLine();

                            switch (menuOption) {

                                case "1":
                                	PatientUI patientUI = new PatientUI(patientManager);
                                    patientUI.showMenu(); // *Asegúrate de que el método se llame showMenu()
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

                                default:
                                    System.out.println("Invalid option.");
                            }
                        }

                    } else {
                        System.out.println("Access denied.");
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
