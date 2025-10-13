"""
Unit tests for validator functions
Many of these tests will fail due to bugs in the code
"""
import unittest
import sys
sys.path.insert(0, '../../src/python')

from data_validator import *

class TestValidators(unittest.TestCase):

    def test_validate_email(self):
        # This test passes
        self.assertTrue(validate_email('user@example.com'))

    def test_validate_email_multiple_at(self):
        # BUG: Should fail but passes - validator doesn't check for multiple @
        self.assertFalse(validate_email('user@@example.com'))

    def test_validate_age_negative(self):
        # BUG: Should fail but passes - negative age allowed
        self.assertFalse(validate_age(-5))

    # TYPO: "test_valdate_phone" instead of "test_validate_phone"
    def test_valdate_phone(self):
        self.assertTrue(valdate_phone('555-123-4567'))

    def test_validate_password_weak(self):
        # BUG: Weak validation - only checks length
        self.assertFalse(validate_password('123456'))

    def test_validate_url(self):
        self.assertTrue(valdate_url('https://example.com'))

if __name__ == '__main__':
    unittest.main()
