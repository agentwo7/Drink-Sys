package drinkConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcConnect {
    public static void main(String[] args) {
        // Initialize DatabaseManager to handle connections
        DatabaseManager dbManager = new DatabaseManager();
        Connection connection = null;

        try {
            // Obtain a database connection
            connection = dbManager.getConnection();
            System.out.println("Connection successful!");

            // Check if the 'guest' table exists and prompt for guest information
            createGuestTable(connection);
            storeGuestInfo(connection);

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            // Close the connection using DatabaseManager's method
            dbManager.closeConnection(connection);
        }
    }

    // Method to create the 'guest' table if it doesn't exist
    private static void createGuestTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS guest ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "first_name VARCHAR(50), "
                + "last_name VARCHAR(50)"
                + ")";
        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
            System.out.println("Table 'guest' is ready.");
        }
    }

    // Method to prompt for guest info and insert it into the 'guest' table
    private static void storeGuestInfo(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter guest first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter guest last name: ");
        String lastName = scanner.nextLine();
        scanner.close();

        String insertSQL = "INSERT INTO guest (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.executeUpdate();
            System.out.println("Guest information stored successfully.");
        }
    }
}
