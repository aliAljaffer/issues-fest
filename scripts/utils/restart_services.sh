#!/bin/bash

# Service Restart Script
# Gracefully restarts services with health checks
# May contain: timeout issues, dependency ordering, rollback problems

SERVICES=(
    "redis"
    "postgresql"
    "nginx"
    "application"
)

MAX_WAIT=30
HEALTH_CHECK_INTERVAL=2

restart_service() {
    local service=$1

    echo "Restarting $service..."

    # Check if service is running
    if systemctl is-active --quiet $service; then
        echo "  Stopping $service..."
        systemctl stop $service

        # Wait for service to stop
        WAIT_COUNT=0
        while systemctl is-active --quiet $service; do
            sleep 1
            WAIT_COUNT=$((WAIT_COUNT + 1))
            if [ $WAIT_COUNT -gt $MAX_WAIT ]; then
                echo "  ERROR: Timeout waiting for $service to stop"
                return 1
            fi
        done
    fi

    # Start service
    echo "  Starting $service..."
    systemctl start $service

    # Wait for service to be ready
    sleep $HEALTH_CHECK_INTERVAL

    # Verify service is running
    if systemctl is-active --quiet $service; then
        echo "  $service restarted successfully"
        return 0
    else
        echo "  ERROR: Failed to start $service"
        return 1
    fi
}

echo "Starting service restarts..."

for service in "${SERVICES[@]}"; do
    restart_service $service

    if [ $? -ne 0 ]; then
        echo "Failed to restart $service, aborting remaining restarts"
        exit 1
    fi
done

echo "All services restarted successfully"
