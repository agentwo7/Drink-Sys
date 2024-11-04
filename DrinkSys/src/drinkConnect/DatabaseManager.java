package drinkConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://192.168.1.47:3307/drink_ordering_db"; // Replace with your IP and database name
    private static final String USER = "drink_admin"; // Your MySQL username
    private static final String PASSWORD = "AP123"; // Your MySQL password

    // Method to establish a connection to the database
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Register the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish and return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close a connection
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
