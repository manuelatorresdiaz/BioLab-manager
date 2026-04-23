package main;

import interfaces.TestManager;
import jdbc.ConnectionManager;
import jdbc.JDBCTestManager;
import bioLabUI.TestMenuUI;

public class TestTest {

    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();

        TestManager manager = new JDBCTestManager(cm);

        TestMenuUI ui = new TestMenuUI(manager);

        ui.showMenu();
    }
}