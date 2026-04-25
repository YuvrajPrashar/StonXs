# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build
./mvnw clean install

# Run (requires PostgreSQL running)
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=DatalayerApplicationTests
```

## Architecture

**StonXs** is a Spring Boot 3.3.1 (Java 22) REST backend for a stock trading simulation platform. All endpoints are served under the `/stonks` context path.

### Layer structure

```
controller/   → REST endpoints, HTTP status mapping
service/      → Business logic, @Transactional
entity/       → JPA entities (PostgreSQL, schema: practice)
dto/          → Request/response shapes
repositary/   → Spring Data JPA repositories (note the typo — it's intentional in the codebase)
Configuration/ → Security, CORS, JWT filter, scheduler
MapperUtil    → Manual entity↔DTO conversion (no MapStruct)
```

### Domain model

- **User** owns one **Portfolio** and one **Watchlist**
- **Portfolio** holds a cash `balance`, `investedValue`, and a list of **Holdings** (stock + quantity pairs)
- **Transactions** are created with status `"In-Progress"` and settled asynchronously by `TransactionService.TransactionManager()`, a `@Scheduled(fixedRate = 5000)` job that processes pending transactions, updates Holdings and Portfolio balances, and marks status `"Completed"` or `"Cancelled"`
- **Stock** has a `category`, `sector`, `currentPrice`, `marketCap`, and soft-delete via `isDeleted`
- Soft deletes are used throughout — check `isDeleted` before returning entities

### Security

- Stateless JWT auth via `JwtFillter` (note typo — class is named with double-l)
- Routes under `/auth/**` require a valid Bearer token; all other routes are public
- Public endpoints: `POST /stonks/api-v1/signup`, `POST /stonks/api-v1/login`
- Admin operations (create/update/delete stocks) check `user.isAdmin()` in the service layer — there is no Spring Security role for this, it's a manual boolean check

### Key conventions

- `MapperUtil` is a `@Component` injected wherever entity↔DTO conversion is needed — add new mapping methods there, don't do inline conversions in services
- Services return `String` messages for write operations; controllers inspect those strings for keywords (`"not found"`, `"failed"`, `"already registered"`) to select the HTTP status code
- CORS is configured for `localhost:5173` and `localhost:5174` (Vite frontend dev servers)
- JWT secret and DB credentials live in `application.properties` — do not commit changes that expose these

### Database

PostgreSQL on `localhost:5432`, database `Stonks`, schema `practice`. DDL is managed by Hibernate (`ddl-auto=update`).
