 package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    // Variable at the class level to keep your original structure
    private Connection connection;

    public Connection connect() {
        try {
            // Explicitly load the SQLite driver to avoid "No suitable driver" errors
            Class.forName("org.sqlite.JDBC");
            
            // Connect to the database located in the project root folder
            connection = DriverManager.getConnection("jdbc:sqlite:biolab.db");
            
            System.out.println("Connected to SQLite database successfully.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: SQLite JDBC Driver not found. Please check Build Path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ ERROR: Could not connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Restored to your original version (no parameters needed!)
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException e) {
            System.err.println("Error disconnecting from database.");
            e.printStackTrace();
        }
    }
}