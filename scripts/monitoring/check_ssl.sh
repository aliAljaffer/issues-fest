#!/bin/bash

# SSL Certificate Expiration Check
# Monitors SSL certificates and alerts before expiration
# May contain: date calculation errors, notification issues, parsing problems

DOMAINS=(
    "example.com"
    "api.example.com"
    "www.example.com"
)

WARNING_DAYS=30
CRITICAL_DAYS=7
ALERT_EMAIL="security@example.com"

echo "Checking SSL certificates..."

for domain in "${DOMAINS[@]}"; do
    echo "Checking: $domain"

    # Get certificate expiration date
    EXPIRY_DATE=$(echo | openssl s_client -servername $domain -connect $domain:443 2>/dev/null | openssl x509 -noout -enddate | cut -d= -f2)

    # Convert to seconds
    EXPIRY_EPOCH=$(date -d "$EXPIRY_DATE" +%s)
    CURRENT_EPOCH=$(date +%s)

    # Calculate days until expiration
    DAYS_UNTIL_EXPIRY=$(( ($EXPIRY_EPOCH - $CURRENT_EPOCH) / 86400 ))

    echo "  Expires in $DAYS_UNTIL_EXPIRY days ($EXPIRY_DATE)"

    # Check thresholds
    if [ $DAYS_UNTIL_EXPIRY -lt $CRITICAL_DAYS ]; then
        echo "  CRITICAL: Certificate expires soon!"
        echo "SSL certificate for $domain expires in $DAYS_UNTIL_EXPIRY days" | mail -s "CRITICAL: SSL Expiring" $ALERT_EMAIL
    elif [ $DAYS_UNTIL_EXPIRY -lt $WARNING_DAYS ]; then
        echo "  WARNING: Certificate expires soon"
        echo "SSL certificate for $domain expires in $DAYS_UNTIL_EXPIRY days" | mail -s "WARNING: SSL Expiring" $ALERT_EMAIL
    fi
done

echo "SSL check completed"
