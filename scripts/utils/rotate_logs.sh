#!/bin/bash

# Log Rotation Script
# Rotates and compresses application logs
# May contain: concurrency issues, race conditions, file handling errors

LOG_DIR="/var/log/myapp"
ARCHIVE_DIR="/var/log/myapp/archive"
MAX_SIZE_MB=100

echo "Starting log rotation..."

# Create archive directory
mkdir -p $ARCHIVE_DIR

# Process each log file
for logfile in $LOG_DIR/*.log; do
    if [ -f "$logfile" ]; then
        # Get file size in MB
        SIZE=$(du -m "$logfile" | cut -f1)

        if [ $SIZE -gt $MAX_SIZE_MB ]; then
            echo "Rotating $logfile (${SIZE}MB)"

            # Create timestamp
            TIMESTAMP=$(date +%Y%m%d_%H%M%S)
            BASENAME=$(basename $logfile .log)

            # Move and compress
            mv $logfile $ARCHIVE_DIR/${BASENAME}_${TIMESTAMP}.log
            gzip $ARCHIVE_DIR/${BASENAME}_${TIMESTAMP}.log

            # Create new empty log file
            touch $logfile
            chmod 644 $logfile

            echo "Rotated to $ARCHIVE_DIR/${BASENAME}_${TIMESTAMP}.log.gz"
        fi
    fi
done

# Clean up archives older than 30 days
find $ARCHIVE_DIR -name "*.log.gz" -mtime +30 -delete

echo "Log rotation completed"
