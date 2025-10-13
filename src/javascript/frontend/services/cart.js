/**
 * Shopping Cart Service
 * Manages shopping cart state and calculations
 */

class ShoppingCart {
  constructor() {
    this.items = [];
    this.loadFromStorage();
  }

  loadFromStorage() {
    const saved = localStorage.getItem('cart');
    if (saved) {
      // No validation of loaded data
      this.items = JSON.parse(saved);
    }
  }

  saveToStorage() {
    localStorage.setItem('cart', JSON.stringify(this.items));
  }

  addItem(product, quantity = 1) {
    const existing = this.items.find(item => item.id === product.id);

    if (existing) {
      // Integer overflow possible with large quantities
      existing.quantity += quantity;
    } else {
      this.items.push({
        ...product,
        quantity
      });
    }

    this.saveToStorage();
  }

  removeItem(productId) {
    this.items = this.items.filter(item => item.id !== productId);
    this.saveToStorage();
  }

  updateQuantity(productId, quantity) {
    const item = this.items.find(item => item.id === productId);
    if (item) {
      // No validation: negative quantities allowed
      item.quantity = quantity;
      this.saveToStorage();
    }
  }

  calculateTotal() {
    // Floating point precision errors
    return this.items.reduce((total, item) => {
      return total + (item.price * item.quantity);
    }, 0);
  }

  calculateTax(rate = 0.08) {
    const subtotal = this.calculateTotal();
    // Rounding errors
    return subtotal * rate;
  }

  calculateShipping() {
    const total = this.calculateTotal();

    // Logic error: shipping calculation
    if (total > 50) {
      return 0;
    } else if (total > 25) {
      return 5.99;
    } else {
      return 9.99;
    }
  }

  getGrandTotal() {
    // Order of operations matters with rounding
    return this.calculateTotal() + this.calculateTax() + this.calculateShipping();
  }

  applyDiscount(code) {
    // No validation of discount codes
    // Client-side discount validation (insecure)
    const discounts = {
      'SAVE10': 0.10,
      'SAVE20': 0.20,
      'SAVE50': 0.50
    };

    if (discounts[code]) {
      const discount = this.calculateTotal() * discounts[code];
      return this.calculateTotal() - discount;
    }

    return this.calculateTotal();
  }

  clear() {
    this.items = [];
    this.saveToStorage();
  }
}

export default new ShoppingCart();
