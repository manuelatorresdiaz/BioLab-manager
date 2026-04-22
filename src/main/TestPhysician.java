package main;

import interfaces.PhysicianManager;
import jdbc.ConnectionManager;
import jdbc.JDBCPhysicianManager;
import bioLabUI.PhysicianMenuUI;

public class TestPhysician {
    public static void main(String[] args) {
        ConnectionManager cm = new ConnectionManager();
        PhysicianManager manager = new JDBCPhysicianManager(cm);
        PhysicianMenuUI ui = new PhysicianMenuUI(manager);

        ui.showMenu();
    }
}