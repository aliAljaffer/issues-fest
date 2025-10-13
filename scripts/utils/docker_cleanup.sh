#!/bin/bash

# Docker Cleanup Script
# Removes old containers, images, and volumes
# May contain: volume deletion issues, running container checks, force removal problems

echo "Starting Docker cleanup..."

# Stop old containers
echo "Stopping old containers..."
OLD_CONTAINERS=$(docker ps -a -f "status=exited" -f "status=created" -q)
if [ ! -z "$OLD_CONTAINERS" ]; then
    docker rm $OLD_CONTAINERS
    echo "Removed $(echo $OLD_CONTAINERS | wc -w) containers"
fi

# Remove dangling images
echo "Removing dangling images..."
DANGLING_IMAGES=$(docker images -f "dangling=true" -q)
if [ ! -z "$DANGLING_IMAGES" ]; then
    docker rmi $DANGLING_IMAGES
    echo "Removed $(echo $DANGLING_IMAGES | wc -w) images"
fi

# Remove old images (older than 30 days)
echo "Removing old images..."
docker images --format "{{.Repository}}:{{.Tag}} {{.CreatedAt}}" | while read image date time rest; do
    # Extract date and calculate age
    IMAGE_DATE=$(date -d "$date" +%s)
    CURRENT_DATE=$(date +%s)
    AGE_DAYS=$(( ($CURRENT_DATE - $IMAGE_DATE) / 86400 ))

    if [ $AGE_DAYS -gt 30 ]; then
        echo "Removing old image: $image (${AGE_DAYS} days old)"
        docker rmi $image
    fi
done

# Remove unused volumes
echo "Removing unused volumes..."
docker volume prune -f

# Show disk usage
echo "Docker disk usage:"
docker system df

echo "Docker cleanup completed"
