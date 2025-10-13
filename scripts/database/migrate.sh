#!/bin/bash

# Database Migration Script
# Applies database schema changes
# May contain: rollback issues, transaction problems, backup gaps

DB_NAME="production_db"
DB_USER="postgres"
DB_HOST="localhost"
MIGRATIONS_DIR="./migrations"
MIGRATION_LOG="/var/log/migrations.log"

echo "Starting database migration..."

# Check if migrations directory exists
if [ ! -d "$MIGRATIONS_DIR" ]; then
    echo "Migrations directory not found: $MIGRATIONS_DIR"
    exit 1
fi

# Get current schema version
CURRENT_VERSION=$(psql -U $DB_USER -h $DB_HOST -d $DB_NAME -t -c "SELECT version FROM schema_version ORDER BY version DESC LIMIT 1" | xargs)

echo "Current schema version: $CURRENT_VERSION"

# Find pending migrations
PENDING_MIGRATIONS=$(find $MIGRATIONS_DIR -name "*.sql" -type f | sort)

for migration in $PENDING_MIGRATIONS; do
    MIGRATION_VERSION=$(basename $migration .sql | cut -d'_' -f1)

    if [ "$MIGRATION_VERSION" -gt "$CURRENT_VERSION" ]; then
        echo "Applying migration: $migration"

        # Apply migration
        psql -U $DB_USER -h $DB_HOST -d $DB_NAME -f $migration

        if [ $? -eq 0 ]; then
            # Update schema version
            psql -U $DB_USER -h $DB_HOST -d $DB_NAME -c "INSERT INTO schema_version (version, applied_at) VALUES ($MIGRATION_VERSION, NOW())"
            echo "Migration $MIGRATION_VERSION applied successfully" | tee -a $MIGRATION_LOG
        else
            echo "Migration $MIGRATION_VERSION failed!" | tee -a $MIGRATION_LOG
            exit 1
        fi
    fi
done

echo "All migrations completed"
