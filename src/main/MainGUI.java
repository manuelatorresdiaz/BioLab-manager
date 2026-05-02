package main;

import gui.LoginGUI;
import JPA.JPAUserManager;
import bioLabPOJOS.Role;
import bioLabPOJOS.User;

public class MainGUI {

    public static void main(String[] args) {

        JPAUserManager authInit = new JPAUserManager();

        if (authInit.findUserByUsername("admin") == null) {
            Role role = new Role("ADMIN");
            User admin = new User("admin", "1234");
            admin.setRole(role);
            authInit.createUser(admin);
        }

        if (authInit.findUserByUsername("patient1") == null) {
            Role role = new Role("PATIENT");
            User user = new User("patient1", "1234");
            user.setRole(role);
            user.setPatientId(1);
            authInit.createUser(user);
        }

        if (authInit.findUserByUsername("physician1") == null) {
            Role role = new Role("PHYSICIAN");
            User user = new User("physician1", "1234");
            user.setRole(role);
            user.setPhysicianId(1);
            authInit.createUser(user);
        }

        if (authInit.findUserByUsername("tech1") == null) {
            Role role = new Role("LAB_TECHNICIAN");
            User user = new User("tech1", "1234");
            user.setRole(role);
            authInit.createUser(user);
        }

        new LoginGUI();
    }
}