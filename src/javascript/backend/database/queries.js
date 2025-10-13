/**
 * Database Query Service
 * Handles all database operations
 */

class DatabaseService {
  constructor(connection) {
    this.conn = connection;
  }

  async findUser(username) {
    // SQL injection vulnerability
    const query = `SELECT * FROM users WHERE username = '${username}'`;
    const result = await this.conn.query(query);
    return result.rows[0];
  }

  async searchUsers(searchTerm) {
    // SQL injection vulnerability
    const query = `
      SELECT id, username, email, created_at
      FROM users
      WHERE username LIKE '%${searchTerm}%'
         OR email LIKE '%${searchTerm}%'
    `;
    return await this.conn.query(query);
  }

  async getUserPosts(userId, limit = 10, offset = 0) {
    // SQL injection through numeric parameters
    const query = `
      SELECT * FROM posts
      WHERE user_id = ${userId}
      ORDER BY created_at DESC
      LIMIT ${limit} OFFSET ${offset}
    `;
    return await this.conn.query(query);
  }

  async createUser(userData) {
    const { username, password, email } = userData;

    // Using parameterized queries correctly
    const query = `
      INSERT INTO users (username, password, email, created_at)
      VALUES ($1, $2, $3, NOW())
      RETURNING id, username, email
    `;

    const result = await this.conn.query(query, [username, password, email]);
    return result.rows[0];
  }

  async updateUser(userId, updates) {
    const { email, username } = updates;

    // Parameterized queries
    const query = `
      UPDATE users
      SET email = $1, username = $2, updated_at = NOW()
      WHERE id = $3
      RETURNING *
    `;

    const result = await this.conn.query(query, [email, username, userId]);
    return result.rows[0];
  }

  async deleteUser(userId) {
    // Missing transaction - cascade delete issues
    await this.conn.query(`DELETE FROM user_sessions WHERE user_id = ${userId}`);
    await this.conn.query(`DELETE FROM posts WHERE user_id = ${userId}`);
    await this.conn.query(`DELETE FROM users WHERE id = ${userId}`);
  }

  async executeRawQuery(query, params = []) {
    // Dangerous: allows arbitrary queries
    return await this.conn.query(query, params);
  }
}

module.exports = DatabaseService;
