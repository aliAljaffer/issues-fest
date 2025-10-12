/**
 * User class for managment system
 */
public class User {
    private String username;
    private String email;
    private int age;
    private boolean isActive;
    
    // BUG: no validation in constructor
    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.isActive = true;
    }
    
    // BUG: very basic email validation
    public boolean validateEmail() {
        return email.contains("@");
    }
    
    // TYPO: "valdate" instead of "validate"
    public boolean valdateAge() {
        return age >= 0;
    }
    
    // BUG: allows negative age
    public void setAge(int age) {
        this.age = age;
    }
    
    // TYPO: "deactivte" instead of "deactivate"
    public void deactivte() {
        isActive = false;
    }
    
    public String getUsername() {
        return username;
    }
    
    // BUG: allows duplicate usernames (no uniqueness check)
    public void setUsername(String username) {
        this.username = username;
    }
    
    // TYPO: "retreive" in comment
    // Retreive the user's email address
    public String getEmail() {
        return email;
    }
}
