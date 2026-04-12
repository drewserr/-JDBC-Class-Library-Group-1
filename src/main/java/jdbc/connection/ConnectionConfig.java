package jdbc.connection;

/**
 * ConnectionConfig.java
 * Stores the database connection settings for SQL Server Express.
 * Uses Windows Authentication (no username/password needed).
 * 
 * @author Andrew Laboy
 * @version 1.0
 * 
 * AI Disclosure: Claude.ai was used to help structure this class.
 */
public class ConnectionConfig {

    private String serverName;
    private String instanceName;
    private String databaseName;

    // Default constructor for localhost\SQLEXPRESS
    public ConnectionConfig() {
        this.serverName = "localhost";
        this.instanceName = "SQLEXPRESS";
        this.databaseName = "Northwinds2024Student";
    }

    // Custom constructor
    public ConnectionConfig(String serverName, String instanceName, String databaseName) {
        this.serverName = serverName;
        this.instanceName = instanceName;
        this.databaseName = databaseName;
    }

    // Builds the JDBC connection URL for SQL Server Express with Windows Auth
    public String getConnectionUrl() {
        return "jdbc:sqlserver://" + serverName + "\\" + instanceName
             + ";databaseName=" + databaseName
             + ";integratedSecurity=true"
             + ";encrypt=true;trustServerCertificate=true";
    }

    public String getServerName() { return serverName; }
    public void setServerName(String serverName) { this.serverName = serverName; }

    public String getInstanceName() { return instanceName; }
    public void setInstanceName(String instanceName) { this.instanceName = instanceName; }

    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }

    @Override
    public String toString() {
        return "ConnectionConfig [server=" + serverName + "\\" + instanceName
             + ", database=" + databaseName + "]";
    }
}
