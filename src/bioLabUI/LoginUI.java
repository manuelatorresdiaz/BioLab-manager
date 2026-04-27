package bioLabUI;

import java.util.Scanner;
import JPA.JPAUserManager;

public class LoginUI {

    private Scanner sc;
    private JPAUserManager userManager;

    public LoginUI() {
        sc = new Scanner(System.in);
        userManager = new JPAUserManager();
    }

    public boolean showLogin() {

        System.out.println("===== LOGIN =====");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

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