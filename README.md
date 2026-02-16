# Merchant Analytics Platform

## Overview

This application retrieves merchant transaction data, supports dynamic filtering, and provides aggregated summaries including Month-To-Date (MTD) and month-by-month metrics.

The backend is built using Spring Boot and follows a clean layered architecture with reusable filtering and aggregation logic.

---

## Tech Stack

**Backend**
- Java 21
- Spring Boot 3
- Maven
- JUnit 5

**Frontend**
- Planned (Day 2)

---

## Features Implemented

### 1. Transaction Retrieval

Endpoint:

```
GET /api/transactions
```

Supports optional filtering by:

- `cardBrand` (VISA, MASTERCARD, AMEX, DISCOVER)
- `status` (APPROVED, DECLINED)
- `declineReasonCode`

Example:

```
GET /api/transactions?cardBrand=VISA
GET /api/transactions?status=DECLINED
GET /api/transactions?status=DECLINED&cardBrand=MASTERCARD
```

---

### 2. Month-To-Date (MTD) Summary

Endpoint:

```
GET /api/summary/mtd
```

Supports same optional filters.

Returns:

- totalTransactions
- totalApproved
- totalDeclined
- totals grouped by cardBrand
- totals grouped by declineReasonCode

Example:

```
GET /api/summary/mtd?status=DECLINED
```

---

### 3. Month-by-Month Summary

Endpoint:

```
GET /api/summary/monthly
```

Returns aggregated metrics grouped by Year-Month (e.g., 2026-01, 2026-02).

Supports optional filters.

Example:

```
GET /api/summary/monthly?cardBrand=VISA
```

---

## Running the Application

Navigate to the backend directory:

```
cd backend
```

Run the application:

```
.\mvnw.cmd spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## Running Tests

Execute:

```
.\mvnw.cmd test
```

Includes:

- Unit tests for filtering logic
- Integration test for summary API
- Spring context load test

---

## Architecture Notes

### Backend Structure

- Controller layer handles HTTP requests
- Service layer contains business logic
- DTO layer isolates response models
- Filtering logic centralized in `TransactionService`
- Aggregation logic dynamically built on filtered dataset

### Design Decisions

- Used `OffsetDateTime` for ISO timestamp support
- Used enums for type safety (`CardBrand`, `TransactionStatus`)
- JSON file used instead of database for simplicity
- Reused filtering logic in summary endpoints to avoid duplication

### Tradeoffs

- In-memory data loading (no database)
- No pagination implemented (not required)
- No caching layer

### Future Improvements

- Add database persistence (PostgreSQL)
- Add caching (Redis)
- Add OpenAPI documentation
- Add Docker support
- Implement React frontend

---

## Status

Backend implementation complete with:

- Dynamic filtering
- Filter-aware aggregation
- Unit and integration tests
- Clean layered architecture
