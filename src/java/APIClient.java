import java.net.*;
import java.io.*;

/**
 * HTTP API client for external servises
 * Handles GET and POST requests
 */
public class APIClient {
    private String baseUrl;
    private String authToken;
    private int timeout = 30000;

    public APIClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // BUG: No timeout configured on connection
    // BUG: No error handling for network failures
    public String get(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

  StringBuilder response = new StringBuilder();

try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
)) {
    String line;
    while ((line = reader.readLine()) != null) {
        response.append(line);
    }
}
return response.toString();
    }

    // BUG: No content-type header set
    public String post(String endpoint, String data) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // BUG: No content-type header set    
try (OutputStream os = conn.getOutputStream()) {
    os.write(data.getBytes());
    os.flush();
}
return "Posted";
    }

    // BUG: Always returns true - doesn't actually check status
    public boolean isResponseOK(HttpURLConnection conn) throws IOException {
        return true;
    }

    public void setAuthenticationToken(String token) {
        // BUG: Token stored but never used in requests
        this.authToken = token;
        System.out.println("Token set: " + token);
    }

    // BUG: Timeout set but never applied to connections
    public void setTimeout(int milliseconds) {
        this.timeout = milliseconds;
    }

   
    public String delete(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        return "Deleted";
    }
}