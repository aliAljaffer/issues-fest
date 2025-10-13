#!/bin/bash

# API Health Check Script
# Monitors API endpoints and checks response times
# May contain: timeout issues, error handling gaps, parsing problems

API_ENDPOINTS=(
    "https://api.example.com/health"
    "https://api.example.com/status"
    "https://api.example.com/version"
)

TIMEOUT=5
ALERT_EMAIL="ops@example.com"
RESPONSE_TIME_THRESHOLD=2

echo "Checking API endpoints..."

for endpoint in "${API_ENDPOINTS[@]}"; do
    echo "Checking: $endpoint"

    # Measure response time
    START=$(date +%s.%N)
    RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" --max-time $TIMEOUT $endpoint)
    END=$(date +%s.%N)

    RESPONSE_TIME=$(echo "$END - $START" | bc)

    # Check HTTP status
    if [ "$RESPONSE" = "200" ]; then
        echo "  Status: OK (${RESPONSE_TIME}s)"

        # Check response time
        if [ $(echo "$RESPONSE_TIME > $RESPONSE_TIME_THRESHOLD" | bc) -eq 1 ]; then
            echo "  WARNING: Slow response time: ${RESPONSE_TIME}s"
            echo "Slow response on $endpoint: ${RESPONSE_TIME}s" | mail -s "API Performance Alert" $ALERT_EMAIL
        fi
    else
        echo "  Status: FAILED (HTTP $RESPONSE)"
        echo "API endpoint $endpoint returned HTTP $RESPONSE" | mail -s "API Alert" $ALERT_EMAIL
    fi
done

echo "API health check completed"
