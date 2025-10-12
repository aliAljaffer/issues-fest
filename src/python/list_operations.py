"""
List operation utlities
"""

# BUG: doesn't handle empty list
def find_max(lst):
    max_val = lst[0]
    for num in lst:
        if num > max_val:
            max_val = num
    return max_val

# BUG: doesn't preserve order
def remove_duplicates(lst):
    return list(set(lst))

# BUG: doesn't handle size <= 0
def chunk_list(lst, size):
    return [lst[i:i + size] for i in range(0, len(lst), size)]

# TYPO: "flattern" instead of "flatten"
def flattern_list(nested_list):
    result = []
    for item in nested_list:
        if isinstance(item, list):
            result.extend(flattern_list(item))
        else:
            result.append(item)
    return result

# BUG: integer division loses precision
def avrerage(numbers):
    return sum(numbers) / len(numbers)
