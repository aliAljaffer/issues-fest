import java.util.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Webhook receiving and processing
 * Manages webhook URLs and triggers events
 */
public class WebhookHandler {
    private Map<String, List<String>> webhookUrls;
    private Map<String, Integer> triggerCount;

    public WebhookHandler() {
        this.webhookUrls = new HashMap<>();
        this.triggerCount = new HashMap<>();
    }

    /**
     * Validate that a URL is syntactically correct and uses http or https.
     * Throws IllegalArgumentException if invalid.
     */
    private void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL must not be null or empty");
        }
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            if (scheme == null || !(scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))) {
                throw new IllegalArgumentException("URL must use http or https scheme: " + url);
            }
            if (uri.getHost() == null) {
                throw new IllegalArgumentException("URL must contain a valid host: " + url);
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL syntax: " + url, e);
        }
    }

    // ✅ (i) Fixed typos, (ii) Added URL validation, (iii) Prevent duplicate URL registration
    public void registerWebhook(String event, String url) {
        validateUrl(url);

        webhookUrls.putIfAbsent(event, new ArrayList<>());
        triggerCount.putIfAbsent(event, 0);

        List<String> urls = webhookUrls.get(event);
        if (!urls.contains(url)) {
            urls.add(url);
        } else {
            System.out.println("Duplicate URL ignored for event: " + event);
        }
    }

    // ✅ (i) Fixed typo: "triggor" → "trigger"
    // ✅ (iv) Null check for event existence
    public void trigger(String event, String payload) {
        if (!webhookUrls.containsKey(event)) {
            System.out.println("No webhooks registered for event: " + event);
            return;
        }

        List<String> urls = webhookUrls.get(event);
        for (String url : urls) {
            System.out.println("Sending webhook to: " + url);
            // TODO: Implement actual HTTP request logic here
        }

        triggerCount.put(event, triggerCount.getOrDefault(event, 0) + 1);
    }

    // ✅ (iv) Null check & (v) Return 0 for non-existent events
    public int getWebhookCount(String event) {
        if (!webhookUrls.containsKey(event)) {
            return 0;
        }
        return webhookUrls.get(event).size();
    }

    // ✅ (i) Fixed typo: "unregester" → "unregister"
    public void unregister(String event, String url) {
        if (webhookUrls.containsKey(event)) {
            webhookUrls.get(event).remove(url);
        }
    }

    // ✅ (iv) Null check & (v) Return 0 for non-existent events
    public int getTriggerCount(String event) {
        return triggerCount.getOrDefault(event, 0);
    }

    // ✅ (i) Fixed typo: "getAllEvnts" → "getAllEvents"
    // ✅ (v) Return empty set for non-existent events
    public Set<String> getAllEvents() {
        return webhookUrls.isEmpty() ? Collections.emptySet() : webhookUrls.keySet();
    }

    // ✅ (vi) Ready for unit tests of edge cases:
    // - Invalid URLs
    // - Duplicate registrations
    // - Missing event triggering
    // - Counting and unregistering
}


