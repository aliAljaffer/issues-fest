"""
Webhook management for Python applications
Handles webhook registration and triggering
"""
import json
import time

# BUG: No validation for webhook URLs
webhooks = {}
webhook_history = []

def register_webhook(event, url):
    """Register a webhook for an event"""
    if event not in webhooks:
        webhooks[event] = []
    # BUG: Allows duplicate URLs
    webhooks[event].append(url)

# TYPO: "triggor" instead of "trigger"
# BUG: No error handling for failed requests
# BUG: Doesn't check if event exists
def trigger_webhook(event, data):
    """Trigger all webhooks for an event"""
    if event not in webhooks:
        return

    for url in webhooks[event]:
        print(f"Triggering webhook: {url}")
        webhook_history.append({
            'event': event,
            'url': url,
            'time': time.time()
        })
        # TODO: Actually send HTTP request

# BUG: Doesn't handle missing events - raises KeyError
def get_webhooks(event):
    """Get all webhooks for an event"""
    return webhooks[event]

# TYPO: "seralize" instead of "serialize"
# BUG: No error handling for non-serializable objects
def serialize_payload(data):
    """Serialize data to JSON"""
    return json.dumps(data)

# TYPO: "unregester" instead of "unregister"
def unregister_webhook(event, url):
    """Unregister a webhook"""
    if event in webhooks:
        if url in webhooks[event]:
            webhooks[event].remove(url)

# BUG: Returns all history, could be memory intensive
def get_webhook_history():
    """Get webhook trigger history"""
    return webhook_history

# TYPO: "cler_history" instead of "clear_history"
def clear_history():
    """Clear webhook history"""
    webhook_history.clear()

# BUG: Doesn't validate event exists
def get_webhook_count(event):
    """Get count of webhooks for an event"""
    return len(webhooks.get(event, []))

def get_all_events():
    """Get all registered events"""
    return list(webhooks.keys())
