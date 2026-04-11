package jdbc.connection;

/**
 * ConnectionConfig.java
 * Stores database configuration settings.
 * @author Person 2 - [Your Name]
 * @version 1.0
 */
public class ConnectionConfig {
    private String url;
    private String username;
    private String password;
    private String driverClass;

    public ConnectionConfig(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // TODO: Add getters and setters
}
