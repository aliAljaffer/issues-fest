// REST API controller
public class ApiController {
    
    // TYPO: "recieve" instead of "receive"
    public String recieveData(String endpoint) {
        return "Data from: " + endpoint;
    }
    
    // BUG: No error handling
    public void sendData(String endpoint, String data) {
        System.out.println("Sending to " + endpoint + ": " + data);
    }
}
