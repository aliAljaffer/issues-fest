import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Password reset functionaliy
 * Generates tokens and handles password resets
 *
 * SECURITY WARNING: Multiple security vulnerabilities present
 */
public class PasswordReset {
    private Map<String, String> resetTokens;
    private Map<String, Long> tokenCreationTime;

    public PasswordReset() {
        this.resetTokens = new HashMap<>();
        this.tokenCreationTime = new HashMap<>();
    }

    // BUG: Uses Random instead of SecureRandom - WEAK TOKEN GENERATION
    // BUG: Only 4 digits - easily guessable
    // BUG: Tokens never expire
    public String generateResetToken(String email) {
        Random rand = new Random();
        String token = String.valueOf(rand.nextInt(9999));
        resetTokens.put(email, token);
        tokenCreationTime.put(email, System.currentTimeMillis());
        return token;
    }

    // TYPO: "valdate" instead of "validate"
    // BUG: No null check - will throw NullPointerException
    // BUG: No expiration check
    public boolean valdateToken(String email, String token) {
        return resetTokens.get(email).equals(token);
    }

    // BUG: No password strength validation
    // BUG: Doesn't verify token before reset
    public void resetPassword(String email, String newPassword) {
        System.out.println("Password reset for: " + email);
        resetTokens.remove(email);
        tokenCreationTime.remove(email);
    }

    // TYPO: "revokeTokenken" instead of "revokeToken" (double 'k')
    public void revokeTokenken(String email) {
        resetTokens.remove(email);
        tokenCreationTime.remove(email);
    }

    // BUG: Doesn't actually check expiration
    public boolean isTokenExpired(String email) {
        return false;
    }

    // TYPO: "getAllTokenns" instead of "getAllTokens"
    public int getAllTokenns() {
        return resetTokens.size();
    }
}
