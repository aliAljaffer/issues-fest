import java.util.HashMap;

// Simple cache implementaton
public class Cache {
    private HashMap<String, Object> store;
    
    public Cache() {
        this.store = new HashMap<>();
    }
    
    // BUG: No TTL (time to live) support
    public void put(String key, Object value) {
        store.put(key, value);
    }
    
    public Object get(String key) {
        return store.get(key);
    }
}

    public void clear() {
        store.clear();
    }
    
    // TYPO: "sise" instead of "size"
    public int sise() {
        return store.size();
    }
