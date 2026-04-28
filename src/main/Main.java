package main;

import java.util.Scanner;

import JPA.JPAUserManager;
import bioLabPOJOS.User;
import bioLabUI.LoginUI;

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

                    boolean logged = loginUI.showLogin();

                    if (logged) {

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
                                    System.out.println("Patients module coming soon.");
                                    break;

                                case "2":
                                    System.out.println("Physicians module coming soon.");
                                    break;

                                case "3":
                                    System.out.println("Laboratory Orders module coming soon.");
                                    break;

                                case "4":
                                    System.out.println("Tests module coming soon.");
                                    break;

                                case "5":
                                    System.out.println("Reports / Analytics module coming soon.");
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