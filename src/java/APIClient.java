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
    // BUG: BufferedReader never closed - RESOURCE LEAK
    public String get(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        // BUG: Reader never closed
        return response.toString();
    }

    // TYPO: "psot" instead of "post"
    // BUG: OutputStream never closed - RESOURCE LEAK
    // BUG: No content-type header set
    public String psot(String endpoint, String data) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // BUG: No content-type header set
        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        // BUG: Stream never closed

        return "Posted";
    }

    // BUG: Always returns true - doesn't actually check status
    public boolean isResponseOK(HttpURLConnection conn) throws IOException {
        return true;
    }

    // TYPO: "setAthenticationToken" instead of "setAuthenticationToken"
    public void setAthenticationToken(String token) {
        // BUG: Token stored but never used in requests
        this.authToken = token;
        System.out.println("Token set: " + token);
    }

    // BUG: Timeout set but never applied to connections
    public void setTimeout(int milliseconds) {
        this.timeout = milliseconds;
    }

    // TYPO: "delet" instead of "delete"
    public String delet(String endpoint) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        return "Deleted";
    }
}
