# Authentication Documention

## Overview
This document describes the authentification system implementd in the project.

## User Registation

Users can register using the `registerUser` method. The system curently stores passwords in plain text (THIS IS A SECURITY ISSUE).

## Login Proccess

The login proces validates user credentials against stored records.

### Example
```java
Authentication auth = new Authentication();
auth.registerUser("john", "password123");
boolean success = auth.authentcate("john", "password123");
```

## Session Managment

Sessions are created upon successfull login and remaster actve until logout.

## Known Issuse

- No password hashing implemnted
- No session expiraton
- No rate limitng on login attempts
