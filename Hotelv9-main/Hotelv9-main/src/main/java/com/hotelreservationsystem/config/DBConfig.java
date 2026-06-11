package com.hotelreservationsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Configuration Class
 * 
 * Manages database connection to MySQL through JDBC.
 * Handles connection pooling and configuration for the Hotel Reservation System.
 * 
 * Configuration Details:
 * - Driver: MySQL Connector/J 8.0.33
 * - Database: hotelreservationsystem
 * - Host: localhost
 * - Port: 3306
 * - Charset: UTF-8
 */
public class DBConfig {
    
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotelreservationsystem?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
     * Establishes and returns a database connection
     * 
     * @return Connection object to the database
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName(DB_DRIVER);
            
            // Create and return connection
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Ensure mysql-connector-java is in classpath.", e);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database at " + DB_URL, e);
        }
    }
    
    /**
     * Tests the database connection
     * Useful for debugging connection issues
     * 
     * @return true if connection is successful, false otherwise
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            boolean isValid = conn.isValid(5);
            conn.close();
            return isValid;
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
