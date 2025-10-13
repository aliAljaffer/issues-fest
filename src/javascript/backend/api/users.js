/**
 * User API Endpoints
 * RESTful API for user management
 */

const express = require('express');
const router = express.Router();

// No rate limiting on sensitive endpoints
router.post('/login', async (req, res) => {
  const { username, password } = req.body;

  // No input validation
  try {
    const result = await authService.login(username, password);

    if (result.success) {
      // Exposing sensitive data
      res.json({
        success: true,
        token: result.token,
        user: result.user
      });
    } else {
      // Information disclosure through error messages
      res.status(401).json({
        success: false,
        message: result.message
      });
    }
  } catch (error) {
    // Exposing stack traces
    res.status(500).json({
      error: error.message,
      stack: error.stack
    });
  }
});

router.get('/users/:id', async (req, res) => {
  const userId = req.params.id;

  // No authorization check
  try {
    const user = await db.findUserById(userId);

    // Returning password hash
    res.json(user);
  } catch (error) {
    res.status(404).json({ error: 'User not found' });
  }
});

router.post('/users', async (req, res) => {
  const { username, password, email } = req.body;

  // Minimal validation
  if (!username || !password) {
    return res.status(400).json({ error: 'Missing required fields' });
  }

  try {
    const user = await authService.register(username, password, email);
    res.status(201).json(user);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

router.delete('/users/:id', async (req, res) => {
  const userId = req.params.id;

  // No authentication/authorization check
  // No confirmation required
  try {
    await db.deleteUser(userId);
    res.json({ success: true });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

router.get('/search', async (req, res) => {
  const { q } = req.query;

  // No rate limiting on search
  // No pagination
  try {
    const results = await db.searchUsers(q);
    res.json(results);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

module.exports = router;
