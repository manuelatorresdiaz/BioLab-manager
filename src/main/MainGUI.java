package main;

import gui.LoginGUI;
import JPA.JPAUserManager;
import bioLabPOJOS.Role;
import bioLabPOJOS.User;
/**
 * Main entry point for the BioLab Graphical User Interface (GUI).
 * This class initializes the security layer and launches the login window.
 */
public class MainGUI {

    public static void main(String[] args) {
    	// --- SECURITY INITIALIZATION (JPA) ---
        // We initialize the JPAUserManager to check for existing accounts.
        JPAUserManager authInit = new JPAUserManager();
        /* * BOOTSTRAPPING LOGIC:
         * To ensure the system is functional on the first run, we check if 
         * default users exist. If not, we create them with their respective roles.
         */

        // 1. Create Default Admin
        if (authInit.findUserByUsername("admin") == null) {
            Role role = new Role("ADMIN");
            User admin = new User("admin", "1234");
            admin.setRole(role);
            authInit.createUser(admin);
        }
     // 2. Create Default Patient (Linked to JDBC Patient record #1)
        if (authInit.findUserByUsername("patient1") == null) {
            Role role = new Role("PATIENT");
            User user = new User("patient1", "1234");
            user.setRole(role);
            user.setPatientId(1);
            authInit.createUser(user);
        }
     // 3. Create Default Physician (Linked to JDBC Physician record #1)
        if (authInit.findUserByUsername("physician1") == null) {
            Role role = new Role("PHYSICIAN");
            User user = new User("physician1", "1234");
            user.setRole(role);
            user.setPhysicianId(1);
            authInit.createUser(user);
        }
     // 4. Create Default Laboratory Technician
        if (authInit.findUserByUsername("tech1") == null) {
            Role role = new Role("LAB_TECHNICIAN");
            User user = new User("tech1", "1234");
            user.setRole(role);
            authInit.createUser(user);
        }
     // --- LAUNCH INTERFACE ---
        // Once the database is ready, we invoke the Login window.
        // Swing utilities will handle the transition to Role-Based menus after authentication.
        new LoginGUI();
    }
}