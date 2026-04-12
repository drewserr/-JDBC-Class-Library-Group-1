package jdbc;

import jdbc.connection.ConnectionConfig;
import jdbc.connection.DatabaseConnection;

/**
 * DatabaseConnectionTest.java
 * Tests for the connection classes.
 * 
 * To run: make sure SQL Server Express is running, then:
 *   javac -cp "lib/*" -d out src/main/java/jdbc/connection/*.java src/main/java/jdbc/exception/*.java src/test/java/jdbc/DatabaseConnectionTest.java
 *   java -cp "out;lib/*" jdbc.DatabaseConnectionTest
 * 
 * @author Andrew Laboy
 * @version 1.0
 */
public class DatabaseConnectionTest {

    public static void testDefaultConfig() {
        System.out.println("--- Test: Default Config ---");
        ConnectionConfig config = new ConnectionConfig();

        System.out.println("Server: " + config.getServerName());
        System.out.println("Instance: " + config.getInstanceName());
        System.out.println("Database: " + config.getDatabaseName());
        System.out.println("URL: " + config.getConnectionUrl());

        if (config.getServerName().equals("localhost") 
            && config.getInstanceName().equals("SQLEXPRESS")
            && config.getDatabaseName().equals("Northwinds2024Student")) {
            System.out.println("PASSED\n");
        } else {
            System.out.println("FAILED\n");
        }
    }

    public static void testCustomConfig() {
        System.out.println("--- Test: Custom Config ---");
        ConnectionConfig config = new ConnectionConfig("192.168.1.100", "SQLDEV", "TestDB");

        System.out.println("Config: " + config.toString());

        if (config.getServerName().equals("192.168.1.100") && config.getDatabaseName().equals("TestDB")) {
            System.out.println("PASSED\n");
        } else {
            System.out.println("FAILED\n");
        }
    }

    public static void testExceptions() {
        System.out.println("--- Test: Exception Classes ---");

        jdbc.exception.DatabaseException dbEx = new jdbc.exception.DatabaseException("Test error");
        jdbc.exception.ConnectionException connEx = new jdbc.exception.ConnectionException("Connection failed");

        System.out.println("DatabaseException: " + dbEx.toString());
        System.out.println("ConnectionException: " + connEx.toString());

        if (connEx.getOperation().equals("CONNECTION")) {
            System.out.println("PASSED\n");
        } else {
            System.out.println("FAILED\n");
        }
    }

    public static void testLiveConnection() {
        System.out.println("--- Test: Live Connection to SQL Server Express ---");
        DatabaseConnection db = new DatabaseConnection();

        boolean connected = db.testConnection();
        if (connected) {
            System.out.println("PASSED - Connected to Northwinds2024Student\n");
        } else {
            System.out.println("FAILED - Could not connect.");
            System.out.println("Make sure SQL Server Express is running and the DLL is in lib/\n");
        }

        db.closeAllConnections();
    }

    public static void testConnectionPool() {
        System.out.println("--- Test: Connection Pool ---");
        DatabaseConnection db = new DatabaseConnection();

        try {
            java.sql.Connection conn1 = db.getConnection();
            System.out.println("Got connection 1");
            db.releaseConnection(conn1);
            System.out.println("Released back to pool. Pool size: " + db.getActivePoolCount());

            java.sql.Connection conn2 = db.getConnection();
            System.out.println("Got connection 2 (reused from pool). Pool size: " + db.getActivePoolCount());

            db.releaseConnection(conn2);
            db.closeAllConnections();
            System.out.println("PASSED\n");

        } catch (Exception e) {
            System.out.println("FAILED - " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  JDBC Connection Tests - Andrew Laboy");
        System.out.println("  Server: localhost\\SQLEXPRESS");
        System.out.println("  Database: Northwinds2024Student");
        System.out.println("  Auth: Windows Authentication");
        System.out.println("========================================\n");

        testDefaultConfig();
        testCustomConfig();
        testExceptions();
        testLiveConnection();
        testConnectionPool();

        System.out.println("========================================");
        System.out.println("  All tests complete.");
        System.out.println("========================================");
    }
}
