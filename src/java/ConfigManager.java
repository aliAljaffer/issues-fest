import java.util.HashMap;
import java.util.Map;

// Configuration managment class
public class ConfigManager {
    private Map<String, String> config;
    
    public ConfigManager() {
        this.config = new HashMap<>();
    }
    
    // BUG: No validation for null keys
    public void set(String key, String value) {
        config.put(key, value);
    }
    
    public String get(String key) {
        return config.get(key);
    }
}
