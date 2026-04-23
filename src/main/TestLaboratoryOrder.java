package main;

import interfaces.LaboratoryOrderManager;
import jdbc.ConnectionManager;
import jdbc.JDBCLaboratoryOrderManager;
import bioLabUI.OrderMenuUI;

public class TestLaboratoryOrder {

    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();

        LaboratoryOrderManager manager = new JDBCLaboratoryOrderManager(cm);

        OrderMenuUI ui = new OrderMenuUI(manager);

        ui.showMenu();
    }
}