#!/bin/bash

# Batch File Processing Script
# Processes files in parallel with rate limiting
# May contain: race conditions, resource limits, error accumulation

INPUT_DIR="/data/input"
OUTPUT_DIR="/data/output"
PROCESSED_DIR="/data/processed"
ERROR_DIR="/data/errors"
MAX_PARALLEL=4

echo "Starting batch file processing..."

# Create directories
mkdir -p $OUTPUT_DIR $PROCESSED_DIR $ERROR_DIR

# Function to process a single file
process_file() {
    local file=$1
    local basename=$(basename "$file")

    echo "Processing: $basename"

    # Simulate processing (replace with actual logic)
    if grep -q "ERROR" "$file"; then
        echo "Error found in $basename"
        mv "$file" "$ERROR_DIR/"
        return 1
    fi

    # Transform file
    cat "$file" | tr '[:lower:]' '[:upper:]' > "$OUTPUT_DIR/$basename"

    # Move to processed
    mv "$file" "$PROCESSED_DIR/"

    echo "Completed: $basename"
    return 0
}

# Export function for parallel execution
export -f process_file
export OUTPUT_DIR PROCESSED_DIR ERROR_DIR

# Process files in parallel
find $INPUT_DIR -type f -name "*.txt" | xargs -P $MAX_PARALLEL -I {} bash -c 'process_file "$@"' _ {}

# Count results
PROCESSED_COUNT=$(ls -1 $PROCESSED_DIR | wc -l)
ERROR_COUNT=$(ls -1 $ERROR_DIR | wc -l)

echo "Processing completed:"
echo "  Processed: $PROCESSED_COUNT files"
echo "  Errors: $ERROR_COUNT files"
