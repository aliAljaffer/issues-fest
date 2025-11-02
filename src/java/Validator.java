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
    // Password must be at least 6 characters
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) 
                hasUpperCase = true;
            if (Character.isLowerCase(c)) 
                hasLowerCase = true;
            if (Character.isDigit(c))
                hasNumber = true;
            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        if (hasLowerCase && hasUpperCase && hasNumber && hasSpecialChar) {
            return true;
        }
        return false;
    }
    
    // TYPO: "valdate" instead of "validate"
    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }
}
// Update 32
// Update 33
// Update 34
// Update 35
// Update 36
// Update 37
// Update 38
// Update 39
// Update 40

    // Emergency security patch
    public static boolean isValidInput(String input) {
        // BUG: Still very basic validation
        return input != null && !input.isEmpty();
    }
