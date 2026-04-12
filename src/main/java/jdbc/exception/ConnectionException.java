package jdbc.exception;

/**
 * ConnectionException.java
 * Thrown when there is a problem connecting to the database.
 * 
 * @author Andrew Laboy
 * @version 1.0
 */
public class ConnectionException extends DatabaseException {

    public ConnectionException(String message) {
        super("CONNECTION", message, null);
    }

    public ConnectionException(String message, Throwable cause) {
        super("CONNECTION", message, cause);
    }
}
