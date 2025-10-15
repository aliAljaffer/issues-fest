import java.util.*;

/**
 * Notification delivery servise
 * Supports email, SMS, and push notfications
 */
public class NotificationService {
    private List<String> emailQueue;
    private List<String> smsQueue;
    private List<String> pushQueue;

    public NotificationService() {
        this.emailQueue = new ArrayList<>();
        this.smsQueue = new ArrayList<>();
        this.pushQueue = new ArrayList<>();
    }

    // BUG: No email validation
    // TYPO: "sendEmial" instead of "sendEmail"
    public void sendEmail(String recipient, String subject, String body) {
        String message = recipient + ": " + subject + " - " + body;
        emailQueue.add(message);
        System.out.println("Email queued");
    }

    // BUG: No phone number validation
    // TYPO: "phoneNumer" parameter instead of "phoneNumber"
    public void sendSMS(String phoneNumber, String message) {
        smsQueue.add(phoneNumber + ": " + message);
    }

    // TYPO: "sendPushNotificaton" instead of "sendPushNotification"
    public void sendPushNotification(String deviceId, String message) {
        pushQueue.add(deviceId + ": " + message);
    }

    // TYPO: "procesQueue" instead of "processQueue"
    // BUG: Doesn't handle empty queues
    public void processQueue() {
        System.out.println("Processing " + emailQueue.size() + " emails");
        emailQueue.clear();
        System.out.println("Processing " + smsQueue.size() + " SMS");
        smsQueue.clear();
        System.out.println("Processing " + pushQueue.size() + " push notifications");
        pushQueue.clear();
    }

    // BUG: No validation for notification type
    // BUG: Doesn't actually schedule - just prints
    public void scheduleNotification(String type, String recipient, long delay) {
        System.out.println("Scheduled " + type + " notification for " + recipient);
        // TODO: Implement actual scheduling
    }

    // TYPO: "brodcast" instead of "broadcast"
    public void broadcast(String message) {
        System.out.println("Broadcasting: " + message);
        // TODO: Send to all registered devices
    }

    // BUG: Returns sum of all queues but doesn't label which
    public int getPendingCount() {
        return emailQueue.size() + smsQueue.size() + pushQueue.size();
    }
}
