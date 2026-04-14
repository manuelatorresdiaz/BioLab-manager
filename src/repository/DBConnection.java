package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/biolab",
                "root",
                "root"
            );
            //Más adelante cambiar:"biolab" por el nombre real de BD
			//"root" por tu usuario
			//"root" por tu contraseña
            //no me sirve conectar a database
        } catch (SQLException e) {
            System.out.println("Error connecting to database.");
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error disconnecting from database.");
            e.printStackTrace();
        }
    }
}
