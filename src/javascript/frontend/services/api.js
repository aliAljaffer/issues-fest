/**
 * Frontend API Service
 * Handles all API communication
 */

class ApiService {
  constructor() {
    this.baseURL = 'https://api.example.com';
    // API key exposed in frontend code
    this.apiKey = 'pk_live_51HxJ8K2eZvKYlo2C0tXBZJ8K9mNoPqRsT3vU4wX5yZ6';
    this.token = null;
  }

  setToken(token) {
    this.token = token;
    // Storing token in localStorage without encryption
    localStorage.setItem('authToken', token);
  }

  getToken() {
    if (!this.token) {
      this.token = localStorage.getItem('authToken');
    }
    return this.token;
  }

  async request(endpoint, options = {}) {
    const url = `${this.baseURL}${endpoint}`;

    const headers = {
      'Content-Type': 'application/json',
      'X-API-Key': this.apiKey
    };

    if (this.token) {
      headers['Authorization'] = `Bearer ${this.token}`;
    }

    try {
      const response = await fetch(url, {
        ...options,
        headers: { ...headers, ...options.headers }
      });

      // Not checking response status properly
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('API Error:', error);
      // Swallowing errors
      return null;
    }
  }

  async login(username, password) {
    return this.request('/login', {
      method: 'POST',
      body: JSON.stringify({ username, password })
    });
  }

  async getUser(userId) {
    // No caching, repeated requests
    return this.request(`/users/${userId}`);
  }

  async updateUser(userId, data) {
    return this.request(`/users/${userId}`, {
      method: 'PUT',
      body: JSON.stringify(data)
    });
  }

  async uploadFile(file) {
    // No file validation
    const formData = new FormData();
    formData.append('file', file);

    return this.request('/upload', {
      method: 'POST',
      body: formData,
      headers: {}
    });
  }
}

// Singleton pattern
const apiService = new ApiService();
export default apiService;
