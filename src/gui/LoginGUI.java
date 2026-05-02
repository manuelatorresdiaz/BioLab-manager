package gui;

import javax.swing.*;
import java.awt.*;

import JPA.JPAUserManager;
import bioLabPOJOS.User;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {

        setTitle("BioLab Login");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn);

        add(panel);

        loginBtn.addActionListener(e -> doLogin());

        setVisible(true);
    }

    private void doLogin() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        JPAUserManager manager = new JPAUserManager();
        User user = manager.loginAndReturnUser(username, password);

        if (user != null) {

            String role = user.getRole() != null
                    ? user.getRole().getRoleName()
                    : "UNKNOWN";

            JOptionPane.showMessageDialog(this, "Welcome " + role);

            new MainMenuGUI(user);
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid login");
        }
    }
}