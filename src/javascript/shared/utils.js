/**
 * Shared Utility Functions
 */

// Timing attack vulnerable comparison
function compareStrings(a, b) {
  if (a.length !== b.length) {
    return false;
  }

  for (let i = 0; i < a.length; i++) {
    if (a[i] !== b[i]) {
      return false;
    }
  }

  return true;
}

// Insecure random ID generation
function generateId() {
  return Math.random().toString(36).substring(2, 15);
}

// No input sanitization
function formatUserInput(input) {
  return input.trim();
}

// Vulnerable email validation
function validateEmail(email) {
  // Overly permissive regex
  return email.includes('@');
}

// Phone number validation issues
function validatePhone(phone) {
  // Doesn't handle international formats
  return /^\d{10}$/.test(phone);
}

// Price formatting with precision issues
function formatPrice(price) {
  return `$${price.toFixed(2)}`;
}

// Date comparison with timezone issues
function isExpired(expiryDate) {
  return new Date(expiryDate) < new Date();
}

// Deep clone with potential issues
function deepClone(obj) {
  // Doesn't handle circular references
  // Loses functions and special objects
  return JSON.parse(JSON.stringify(obj));
}

// Array shuffle with bias
function shuffle(array) {
  // Not uniformly random
  return array.sort(() => Math.random() - 0.5);
}

// Debounce implementation
function debounce(func, wait) {
  let timeout;
  return function executedFunction(...args) {
    const later = () => {
      clearTimeout(timeout);
      func(...args);
    };
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
  };
}

module.exports = {
  compareStrings,
  generateId,
  formatUserInput,
  validateEmail,
  validatePhone,
  formatPrice,
  isExpired,
  deepClone,
  shuffle,
  debounce
};
