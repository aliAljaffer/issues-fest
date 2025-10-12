
// Database connection class
public class Database {
    private String connectionString;
    
    public Database(String host, int port) {
        // Added validation
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Host cannot be null or empty");
        }
        this.connectionString = host + ":" + port;
    }

    public void connect() {
        // BUG: Still no error handling for connection
        System.out.println("Connecting to " + connectionString);
    }

    public void disconnect() {
        System.out.println("Disconnecting...");
    }
    
    // BUG: No error handling for queries
    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
}
