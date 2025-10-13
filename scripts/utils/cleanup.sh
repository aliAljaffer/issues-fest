#!/bin/bash

# System Cleanup Script
# Removes temporary files, old logs, and cleans up disk space
# May contain: deletion safety issues, path problems, confirmation bypasses

TEMP_DIRS=(
    "/tmp"
    "/var/tmp"
    "/var/cache/apt"
)

LOG_DIRS=(
    "/var/log"
)

AGE_DAYS=30
DRY_RUN=${1:-false}

echo "Starting system cleanup..."

if [ "$DRY_RUN" = "true" ]; then
    echo "Running in DRY RUN mode - no files will be deleted"
fi

# Clean temporary directories
for dir in "${TEMP_DIRS[@]}"; do
    echo "Cleaning: $dir"

    if [ "$DRY_RUN" = "true" ]; then
        find $dir -type f -mtime +$AGE_DAYS -ls
    else
        find $dir -type f -mtime +$AGE_DAYS -delete
    fi
done

# Clean old log files
echo "Cleaning old log files..."
for dir in "${LOG_DIRS[@]}"; do
    if [ "$DRY_RUN" = "true" ]; then
        find $dir -name "*.log" -mtime +$AGE_DAYS -ls
    else
        find $dir -name "*.log" -mtime +$AGE_DAYS -delete
    fi
done

# Clean package manager cache
echo "Cleaning package cache..."
if [ "$DRY_RUN" = "false" ]; then
    apt-get clean
    apt-get autoclean
fi

# Show disk usage
echo "Current disk usage:"
df -h

echo "Cleanup completed"
