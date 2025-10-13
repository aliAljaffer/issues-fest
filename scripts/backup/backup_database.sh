#!/bin/bash

# Database Backup Script
# Creates compressed backups of PostgreSQL databases
# May contain: space checking issues, cleanup problems, path handling

BACKUP_DIR="/var/backups/postgresql"
RETENTION_DAYS=7
DB_NAME="production_db"
DB_USER="postgres"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
BACKUP_FILE="$BACKUP_DIR/${DB_NAME}_${TIMESTAMP}.sql"

echo "Starting database backup..."

# Create backup directory
mkdir -p $BACKUP_DIR

# Check disk space (requires at least 1GB free)
AVAILABLE_SPACE=$(df -BG $BACKUP_DIR | tail -1 | awk '{print $4}' | sed 's/G//')
if [ $AVAILABLE_SPACE -lt 1 ]; then
    echo "Warning: Less than 1GB available space"
fi

# Dump database
pg_dump -U $DB_USER -d $DB_NAME > $BACKUP_FILE

# Compress backup
gzip $BACKUP_FILE

# Delete old backups
find $BACKUP_DIR -name "${DB_NAME}_*.sql.gz" -mtime +$RETENTION_DAYS -delete

# Calculate backup size
BACKUP_SIZE=$(du -h ${BACKUP_FILE}.gz | cut -f1)
echo "Backup completed: ${BACKUP_FILE}.gz ($BACKUP_SIZE)"

# Send notification
if [ -f /usr/bin/mail ]; then
    echo "Database backup completed successfully" | mail -s "Backup Report" admin@example.com
fi
