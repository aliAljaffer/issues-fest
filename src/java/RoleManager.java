import java.util.*;

/**
 * User role and permision management
 * Handles role assignment and permission checking
 */
public class RoleManager {
    private Map<String, Set<String>> userRoles;
    private Map<String, Set<String>> rolePermissions;
    private Map<String, String> roleDescriptions;

    public RoleManager() {
        this.userRoles = new HashMap<>();
        this.rolePermissions = new HashMap<>();
        this.roleDescriptions = new HashMap<>();
        initializeDefaultRoles();
    }

    // BUG: No validation for null or empty role
    public void assignRole(String username, String role) {
        if (!userRoles.containsKey(username)) {
            userRoles.put(username, new HashSet<>());
        }
        userRoles.get(username).add(role);
    }

    // TYPO: "addPermision" instead of "addPermission"
    public void addPermision(String role, String permission) {
        if (!rolePermissions.containsKey(role)) {
            rolePermissions.put(role, new HashSet<>());
        }
        rolePermissions.get(role).add(permission);
    }

    // BUG: Doesn't check if user or role exists - throws NullPointerException
    public boolean hasPermission(String username, String permission) {
        Set<String> roles = userRoles.get(username);
        for (String role : roles) {
            if (rolePermissions.get(role).contains(permission)) {
                return true;
            }
        }
        return false;
    }

    // TYPO: "remveRole" instead of "removeRole"
    public void remveRole(String username, String role) {
        if (userRoles.containsKey(username)) {
            userRoles.get(username).remove(role);
        }
    }

    // BUG: Returns null if role doesn't exist
    public Set<String> getUserRoles(String username) {
        return userRoles.get(username);
    }

    // TYPO: "getPermisions" instead of "getPermissions"
    public Set<String> getPermisions(String role) {
        return rolePermissions.get(role);
    }

    private void initializeDefaultRoles() {
        addPermision("admin", "read");
        addPermision("admin", "write");
        addPermision("admin", "delete");
        addPermision("user", "read");
    }

    // TYPO: "setRoleDescritpion" instead of "setRoleDescription"
    public void setRoleDescritpion(String role, String description) {
        roleDescriptions.put(role, description);
    }
}
