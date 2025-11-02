# Authentication Documentation

## Overview
This document provides an overview of the **authentication system** implemented in this project.  
It explains how users are registered, authenticated, and managed within sessions.

---

## User Registration
Users can register using the `registerUser()` method.  
> ⚠️ **Note:** The system currently stores passwords in plain text, which is a serious security risk and should be addressed in future updates.

---

## Login Process
The login process validates user credentials against stored records to confirm identity.

### Example
```java
Authentication auth = new Authentication();
auth.registerUser("john", "password123");
boolean success = auth.authenticate("john", "password123");
```
---
## Session Management
After a successful login, a session is created and remains active until the user logs out.  
Future improvements may include implementing session expiration and token-based authentication for stronger security.

---

## Known Issues
- Passwords are **not hashed or encrypted**.  
- **Session expiration** and renewal mechanisms are missing.  
- No **rate limiting** on login attempts.  

---

## Recommendations
To enhance security and maintain best practices:

- Use strong password hashing algorithms (e.g., **bcrypt**, **Argon2**).  
- Implement session expiration and token renewal mechanisms.  
- Add rate limiting to prevent brute-force attacks.  
- Regularly update authentication logic to follow modern security standards.  
- Review and audit authentication workflows periodically.

