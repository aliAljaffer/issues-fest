#!/bin/bash

# System Health Check Script
# Monitors CPU, memory, disk usage and alerts on thresholds
# May contain: threshold logic errors, notification failures, parsing issues

ALERT_EMAIL="admin@example.com"
CPU_THRESHOLD=80
MEMORY_THRESHOLD=85
DISK_THRESHOLD=90

echo "Running system health checks..."

# Check CPU usage
CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | sed "s/.*, *\([0-9.]*\)%* id.*/\1/" | awk '{print 100 - $1}')
if [ $(echo "$CPU_USAGE > $CPU_THRESHOLD" | bc) -eq 1 ]; then
    echo "ALERT: CPU usage is ${CPU_USAGE}%"
    echo "CPU usage critical: ${CPU_USAGE}%" | mail -s "CPU Alert" $ALERT_EMAIL
fi

# Check memory usage
MEMORY_USAGE=$(free | grep Mem | awk '{print ($3/$2) * 100.0}')
if [ $(echo "$MEMORY_USAGE > $MEMORY_THRESHOLD" | bc) -eq 1 ]; then
    echo "ALERT: Memory usage is ${MEMORY_USAGE}%"
    echo "Memory usage critical: ${MEMORY_USAGE}%" | mail -s "Memory Alert" $ALERT_EMAIL
fi

# Check disk usage
df -H | grep -vE '^Filesystem|tmpfs|cdrom' | awk '{ print $5 " " $1 }' | while read output;
do
    USAGE=$(echo $output | awk '{print $1}' | sed 's/%//')
    PARTITION=$(echo $output | awk '{print $2}')

    if [ $USAGE -ge $DISK_THRESHOLD ]; then
        echo "ALERT: Disk usage on $PARTITION is ${USAGE}%"
        echo "Disk usage critical on $PARTITION: ${USAGE}%" | mail -s "Disk Alert" $ALERT_EMAIL
    fi
done

# Check if critical services are running
SERVICES=("nginx" "postgresql" "redis")
for service in "${SERVICES[@]}"; do
    if ! systemctl is-active --quiet $service; then
        echo "ALERT: Service $service is not running"
        echo "Service $service is down" | mail -s "Service Alert" $ALERT_EMAIL
    fi
done

echo "Health check completed at $(date)"
