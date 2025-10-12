"""
Test cases for Python calculator module
Note: Many tests will fail due to bugs!
"""
import sys
sys.path.insert(0, '../src/python')
from calculator import add, subtract, multiply

def test_add():
    assert add(2, 3) == 5
    print("Add test passd")

# This test will fail - subtract is backwards
def test_subtract():
    assert subtract(10, 3) == 7
    print("Subtract test passd")

# This test will fail - multiply returns sum
def test_multiply():
    assert multiply(3, 4) == 12
    print("Multipy test passd")

if __name__ == "__master__":
    test_add()
    test_subtract()
    test_multiply()
    print("All tests passd!")
