// Simple logging utility
public class Logger {
    public static void log(String message) {
        System.out.println("[LOG] " + message);
    }
    
    // TYPO: "erro" instead of "error"
    public static void erro(String message) {
        System.err.println("[ERROR] " + message);
    }
}
