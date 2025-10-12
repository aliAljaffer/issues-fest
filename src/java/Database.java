
// Database connection class
public class Database {
    private String connectionString;
    
    public Database(String host, int port) {
        this.connectionString = host + ":" + port;
    }
    
    // TODO: Implement connection logic
}

    public void connect() {
        // BUG: No error handling
        System.out.println("Connecting to " + connectionString);
    }

    public void disconnect() {
        System.out.println("Disconnecting...");
    }
    
    // BUG: No error handling for queries
    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }
