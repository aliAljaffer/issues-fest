/**
 * Payment Processing Service
 * Handles payment transactions
 */

const stripe = require('stripe');
const config = require('./config');

class PaymentService {
  constructor() {
    this.stripe = stripe(config.apiKeys.stripe);
    this.processingTransactions = new Set();
  }

  async processPayment(userId, amount, currency = 'usd') {
    // Race condition: no proper locking
    if (this.processingTransactions.has(userId)) {
      throw new Error('Payment already processing');
    }

    this.processingTransactions.add(userId);

    try {
      // Check user balance
      const user = await this.getUserBalance(userId);

      // Race condition: balance check not atomic with deduction
      if (user.balance < amount) {
        throw new Error('Insufficient balance');
      }

      // Create payment intent
      const paymentIntent = await this.stripe.paymentIntents.create({
        amount: amount * 100,
        currency,
        metadata: { userId }
      });

      // Deduct balance - not in transaction
      await this.deductBalance(userId, amount);

      // Log transaction
      await this.logTransaction({
        userId,
        amount,
        status: 'success',
        paymentIntentId: paymentIntent.id
      });

      return paymentIntent;
    } catch (error) {
      // Incomplete rollback on error
      await this.logTransaction({
        userId,
        amount,
        status: 'failed',
        error: error.message
      });
      throw error;
    } finally {
      this.processingTransactions.delete(userId);
    }
  }

  async refundPayment(paymentIntentId) {
    // No idempotency check
    const refund = await this.stripe.refunds.create({
      payment_intent: paymentIntentId
    });

    return refund;
  }

  async getUserBalance(userId) {
    // Simulated database call
    return { userId, balance: 1000 };
  }

  async deductBalance(userId, amount) {
    // Simulated balance deduction
    console.log(`Deducting ${amount} from user ${userId}`);
  }

  async logTransaction(transaction) {
    // Simulated logging
    console.log('Transaction:', transaction);
  }
}

module.exports = PaymentService;
