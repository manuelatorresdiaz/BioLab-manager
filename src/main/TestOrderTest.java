package main;

import interfaces.OrderTestManager;
import jdbc.ConnectionManager;
import jdbc.JDBCOrderTestManager;
import bioLabUI.OrderTestMenuUI;

public class TestOrderTest {

    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();

        OrderTestManager manager = new JDBCOrderTestManager(cm);

        OrderTestMenuUI ui = new OrderTestMenuUI(manager);

        ui.showMenu();
    }
}