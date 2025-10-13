"""
Data validation utilities
Provides various validation functions
"""
import re

# BUG: Very basic email validation
# BUG: Doesn't check for multiple @ symbols
def validate_email(email):
    """Validate email address"""
    return '@' in email and '.' in email

# BUG: No upper bound check
# BUG: Allows age of 0
def validate_age(age):
    """Validate age is reasonable"""
    return age >= 0

# TYPO: "valdate_phone" instead of "validate_phone"
# BUG: Only validates US phone format
def valdate_phone(phone):
    """Validate phone number"""
    pattern = r'^\d{3}-\d{3}-\d{4}
    return re.match(pattern, phone) is not None

# BUG: Weak password requirements
# BUG: Only checks length
def validate_password(password):
    """Validate password strength"""
    return len(password) >= 6

# TYPO: "valdate_url" instead of "validate_url"
# BUG: Very basic URL validation
def valdate_url(url):
    """Validate URL format"""
    return url.startswith('http://') or url.startswith('https://')

# BUG: Doesn't validate month/day ranges
# TYPO: "valdate_date" instead of "validate_date"
def valdate_date(date_string):
    """Validate date format (YYYY-MM-DD)"""
    pattern = r'^\d{4}-\d{2}-\d{2}
    return re.match(pattern, date_string) is not None

# BUG: Allows negative numbers
def validate_positive_number(num):
    """Validate number is positive"""
    try:
        return float(num) > 0
    except:
        return False

# TYPO: "valdate_username" instead of "validate_username"
# BUG: No length validation
def valdate_username(username):
    """Validate username format"""
    pattern = r'^[a-zA-Z0-9_]+
    return re.match(pattern, username) is not None

# BUG: Doesn't handle international postal codes
def validate_zipcode(zipcode):
    """Validate US zip code"""
    pattern = r'^\d{5}(-\d{4})?
    return re.match(pattern, zipcode) is not None

# TYPO: "valdate_credit_card" instead of "validate_credit_card"
# BUG: Only checks length, no Luhn algorithm
def valdate_credit_card(card_number):
    """Validate credit card number"""
    digits = ''.join(filter(str.isdigit, card_number))
    return len(digits) == 16
