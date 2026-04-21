package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:biolab.db");
            System.out.println("Connected to SQLite database successfully.");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database.");
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException e) {
            System.out.println("Error disconnecting from database.");
            e.printStackTrace();
        }
    }
}
