package main;

import interfaces.ReferenceRangeManager;
import jdbc.ConnectionManager;
import jdbc.JDBCReferenceRangeManager;
import bioLabUI.ReferenceRangeMenuUI;

public class TestReferenceRange {

    public static void main(String[] args) {

        ConnectionManager cm = new ConnectionManager();

        ReferenceRangeManager manager = new JDBCReferenceRangeManager(cm);

        ReferenceRangeMenuUI ui = new ReferenceRangeMenuUI(manager);
        
        ui.showMenu();
    }
}