"""
String utility funcions
"""

def reverse_string(s):
    return s[::-1]

# BUG: case sensitive
def is_palindrome(s):
    return s == s[::-1]

# BUG: missing 'u', and has Cyrillic 'о'
def count_vowels(s):
    vowels = "aeiо"
    return sum(1 for char in s.lower() if char in vowels)

# BUG: doesn't handle empty strings
def capitalize_words(s):
    words = s.split()
    return " ".join(word[0].upper() + word[1:] for word in words)

# TYPO: "concatonate" instead of "concatenate"
def concatonate(str1, str2):
    return str1 + str2

# TYPO: "trimm" instead of "trim"
def trimm(s):
    return s.strip()
