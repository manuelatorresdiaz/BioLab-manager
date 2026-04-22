package main;

import interfaces.PatientManager;
import jdbc.ConnectionManager;
import jdbc.JDBCPatientManager;
import ui.PatientUI;

public class TestPatient {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        PatientManager patientManager = new JDBCPatientManager(connectionManager);
        PatientUI patientUI = new PatientUI(patientManager);

        patientUI.showMenu();
    }
}