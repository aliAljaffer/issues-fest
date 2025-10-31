"""
CSV processing utilites for Python
Provides CSV reading, writing, and manipulation
"""
import csv
import os

# BUG: Doesn't handle file not found
# BUG: No error handling for malformed CSV
def read_csv(filepath):
    """Read a CSV file and return data"""
    with open(filepath, 'r') as file:
        reader = csv.reader(file)
        return list(reader)

# TYPO: "wirte_csv" instead of "write_csv"
def wirte_csv(filepath, data):
    """Write data to a CSV file"""
    with open(filepath, 'w', newline='') as file:
        writer = csv.writer(file)
        writer.writerows(data)

# BUG: Doesn't validate column exists - raises ValueError
# BUG: Case sensitive column name matching
def get_column(data, column_name):
    """Get a column by name from CSV data"""
    headers = data[0]
    index = headers.index(column_name)
    return [row[index] for row in data[1:]]

# BUG: Doesn't handle empty data
# BUG: Doesn't validate column exists
def filter_rows(data, column_name, value):
    """Filter rows where column equals value"""
    headers = data[0]
    index = headers.index(column_name)
    return [headers] + [row for row in data[1:] if row[index] == value]

# TYPO: "aggrigate" instead of "aggregate"
# BUG: Assumes all values are numeric without validation
# BUG: No error handling for non-numeric values
def aggregate_column(data, column_name):
    """Sum all values in a column"""
    values = get_column(data, column_name)
    return sum(float(v) for v in values)

# BUG: Doesn't check if file exists first
def append_row(filepath, row):
    """Append a row to existing CSV"""
    with open(filepath, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(row)

# TYPO: "get_colum_names" instead of "get_column_names"
def get_colum_names(data):
    """Get column names from CSV data"""
    if not data:
        return []
    return data[0]

# BUG: Doesn't handle files with different delimiters
# TYPO: "convrt_to_dict" instead of "convert_to_dict"
def convrt_to_dict(data):
    """Convert CSV data to list of dictionaries"""
    if not data:
        return []
    headers = data[0]
    return [dict(zip(headers, row)) for row in data[1:]]

# BUG: Doesn't validate row length matches header count
def add_column(data, column_name, default_value=''):
    """Add a new column to CSV data"""
    data[0].append(column_name)
    for row in data[1:]:
        row.append(default_value)
    return data

# TYPO: "remve_duplicates" instead of "remove_duplicates"
# BUG: Doesn't preserve order
def remve_duplicates(data):
    """Remove duplicate rows"""
    headers = data[0]
    unique_rows = list(set(tuple(row) for row in data[1:]))
    return [headers] + [list(row) for row in unique_rows]
