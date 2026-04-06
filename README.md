# Experiment 7 — Role-Based Authorization (RBAC) using Spring Boot and Spring Security

## Overview
This project implements **Role-Based Access Control (RBAC)** using Spring Boot and Spring Security.
Users are assigned roles (`ROLE_USER` or `ROLE_ADMIN`), and API endpoints are protected based on those roles.

---

## Tech Stack
| Layer       | Technology                        |
|-------------|-----------------------------------|
| Backend     | Spring Boot 3.5.13                |
| Security    | Spring Security (HTTP Basic Auth) |
| Database    | H2 In-Memory                      |
| ORM         | Spring Data JPA                   |
| Build Tool  | Maven                             |

---

## Project Structure
src/
└── main/
└── java/com/example/experiment7/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── PublicController.java
│   ├── UserController.java
│   └── AdminController.java
├── entity/
│   └── User.java
├── repository/
│   └── UserRepository.java
└── service/
└── CustomUserDetailsService.java
└── resources/
├── application.properties
└── data.sql
screenshots/
├── 01-login-success.png
├── 02-user-endpoint-success.png
├── 03-admin-endpoint-success.png
└── 04-access-denied.png

---

## API Endpoints

| Endpoint               | Method | Access Role     | Description                  |
|------------------------|--------|-----------------|------------------------------|
| `/api/public/hello`    | GET    | Public (anyone) | No authentication needed     |
| `/api/auth/me`         | GET    | USER, ADMIN     | Returns current logged-in user |
| `/api/user/profile`    | GET    | USER, ADMIN     | User profile info            |
| `/api/admin/dashboard` | GET    | ADMIN only      | Admin-only dashboard         |

---

## Demo Users

| Username | Password  | Role       |
|----------|-----------|------------|
| user1    | user123   | ROLE_USER  |
| admin1   | admin123  | ROLE_ADMIN |

> Passwords are BCrypt-encoded in the database.

---

## How to Run
```bash
# Clone the repo
git clone https://github.com/simar2501/Role-Based-Authorization-RBAC-using-Spring-Boot-and-Spring-Security.git
cd Role-Based-Authorization-RBAC-using-Spring-Boot-and-Spring-Security

# Run with Maven
./mvnw spring-boot:run
```

App starts on **http://localhost:8080**

---

## Postman Testing

| Test | URL | Auth | Expected |
|------|-----|------|----------|
| Public endpoint | `/api/public/hello` | None | 200 OK |
| User login | `/api/user/profile` | user1 / user123 | 200 OK |
| User denied admin | `/api/admin/dashboard` | user1 / user123 | 403 Forbidden |
| Admin access | `/api/admin/dashboard` | admin1 / admin123 | 200 OK |
| No auth | `/api/user/profile` | None | 401 Unauthorized |

---

## Access Control Rules
/api/public/**   → permitAll()
/api/user/**     → hasAnyRole("USER", "ADMIN")
/api/admin/**    → hasRole("ADMIN")
anyRequest       → authenticated()

- **401 Unauthorized** — No credentials provided
- **403 Forbidden** — Valid credentials but insufficient role

---

## Screenshots

All screenshots are in the `screenshots/` folder.

---

*Experiment 7 | CSE AI/ML | Chandigarh University*
