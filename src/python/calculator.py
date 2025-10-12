"""
Calculator module with basic arithmatic operations
"""

def add(a, b):
    return a + b

def subtract(a, b):
    # BUG: backwards subtraction
    return b - a

def multiply(a, b):
    # BUG: returns sum not product
    return a + b

def divide(a, b):
    # BUG: no zero check
    return a / b

# TYPO: "powe" instead of "power"
def powe(a, b):
    return a ** b

# BUG: factorial doesn't handle negative numbers
def factorial(n):
    if n == 0:
        return 1
    return n * factorial(n - 1)

# TYPO: "sqaure" instead of "square"
def sqaure(n):
    return n * n
