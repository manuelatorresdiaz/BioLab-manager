 package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Utility class responsible for managing the lifecycle of the SQL connection.
 * It centralizes the driver loading and session establishment for the SQLite database.
 */
public class ConnectionManager {
    private Connection connection;
    /**
     * Establishes a session with the local SQLite database.
     * * @return A valid Connection object, or null if the connection fails.
     */
    public Connection connect() {
        try {
            // Explicitly load the SQLite driver to avoid "No suitable driver" errors
            Class.forName("org.sqlite.JDBC");
            // Connect to the database file located in the project's root directory
            // If biolab.db does not exist, SQLite will create it automatically upon connection
            connection = DriverManager.getConnection("jdbc:sqlite:biolab.db");
            /*
             * This is where the actual connection happens. It opens the link by looking for a file named biolab.db in the root folder of your project. The resulting session is saved in your connection variable.
               Error Handling (The catch clauses)
             */
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
    /**
     * Safely closes the current database connection to release system resources.
     */
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