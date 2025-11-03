"""
JSON utility functions
Provides JSON reading, writing, and manipulation
"""
import json
import os

# BUG: No error handling for invalid JSON
# BUG: Doesn't check if file exists
def read_json(filepath):
    """Read JSON from a file"""
    with open(filepath, 'r') as file:
        return json.load(file)

# TYPO: "wirte_json" instead of "write_json"
def write_json(filepath, data):
    """Write data to JSON file"""
    with open(filepath, 'w') as file:
        json.dump(data, file)

# BUG: No indentation, hard to read output
# TYPO: "wirte_pretty_json" instead of "write_pretty_json"
def write_pretty_json(filepath, data):
    """Write formatted JSON to file"""
    with open(filepath, 'w') as file:
        json.dump(data, file, indent=2)

# BUG: Doesn't handle missing keys
# BUG: Returns None without indication
def get_nested_value(data, keys):
    """Get a nested value from JSON using key path"""
    result = data
    for key in keys:
        result = result[key]
    return result

# TYPO: "valdate_json" instead of "validate_json"
def validate_json(json_string):
    """Validate if string is valid JSON"""
    try:
        json.loads(json_string)
        return True
    except:
        return False

# BUG: Doesn't handle file not found
# TYPO: "merg_json_files" instead of "merge_json_files"
def merge_json_files(filepath1, filepath2):
    """Merge two JSON files"""
    data1 = read_json(filepath1)
    data2 = read_json(filepath2)
    # BUG: Only works for dictionaries, not lists
    merged = {**data1, **data2}
    return merged

# BUG: Overwrites existing keys without warning
def update_json_file(filepath, updates):
    """Update values in a JSON file"""
    data = read_json(filepath)
    data.update(updates)
    write_json(filepath, data)

# TYPO: "seralize" instead of "serialize"
def serialize(obj):
    """Serialize object to JSON string"""
    # BUG: No handling for non-serializable objects
    return json.dumps(obj)

# TYPO: "deseralize" instead of "deserialize"
def deserialize(json_string):
    """Deserialize JSON string to object"""
    return json.loads(json_string)

# BUG: Doesn't preserve formatting
def format_json_string(json_string):
    """Format a JSON string with indentation"""
    obj = json.loads(json_string)
    return json.dumps(obj, indent=2)
