package gui;

import javax.swing.*;
import java.awt.*;

import JPA.JPAUserManager;
import bioLabPOJOS.User;

/**
 * Entry point for the Graphical User Interface.
 * Handles user authentication via JPA and navigates to the main menu based on roles.
 */

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Initializes the login window components and layout.
     */
    
    public LoginGUI() {
    	// Window configuration
        setTitle("BioLab Login");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

     // Layout setup: 3 rows, 2 columns (Username, Password, Login button)
        JPanel panel = new JPanel(new GridLayout(3, 2));
     // Username Input
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
     // Password Input
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
     // Action Component
        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn);

        add(panel);
     // Event Listener using Lambda expression
        loginBtn.addActionListener(e -> doLogin());

        setVisible(true);
    }
    /**
     * Orchestrates the authentication logic.
     * Communicates with the JPA layer to verify credentials and determine user permissions.
     */
    private void doLogin() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
     // Initializing the JPA Manager specifically for Security/User logic
        JPAUserManager manager = new JPAUserManager();
        User user = manager.loginAndReturnUser(username, password);
     // Role-based authorization check
        if (user != null) {

            String role = user.getRole() != null
                    ? user.getRole().getRoleName()
                    : "UNKNOWN";

            JOptionPane.showMessageDialog(this, "Welcome " + role);

            new MainMenuGUI(user);
            dispose();

        } else {
        	// Authentication failure
            JOptionPane.showMessageDialog(this, "Invalid login");
        }
    }
}