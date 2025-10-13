/**
 * User Profile Component
 * Displays and edits user information
 */

class UserProfile {
  constructor(container) {
    this.container = container;
    this.user = null;
  }

  async load(userId) {
    this.user = await apiService.getUser(userId);
    this.render();
  }

  render() {
    if (!this.user) {
      this.container.innerHTML = '<p>Loading...</p>';
      return;
    }

    // XSS vulnerability: innerHTML with user data
    this.container.innerHTML = `
      <div class="profile">
        <h2>Welcome, ${this.user.username}!</h2>
        <div class="profile-bio">
          ${this.user.bio}
        </div>
        <p>Email: ${this.user.email}</p>
        <p>Member since: ${this.user.createdAt}</p>

        <div class="profile-actions">
          <button onclick="editProfile()">Edit Profile</button>
          <button onclick="deleteAccount()">Delete Account</button>
        </div>
      </div>
    `;

    this.attachEventListeners();
  }

  attachEventListeners() {
    // Using global functions instead of proper event delegation
    window.editProfile = () => this.edit();
    window.deleteAccount = () => this.delete();
  }

  async edit() {
    const newBio = prompt('Enter new bio:');

    // No validation
    if (newBio) {
      await apiService.updateUser(this.user.id, { bio: newBio });
      this.user.bio = newBio;
      this.render();
    }
  }

  async delete() {
    // No confirmation dialog
    const confirmed = confirm('Are you sure?');
    if (confirmed) {
      await apiService.deleteUser(this.user.id);
      window.location.href = '/';
    }
  }
}

export default UserProfile;
