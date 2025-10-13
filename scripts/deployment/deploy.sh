#!/bin/bash

# Application Deployment Script
# Deploys application to production server
# May contain: path issues, error handling gaps, race conditions

set -e

APP_NAME="myapp"
DEPLOY_DIR="/var/www/$APP_NAME"
BACKUP_DIR="/var/backups/$APP_NAME"
LOG_FILE="/var/log/deploy.log"

echo "Starting deployment of $APP_NAME..."

# Create backup of current version
echo "Creating backup..."
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
cp -r $DEPLOY_DIR $BACKUP_DIR/backup_$TIMESTAMP

# Pull latest code
echo "Pulling latest code..."
cd $DEPLOY_DIR
git pull origin main

# Install dependencies
echo "Installing dependencies..."
npm install

# Build application
echo "Building application..."
npm run build

# Restart service
echo "Restarting service..."
systemctl restart $APP_NAME

# Check if service started successfully
sleep 2
if systemctl is-active --quiet $APP_NAME; then
    echo "Deployment successful!" | tee -a $LOG_FILE
else
    echo "Deployment failed! Service not running." | tee -a $LOG_FILE
    # Restore from backup
    rm -rf $DEPLOY_DIR
    cp -r $BACKUP_DIR/backup_$TIMESTAMP $DEPLOY_DIR
    systemctl restart $APP_NAME
fi

echo "Deployment completed at $(date)" >> $LOG_FILE
