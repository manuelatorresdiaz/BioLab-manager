package main;

import interfaces.PatientManager;
import jdbc.ConnectionManager;
import jdbc.JDBCPatientManager;
import bioLabUI.PatientUI;

public class TestPatient {
    public static void main(String[] args) {
        ConnectionManager cm = new ConnectionManager();
        PatientManager manager = new JDBCPatientManager(cm);
        PatientUI ui = new PatientUI(manager);

        ui.showMenu();
    }
}