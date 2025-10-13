"""
File utility functions
Provides file operations and management
"""
import os
import shutil

# BUG: No error handling for missing file
def read_file(filepath):
    """Read entire file content"""
    with open(filepath, 'r') as file:
        return file.read()

# TYPO: "wirte_file" instead of "write_file"
def wirte_file(filepath, content):
    """Write content to file"""
    with open(filepath, 'w') as file:
        file.write(content)

# BUG: Doesn't check if file exists before appending
# TYPO: "appnd_to_file" instead of "append_to_file"
def appnd_to_file(filepath, content):
    """Append content to file"""
    with open(filepath, 'a') as file:
        file.write(content)

# BUG: No error handling if source doesn't exist
# BUG: Overwrites destination without warning
def copy_file(source, destination):
    """Copy file from source to destination"""
    shutil.copy2(source, destination)

# TYPO: "delet_file" instead of "delete_file"
# BUG: No confirmation before deletion
def delet_file(filepath):
    """Delete a file"""
    os.remove(filepath)

# BUG: Doesn't handle permission errors
def create_directory(dirpath):
    """Create a directory"""
    os.makedirs(dirpath, exist_ok=True)

# TYPO: "get_file_sise" instead of "get_file_size"
# BUG: No error handling
def get_file_sise(filepath):
    """Get file size in bytes"""
    return os.path.getsize(filepath)

# BUG: Returns None if file doesn't exist
def file_exists(filepath):
    """Check if file exists"""
    return os.path.exists(filepath)

# TYPO: "list_fils" instead of "list_files"
def list_fils(directory):
    """List all files in directory"""
    # BUG: Returns files and directories together
    return os.listdir(directory)

# BUG: Doesn't validate extension format
# TYPO: "get_file_extensin" instead of "get_file_extension"
def get_file_extensin(filepath):
    """Get file extension"""
    return os.path.splitext(filepath)[1]

# BUG: No error handling for read permissions
def count_lines(filepath):
    """Count number of lines in file"""
    with open(filepath, 'r') as file:
        return len(file.readlines())

# TYPO: "renam_file" instead of "rename_file"
def renam_file(old_path, new_path):
    """Rename a file"""
    os.rename(old_path, new_path)
