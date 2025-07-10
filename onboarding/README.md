# 🚀 Candidate Onboarding System

A robust Spring Boot backend system for managing candidate onboarding, role-based access, OTP-based registration via email using **Kafka + Redis**, and asynchronous offer notifications via **RabbitMQ** — all containerized using **Docker & Docker Compose**.

---

## 📚 Table of Contents

- [✅ Features](#-features)
- [⚙️ Tech Stack](#️-tech-stack)
- [📁 Project Structure](#-project-structure)
- [🔐 Authentication Flow](#-authentication-flow)
- [📦 API Endpoints](#-api-endpoints)
- [🐳 Docker Setup](#-docker-setup)
- [📬 Email Configuration](#-email-configuration)
- [🔮 Future Scope](#-future-scope)
- [🤝 Author](#-author)

---

## ✅ Features

### 🔐 User Authentication
- JWT-based stateless login
- OTP-based email verification using Kafka
- Redis-based OTP expiry (5 minutes)

### 👨‍💼 Candidate Management
- Create, view, update, delete candidates
- Role-based access with Spring Security

### 📧 Notification System
- RabbitMQ used to queue email notifications (e.g., sending offer letters)

### 📁 File Upload
- Upload resumes or candidate documents

### 🐳 Dockerized Services
- MySQL, Redis, Kafka, Zookeeper, RabbitMQ, Spring App

---

## ⚙️ Tech Stack

| Category            | Technology                     |
|---------------------|--------------------------------|
| Language            | Java 17                        |
| Framework           | Spring Boot 3.x                |
| Security            | Spring Security + JWT          |
| Email Service       | JavaMailSender (Gmail SMTP)    |
| Asynchronous Queue  | RabbitMQ                       |
| Stream Processing   | Apache Kafka                   |
| Caching             | Redis                          |
| ORM / DB            | Spring Data JPA + MySQL        |
| Containerization    | Docker, Docker Compose         |

---

## 📁 Project Structure

```
📦 onboarding/
 ┣ 📁 controller         → REST APIs
 ┣ 📁 service            → Business logic
 ┣ 📁 repository         → JPA Repos
 ┣ 📁 entity             → DB Models
 ┣ 📁 config             → JWT, Kafka, Redis configs
 ┣ 📁 dto                → Request/Response DTOs
 ┣ 📄 Dockerfile
 ┣ 📄 docker-compose.yml
 ┗ 📄 application-dev.properties
```

---

## 🔐 Authentication Flow

### 1️⃣ Register — Send OTP via Email
```http
POST /api/auth/register
Body:
{
  "username": "darpan",
  "email": "darpan@example.com",
  "password": "password123"
}
```
➡ Sends OTP to email via Kafka  
➡ Stores user data temporarily in Redis

---

### 2️⃣ Verify OTP — Complete Registration
```http
POST /api/auth/verify-otp?email=darpan@example.com&otp=123456
```
➡ Validates OTP from Redis  
➡ Saves user to MySQL

---

### 🔓 Login
```http
POST /api/auth/login
Body:
{
  "username": "darpan",
  "password": "password123"
}
```
➡ Returns JWT token for future requests

---

## 📦 API Endpoints

### 🔐 Auth APIs

| Method | Endpoint                 | Description                      |
|--------|--------------------------|----------------------------------|
| POST   | `/api/auth/register`     | Register and send OTP            |
| POST   | `/api/auth/verify-otp`   | Validate OTP and complete signup |
| POST   | `/api/auth/login`        | Login and receive JWT token      |

---

### 👨‍💼 Candidate APIs

> Requires: `Authorization: Bearer <JWT>`

| Method | Endpoint            | Description          |
|--------|---------------------|----------------------|
| GET    | `/api/candidates`   | Get all candidates   |
| POST   | `/api/candidates`   | Add new candidate    |
| PUT    | `/api/candidates/{id}` | Update candidate   |
| DELETE | `/api/candidates/{id}` | Delete candidate   |

---

### 📁 File Upload

| Method | Endpoint         | Description           |
|--------|------------------|-----------------------|
| POST   | `/api/upload`    | Upload document/resume |

---

## 🐳 Docker Setup

### ✅ Prerequisites
- Docker & Docker Compose
- Java 17 + Maven installed

### 🛠️ Build & Run

```bash
# 1. Build the Spring Boot JAR
./mvnw clean package

# 2. Start all services
docker-compose up --build -d
```

### 🚪 Exposed Ports

| Service     | Port |
|-------------|------|
| App (Spring)| 8080 |
| MySQL       | 3306 |
| Kafka       | 9092 |
| RabbitMQ    | 5672 |
| Rabbit UI   | 15672 |
| Redis       | 6379 |

---

## 📬 Email Configuration

In `application-dev.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

Use a **Gmail App Password**, not your real Gmail password.

---

## 🔮 Future Scope

- 🔄 Kafka consumer for login OTPs
- 📑 Swagger / OpenAPI Docs
- 🧱 Convert to Microservices
- 🔐 Role-based endpoint protection
- 🚀 Render/Railway/GitHub Actions deployment
- 💻 React frontend
- 📊 Prometheus + Grafana monitoring

---

## 🤝 Author

**👤 Darpan Salgotra**  
📧 darpan0408.be21@chitkara.edu.in  
🔗 [LinkedIn](https://linkedin.com/in/darpan-0408s)

---

> Built with ❤️ by Darpan using Spring Boot, Kafka, Redis, RabbitMQ, MySQL, and Docker.