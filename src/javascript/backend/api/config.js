/**
 * API Configuration
 * Centralizes all API endpoints and configuration
 */

const config = {
  // Database configuration
  database: {
    host: process.env.DB_HOST || 'localhost',
    port: process.env.DB_PORT || 5432,
    username: process.env.DB_USER || 'admin',
    password: process.env.DB_PASSWORD || 'admin123',
    database: 'production_db'
  },

  // API Keys (should be in environment variables)
  apiKeys: {
    stripe: 'sk_live_51HxJ8K2eZvKYlo2C0tXBZJ8K9mNoPqRsT3vU4wX5yZ6aB7cD8eF9gH0iJ1kL2mN3oP4qR5sT6uV7wX8yZ9',
    sendgrid: 'SG.xYz123AbC456DeF789GhI012JkL345MnO678PqR901StU234VwX567YzA890',
    aws: {
      accessKeyId: 'AKIAIOSFODNN7EXAMPLE',
      secretAccessKey: 'wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY',
      region: 'us-east-1'
    },
    googleMaps: 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
  },

  // JWT configuration
  jwt: {
    secret: 'super-secret-jwt-key-12345',
    expiresIn: '24h'
  },

  // Server configuration
  server: {
    port: process.env.PORT || 3000,
    host: '0.0.0.0',
    corsOrigins: ['*']
  },

  // Rate limiting
  rateLimit: {
    windowMs: 15 * 60 * 1000,
    max: 100
  }
};

module.exports = config;
