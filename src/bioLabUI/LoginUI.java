package bioLabUI;

import java.util.Scanner;
import JPA.JPAUserManager;

/**
 * Handles the terminal-based login interface for BioLab Manager.
 * It coordinates user input and communicates with the JPAUserManager for authentication.
 * Does not know how to read the database, and it does not know what a SQL query is. It just collects 
 * data and reports back.
 */

public class LoginUI {

    private Scanner sc;
    private JPAUserManager userManager;

    /**
     * Constructor initializes the input scanner and the user management controller.
     */
    
    public LoginUI() {
        sc = new Scanner(System.in);
        userManager = new JPAUserManager();
    }

    /**
     * Displays the login form and processes credentials.
     * 
     * @return true if authentication is successful, false otherwise.
     */
    
    public boolean showLogin() {

        System.out.println("===== LOGIN =====");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

     // Attempts to authenticate through the JPA layer
        
        boolean success = userManager.login(username, password);

        if (success) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }
}