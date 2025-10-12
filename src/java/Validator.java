/**
 * Input validaton utility class
 */
public class Validator {
    
    // BUG: very basic email validation
    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    
    // BUG: no upper bound check for age
    public static boolean isValidAge(int age) {
        return age >= 0;
    }
    
    // BUG: no length validation
    public static boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9]+");
    }
    
    // BUG: weak password validation
    // TYPO: "atleast" instead of "at least"
    // Password must be atleast 6 characters
    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
    
    // TYPO: "valdate" instead of "validate"
    public static boolean valdatePhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }
}
// Update 32
// Update 33
// Update 34
// Update 35
// Update 36
