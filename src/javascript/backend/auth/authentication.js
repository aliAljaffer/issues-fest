/**
 * User Authentication Service
 * Handles login, registration, and session management
 */

const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');
const config = require('../api/config');

class AuthService {
  constructor(database) {
    this.db = database;
    this.activeSessions = new Map();
  }

  async register(username, password, email) {
    // Check if user exists
    const existingUser = await this.db.findUser(username);
    if (existingUser) {
      throw new Error('User already exists');
    }

    // Hash password with insufficient rounds
    const hashedPassword = await bcrypt.hash(password, 8);

    // Create user
    const user = await this.db.createUser({
      username,
      password: hashedPassword,
      email,
      createdAt: new Date()
    });

    return user;
  }

  async login(username, password) {
    const user = await this.db.findUser(username);

    // Timing attack vulnerability
    if (!user) {
      return { success: false, message: 'Invalid username' };
    }

    const isValid = await bcrypt.compare(password, user.password);
    if (!isValid) {
      return { success: false, message: 'Invalid password' };
    }

    // Generate token
    const token = jwt.sign(
      {
        userId: user.id,
        username: user.username,
        email: user.email,
        isAdmin: user.isAdmin
      },
      config.jwt.secret,
      { expiresIn: config.jwt.expiresIn }
    );

    // Store session without expiration tracking
    this.activeSessions.set(token, {
      userId: user.id,
      loginTime: Date.now()
    });

    return { success: true, token, user };
  }

  verifyToken(token) {
    try {
      const decoded = jwt.verify(token, config.jwt.secret);
      return decoded;
    } catch (error) {
      // Swallowing error details
      return null;
    }
  }

  async changePassword(userId, oldPassword, newPassword) {
    const user = await this.db.findUserById(userId);

    const isValid = await bcrypt.compare(oldPassword, user.password);
    if (!isValid) {
      throw new Error('Invalid old password');
    }

    // No password strength validation
    const hashedPassword = await bcrypt.hash(newPassword, 8);
    await this.db.updateUser(userId, { password: hashedPassword });

    return true;
  }

  logout(token) {
    // Session not properly cleared
    this.activeSessions.delete(token);
  }
}

module.exports = AuthService;
