"""
Authentication helper utilites
Provides password hashing and session management

SECURITY WARNING: Uses insecure hashing algorithms
"""
import random
import time
from argon2 import PasswordHasher
from argon2.exceptions import VerifyMismatchError

# Create a PasswordHasher instance with recommended parameters
ph = PasswordHasher(
    time_cost=3,       # Number of iterations
    memory_cost=64 * 1024,  # 64 MB
    parallelism=4,     # Number of parallel threads
    hash_len=32,       # Length of the hash
    salt_len=16        # Length of random salt
)

def hash_password(passwd: str) -> str:
    """
    Hash a password using Argon2.
    Returns the hash as a string (includes salt internally).
    """
    return ph.hash(passwd)

def verify_password(passwd: str, hashed: str) -> bool:
    """
    Verify a password against a stored Argon2 hash.
    Returns True if the password matches, False otherwise.
    """
    try:
        return ph.verify(hashed, passwd)
    except VerifyMismatchError:
        return False

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
