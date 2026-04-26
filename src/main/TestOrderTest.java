package main; // Tu paquete

import jdbc.ConnectionManager;
import jdbc.JDBCOrderTestManager;
import bioLabUI.OrderTestMenuUI; // Importamos tu menú visual

public class TestOrderTest {

    public static void main(String[] args) {
        System.out.println("Arrancando el sistema...");

        // 1. Conectamos a la base de datos
        ConnectionManager cm = new ConnectionManager();
        
        // 2. Preparamos tu lógica (donde viven tus 4 métodos de analítica)
        JDBCOrderTestManager manager = new JDBCOrderTestManager(cm);
        
        // 3. Preparamos la pantalla visual y le pasamos tu lógica
        OrderTestMenuUI menu = new OrderTestMenuUI(manager);
        
        // 4. ¡Mostramos el menú en la consola!
        menu.showMenu();
    }
}