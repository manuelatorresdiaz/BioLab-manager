package main;

import interfaces.PhysicianManager;
import jdbc.ConnectionManager;
import jdbc.JDBCPhysicianManager;
import ui.PhysicianUI;

public class TestPhysician {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();
        PhysicianManager physicianManager = new JDBCPhysicianManager(connectionManager);
        PhysicianUI physicianUI = new PhysicianUI(physicianManager);

        physicianUI.showMenu();
    }
}