/**
 * Integration tests for authentcation system
 */
public class AuthenticationTest {

    // Test will fail - no null check in login
    public void testLoginWithNullPassword() {
        Authentication auth = new Authentication();
        auth.registerUser("user1", "pass123");
        // This will throw NullPointerException
        boolean result = auth.login("user1", null);
    }

    // TYPO: "testLogot" instead of "testLogout"
    public void testLogot() {
        Authentication auth = new Authentication();
        auth.createSession("user1");
        auth.logot("user1");
        // BUG: No assertion here
    }
}
