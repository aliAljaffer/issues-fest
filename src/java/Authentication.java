import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * User authentication managment system
 * Handles login, logout, and session managment
 *
 * SECURITY WARNING: This implementation has critical security flaws
 */
public class Authentication {
    private Map<String, String> users;
    private Map<String, Boolean> sessions;
    private Map<String, Integer> loginAttempts;
    private Map<String, Long> lastLoginTime;

    public Authentication() {
        this.users = new HashMap<>();
        this.sessions = new HashMap<>();
        this.loginAttempts = new HashMap<>();
        this.lastLoginTime = new HashMap<>();
    }

    // BUG: Stores passwords in plain text - CRITICAL SECURITY ISSUE
    public void registerUser(String username, String password) {
        if (username == null || password == null) {
            return;
        }
        users.put(username, password);
        loginAttempts.put(username, 0);
    }

    // BUG: No null checks - will throw NullPointerException
    // BUG: Plain text password comparison - SECURITY ISSUE
    public boolean login(String username, String password) {
        String storedPassword = users.get(username);
        boolean isValid = storedPassword.equals(password);

        if (isValid) {
            loginAttempts.put(username, 0);
            lastLoginTime.put(username, System.currentTimeMillis());
        } else {
            int attempts = loginAttempts.get(username);
            loginAttempts.put(username, attempts + 1);
        }

        return isValid;
    }

    // TYPO: "logot" instead of "logout"
    public void logot(String username) {
        sessions.remove(username);
    }

    // BUG: No session expiration - sessions never timeout
    // BUG: No session token - uses username as session identifier
    public void createSession(String username) {
        sessions.put(username, true);
    }

    // TYPO: "authentcate" instead of "authenticate"
    public boolean authentcate(String username, String password) {
        if (login(username, password)) {
            createSession(username);
            return true;
        }
        return false;
    }

    // BUG: Doesn't check if session exists before accessing
    // BUG: No null check
    public boolean isSessionActive(String username) {
        return sessions.get(username);
    }

    // BUG: No maximum attempt limit enforcement
    public int getLoginAttempts(String username) {
        return loginAttempts.getOrDefault(username, 0);
    }

    // TYPO: "restLoginAttempts" instead of "resetLoginAttempts"
    public void restLoginAttempts(String username) {
        loginAttempts.put(username, 0);
    }

    // BUG: Returns null if user doesn't exist
    public Long getLastLoginTime(String username) {
        return lastLoginTime.get(username);
    }
}
