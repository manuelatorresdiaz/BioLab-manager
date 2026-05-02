package gui;

import javax.swing.*;
import java.awt.*;

import bioLabPOJOS.User;
import XML.XMLManager;
import XML.XMLDataBase;

import jdbc.ConnectionManager;
import jdbc.JDBCPatientManager;
import jdbc.JDBCPhysicianManager;
import jdbc.JDBCLaboratoryOrderManager;
import jdbc.JDBCTestManager;
import jdbc.JDBCOrderTestManager;
import jdbc.JDBCReferenceRangeManager;

import bioLabUI.*;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI(User user) {

        setTitle("BioLab Menu");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        String role = user.getRole() != null
                ? user.getRole().getRoleName()
                : "UNKNOWN";

        panel.add(new JLabel("Role: " + role));
        panel.add(new JLabel("Some options require console interaction"));

        // Managers (igual que tu Main)
        ConnectionManager cm = new ConnectionManager();
        JDBCPatientManager patientManager = new JDBCPatientManager(cm);
        JDBCPhysicianManager physicianManager = new JDBCPhysicianManager(cm);
        JDBCLaboratoryOrderManager labOrderManager = new JDBCLaboratoryOrderManager(cm);
        JDBCTestManager testManager = new JDBCTestManager(cm);
        JDBCOrderTestManager orderTestManager = new JDBCOrderTestManager(cm);
        JDBCReferenceRangeManager rangeManager = new JDBCReferenceRangeManager(cm);

        XMLManager xmlManager = new XMLManager();

        // ================= ADMIN =================
        if (role.equalsIgnoreCase("ADMIN")) {

            JButton patients = new JButton("Patients");
            patients.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new PatientUI(patientManager).showMenu();
            });
            panel.add(patients);

            JButton physicians = new JButton("Physicians");
            physicians.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new PhysicianMenuUI(physicianManager).showMenu();
            });
            panel.add(physicians);

            JButton orders = new JButton("Orders");
            orders.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderMenuUI(labOrderManager).showMenu();
            });
            panel.add(orders);

            JButton tests = new JButton("Tests");
            tests.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new TestMenuUI(testManager).showMenu();
            });
            panel.add(tests);

            JButton reports = new JButton("Reports / Analytics");
            reports.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu();
            });
            panel.add(reports);

            JButton exportXML = new JButton("Export XML");
            exportXML.addActionListener(e -> {
                XMLDataBase db = new XMLDataBase();
                xmlManager.databaseToXML(db, "export.xml");
                JOptionPane.showMessageDialog(this, "XML exported");
            });
            panel.add(exportXML);

            JButton importXML = new JButton("Import XML");
            importXML.addActionListener(e -> {
                xmlManager.xmlToDatabase("export.xml");
                JOptionPane.showMessageDialog(this, "XML imported");
            });
            panel.add(importXML);

            JButton html = new JButton("Generate HTML");
            html.addActionListener(e -> {
                xmlManager.xmlToHtml("export.xml", "biolab.xsl", "report.html");
                JOptionPane.showMessageDialog(this, "HTML generated");
            });
            panel.add(html);
        }

        // ================= PATIENT =================
        else if (role.equalsIgnoreCase("PATIENT")) {

            JButton myOrders = new JButton("My Orders");
            myOrders.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderMenuUI(labOrderManager).showMenu();
            });
            panel.add(myOrders);

            JButton myResults = new JButton("My Results");
            myResults.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu();
            });
            panel.add(myResults);
        }

        // ================= PHYSICIAN =================
        else if (role.equalsIgnoreCase("PHYSICIAN")) {

            JButton patients = new JButton("Patients");
            patients.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new PatientUI(patientManager).showMenu();
            });
            panel.add(patients);

            JButton orders = new JButton("Orders");
            orders.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderMenuUI(labOrderManager).showMenu();
            });
            panel.add(orders);

            JButton analytics = new JButton("Analytics");
            analytics.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu();
            });
            panel.add(analytics);
        }

        // ================= LAB TECHNICIAN =================
        else if (role.equalsIgnoreCase("LAB_TECHNICIAN")) {

            JButton catalog = new JButton("Test Catalog");
            catalog.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new TestMenuUI(testManager).showMenu();
            });
            panel.add(catalog);

            JButton orders = new JButton("Lab Orders");
            orders.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderMenuUI(labOrderManager).showMenu();
            });
            panel.add(orders);

            JButton results = new JButton("Enter Results");
            results.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Continue in console for interaction");
                new OrderTestMenuUI(orderTestManager, testManager, rangeManager).showMenu();
            });
            panel.add(results);
        }

        // LOGOUT
        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        panel.add(logout);

        add(panel);
        setVisible(true);
    }
}