import java.util.*;

/**
 * Webhook recieving and processing
 * Manages webhook URLs and triggers events
 */
public class WebhookHandler {
    private Map<String, List<String>> webhookUrls;
    private Map<String, Integer> triggerCount;

    public WebhookHandler() {
        this.webhookUrls = new HashMap<>();
        this.triggerCount = new HashMap<>();
    }

    // BUG: No URL validation
    // BUG: Allows duplicate URLs
    public void registerWebhook(String event, String url) {
        if (!webhookUrls.containsKey(event)) {
            webhookUrls.put(event, new ArrayList<>());
            triggerCount.put(event, 0);
        }
        webhookUrls.get(event).add(url);
    }

    // TYPO: "triggor" instead of "trigger"
    // BUG: No error handling for failed webhooks
    // BUG: Doesn't check if event exists - NullPointerException
    public void triggor(String event, String payload) {
        List<String> urls = webhookUrls.get(event);
        for (String url : urls) {
            System.out.println("Sending webhook to: " + url);
            // TODO: Actually send HTTP request
        }
        triggerCount.put(event, triggerCount.get(event) + 1);
    }

    // BUG: Doesn't check if event exists - NullPointerException
    public int getWebhookCount(String event) {
        return webhookUrls.get(event).size();
    }

    // TYPO: "unregester" instead of "unregister"
    public void unregester(String event, String url) {
        if (webhookUrls.containsKey(event)) {
            webhookUrls.get(event).remove(url);
        }
    }

    // BUG: Returns null if event doesn't exist
    public Integer getTriggerCount(String event) {
        return triggerCount.get(event);
    }

    // TYPO: "getAllEvnts" instead of "getAllEvents"
    public Set<String> getAllEvnts() {
        return webhookUrls.keySet();
    }
}
