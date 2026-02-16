# Merchant Analytics Platform

A full-stack analytics dashboard built with:

- Spring Boot (Java 21)
- React + TypeScript (Vite)
- In-memory transaction processing
- REST-based filtering & aggregation

---

## Architecture Overview

### Backend

- Layered architecture:
  - Controller → Service → Model
- Transactions loaded from JSON at startup
- All filtering performed in service layer
- Aggregations reuse filtered datasets
- Supports dynamic query parameters:
  - cardBrand
  - status
  - declineReasonCode

Endpoints:

GET /api/summary/mtd  
GET /api/summary/monthly  

---

### Frontend

- Built with React + TypeScript
- Component-based structure:
  - FilterBar
  - MTDSummary
  - MonthlySummary
- Axios-based API abstraction
- Environment-based API configuration
- Loading and error handling implemented

Environment variable:

VITE_API_BASE=http://localhost:8080/api

---

## Design Decisions

- Filtering logic centralized in backend service to avoid duplication.
- Aggregation functions operate on already-filtered datasets.
- Frontend kept thin and declarative.
- API base configured via environment variable for deploy flexibility.
- UI structured as dashboard-style summary cards.

---

## Assumptions

- Transactions are loaded in-memory (no database required).
- Data volume assumed small-to-medium.
- Focused on clarity, correctness, and separation of concerns.

---

## How to Run

### Backend

cd backend  
mvn spring-boot:run  

### Frontend

cd frontend  
npm install  
npm run dev  

---

## Future Improvements

- Pagination for transaction listing
- Database integration
- Authentication
- Chart visualizations
- Dockerization
- CI/CD pipeline

---

## Summary

This implementation prioritizes:

- Clean architecture
- Reusable filtering logic
- Proper separation of concerns
- Environment-driven configuration
- Production-aware frontend behavior

