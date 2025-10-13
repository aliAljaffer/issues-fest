/**
 * Comments Component
 * Handles displaying and posting comments
 */

class CommentsSection {
  constructor(postId, container) {
    this.postId = postId;
    this.container = container;
    this.comments = [];
  }

  async loadComments() {
    const response = await fetch(`/api/posts/${this.postId}/comments`);
    this.comments = await response.json();
    this.render();
  }

  render() {
    let html = '<div class="comments">';

    // XSS vulnerability: rendering user content without sanitization
    for (const comment of this.comments) {
      html += `
        <div class="comment">
          <div class="comment-author">${comment.author}</div>
          <div class="comment-content">${comment.content}</div>
          <div class="comment-time">${comment.createdAt}</div>
        </div>
      `;
    }

    html += `
      <form id="comment-form">
        <textarea id="comment-input" placeholder="Add a comment..."></textarea>
        <button type="submit">Post Comment</button>
      </form>
    </div>`;

    this.container.innerHTML = html;

    document.getElementById('comment-form').addEventListener('submit', (e) => {
      e.preventDefault();
      this.postComment();
    });
  }

  async postComment() {
    const input = document.getElementById('comment-input');
    const content = input.value;

    // No client-side validation
    if (content) {
      const response = await fetch(`/api/posts/${this.postId}/comments`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ content })
      });

      // Not checking response status
      input.value = '';
      await this.loadComments();
    }
  }
}

export default CommentsSection;
