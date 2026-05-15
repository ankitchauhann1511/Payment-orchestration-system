# Payment Orchestration System

## Project Overview

This project is a fintech-based Payment Orchestration System built using Spring Boot and MySQL.

The system intelligently routes payments between multiple payment gateways such as:
- Razorpay
- Stripe
- PayU
- UPI

It supports:
- intelligent routing
- gateway failover
- retry logic
- idempotency
- webhook ingestion
- reconciliation
- refund handling
- transaction lifecycle management

---

# Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- Postman
- GitHub

---

# Features

## Multi Gateway Routing
Routes payments dynamically based on transaction amount and gateway availability.

## Gateway Health Monitoring
Tracks gateway UP/DOWN status.

## Automatic Failover
Automatically switches to another gateway if the preferred gateway fails.

## Retry Logic
Retries failed payment attempts automatically.

## Idempotency
Prevents duplicate payments using unique idempotency keys.

## Transaction State Machine
Handles:
- PENDING
- SUCCESS
- FAILED
- REFUNDED

## Refund System
Supports payment refund processing.

## Webhook Ingestion Pipeline
Processes webhook events from gateways.

## Reconciliation Engine
Matches internal payment status with gateway status.

## UPI Simulation
Supports simulated UPI transactions and failover scenarios.

---

# Architecture Flow

Client
↓
Payment Controller
↓
Payment Service
↓
Routing Service
↓
Gateway Selection
↓
Payment Gateway
↓
Database + Webhooks

---

# APIs

## Process Payment

POST /payments/process

### Request Body

```json
{
  "orderId":"ORD1001",
  "amount":1200,
  "currency":"INR",
  "idempotencyKey":"abc123"
}
```

---

## Refund Payment

POST /payments/refund/{orderId}

---

## Payment Status

GET /payments/status/{orderId}

---

## Gateway Down

POST /gateway/down/{gateway}

Example:
POST /gateway/down/STRIPE

---

## Gateway Up

POST /gateway/up/{gateway}

---

## Webhook Processing

POST /webhooks/payment

---

## Reconciliation

GET /reconciliation/{orderId}

---

# Security

This project uses Spring Security with Basic Authentication.

Example credentials:

Username: admin  
Password: admin123

---

# Database

MySQL Database:
payment_orchestration

---

# Future Improvements

- Kafka Integration
- Redis Caching
- Circuit Breaker
- Docker Deployment
- Kubernetes
- API Gateway
- Monitoring Dashboard

---

# Author

Ankit Chauhan# Payment Orchestration System

## Project Overview

This project is a fintech-based Payment Orchestration System built using Spring Boot and MySQL.

The system intelligently routes payments between multiple payment gateways such as:
- Razorpay
- Stripe
- PayU
- UPI

It supports:
- intelligent routing
- gateway failover
- retry logic
- idempotency
- webhook ingestion
- reconciliation
- refund handling
- transaction lifecycle management

---

# Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- Postman
- GitHub

---

# Features

## Multi Gateway Routing
Routes payments dynamically based on transaction amount and gateway availability.

## Gateway Health Monitoring
Tracks gateway UP/DOWN status.

## Automatic Failover
Automatically switches to another gateway if the preferred gateway fails.

## Retry Logic
Retries failed payment attempts automatically.

## Idempotency
Prevents duplicate payments using unique idempotency keys.

## Transaction State Machine
Handles:
- PENDING
- SUCCESS
- FAILED
- REFUNDED

## Refund System
Supports payment refund processing.

## Webhook Ingestion Pipeline
Processes webhook events from gateways.

## Reconciliation Engine
Matches internal payment status with gateway status.

## UPI Simulation
Supports simulated UPI transactions and failover scenarios.

---

# Architecture Flow

Client
↓
Payment Controller
↓
Payment Service
↓
Routing Service
↓
Gateway Selection
↓
Payment Gateway
↓
Database + Webhooks

---

# APIs

## Process Payment

POST http://localhost:8080/payments/process

### Request Body

```json
{
  "orderId":"ORD1001",
  "amount":1200,
  "currency":"INR",
  "idempotencyKey":"abc123"
}
```

---

## Refund Payment

POST http://localhost:8080/payments/refund/ORD1001

---

## Payment Status

GET http://localhost:8080/payments/status/ORD1001

---

## Gateway Down

POST /gateway/down/{gateway}

Example:
POST /gateway/down/STRIPE

---

## Gateway Up

POST /gateway/up/{gateway}

---

## Webhook Processing

POST /webhooks/payment

---

## Reconciliation

GET /reconciliation/{orderId}

---

# Security

This project uses Spring Security with Basic Authentication.

Use your configured Spring Security username and password.

Example credentials:

Username: admin  
Password: admin123

---

# Database

MySQL Database:
payment_orchestration

----

# Database Tables

- payment
- webhook_event
- gateway_health

---

# Project Modules

- Payment Processing
- Routing Engine
- Gateway Health Monitoring
- Failover System
- Retry Mechanism
- Idempotency Framework
- Refund Management
- Webhook Pipeline
- Reconciliation Engine
- UPI Simulation

---

# Future Improvements

- Kafka Integration
- Redis Caching
- Circuit Breaker
- Docker Deployment
- Kubernetes
- API Gateway
- Monitoring Dashboard

---

# Author

Ankit Chauhan
Backend Developer | Java | Spring Boot
