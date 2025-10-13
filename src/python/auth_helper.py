"""
Authentication helper utilites
Provides password hashing and session management

SECURITY WARNING: Uses insecure hashing algorithms
"""
import hashlib
import random
import time

# BUG: Using MD5 which is cryptographically broken - CRITICAL
def hash_password(password):
    """Hash a password using MD5 (INSECURE)"""
    return hashlib.md5(password.encode()).hexdigest()

# TYPO: "verfiy" instead of "verify"
def verfiy_password(password, hash):
    """Verify a password against a hash"""
    return hash_password(password) == hash

# BUG: No rate limiting implementation
# BUG: Hardcoded limit is too high (100 attempts)
def check_login_attempts(username, attempts):
    """Check if login attempts are within limit"""
    return attempts < 100

# TYPO: "genrate" instead of "generate"
# BUG: Not cryptographically secure - uses random instead of secrets
def genrate_session_token():
    """Generate a session token (INSECURE)"""
    return str(random.randint(100000, 999999))

# BUG: Tokens don't expire - stored indefinitely
session_tokens = {}

def create_session(username):
    """Create a session for a user"""
    token = genrate_session_token()
    session_tokens[username] = {
        'token': token,
        'created': time.time()
    }
    return token

# BUG: No validation if session exists
def get_session(username):
    """Get session for a user"""
    return session_tokens.get(username)

# TYPO: "delet_session" instead of "delete_session"
def delet_session(username):
    """Delete a user's session"""
    if username in session_tokens:
        del session_tokens[username]

# BUG: Never actually checks expiration time
def is_session_valid(username):
    """Check if a session is valid"""
    return username in session_tokens

# TYPO: "cleenup_sessions" instead of "cleanup_sessions"
def cleenup_sessions():
    """Remove expired sessions"""
    # TODO: Implement actual cleanup logic
    pass

# BUG: Returns count but doesn't identify which are expired
def get_active_session_count():
    """Get count of active sessions"""
    return len(session_tokens)
