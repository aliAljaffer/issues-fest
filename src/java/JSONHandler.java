import java.io.*;
import java.util.*;

/**
 * JSON file procesing utilities
 * Simple JSON handling without external libraries
 *
 * NOTE: This is a simplified implementation
 */
public class JSONHandler {

    // BUG: No error handling
    // BUG: BufferedReader never closed - RESOURCE LEAK
    public String readJSON(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        // BUG: Reader never closed
        return content.toString();
    }

    // TYPO: "wirteJSON" instead of "writeJSON"
    // BUG: FileWriter never closed - RESOURCE LEAK
    public void wirteJSON(String filepath, String json) throws IOException {
        FileWriter writer = new FileWriter(filepath);
        writer.write(json);
        // BUG: Writer never closed
    }

    // BUG: Very basic validation - doesn't handle nested structures
    public boolean valdateJSON(String jsonString) {
        try {
            int braceCount = 0;
            for (char c : jsonString.toCharArray()) {
                if (c == '{') braceCount++;
                if (c == '}') braceCount--;
            }
            return braceCount == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // BUG: Doesn't handle malformed JSON
    // TYPO: "parsSimple" instead of "parseSimple"
    public Map<String, String> parsSimple(String json) {
        Map<String, String> result = new HashMap<>();
        // Very basic parser - many bugs
        String content = json.replace("{", "").replace("}", "");
        String[] pairs = content.split(",");
        for (String pair : pairs) {
            String[] kv = pair.split(":");
            if (kv.length == 2) {
                result.put(kv[0].trim().replace("\"", ""),
                          kv[1].trim().replace("\"", ""));
            }
        }
        return result;
    }

    // TYPO: "formatt" instead of "format" (double 't')
    public String formatt(Map<String, String> data) {
        StringBuilder json = new StringBuilder("{");
        int i = 0;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":");
            json.append("\"").append(entry.getValue()).append("\"");
            if (i < data.size() - 1) {
                json.append(",");
            }
            i++;
        }
        json.append("}");
        return json.toString();
    }
}
