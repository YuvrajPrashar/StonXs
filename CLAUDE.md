# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Monorepo structure

StonXs is a full-stack stock trading simulation platform split into two sub-projects:

| Directory | Stack | Purpose |
|---|---|---|
| `client/` | React 18 + Vite + Tailwind + MUI | SPA frontend |
| `datalayer/` | Spring Boot 3.3.1 (Java 22) + PostgreSQL | REST backend |

Each sub-project has its own `CLAUDE.md` with commands and architecture details. Read those first when working in a single layer.

## Running the full stack

Start backend first (requires PostgreSQL on `localhost:5432`, database `Stonks`, schema `practice`):

```bash
cd datalayer && ./mvnw spring-boot:run
```

Then start the frontend:

```bash
cd client && npm run dev
```

The frontend calls `http://localhost:8080/stonks/` and the dev server runs on port 5173 (or 5174 if 5173 is taken).

## Cross-cutting conventions

- **Auth flow**: Frontend stores `token`, `userId`, `watchlistId`, `portfolioId` in `localStorage` after login. The token is sent as a bare `Authorization` header (no `Bearer` prefix). Backend verifies it in `JwtFillter` (note intentional typo in class name).
- **Transaction settlement**: Trades are created with status `"In-Progress"` and settled every 5 seconds by a `@Scheduled` job in `TransactionService`. When testing trade flows end-to-end, wait ~5 s for status to flip to `"Completed"` or `"Cancelled"`.
- **Soft deletes**: Stocks (and related entities) use an `isDeleted` boolean — always filter on this before returning data.
- **Admin checks**: Admin-only operations (stock CRUD) are guarded by a `user.isAdmin()` boolean in the service layer, not by Spring Security roles.
- **Typos that are load-bearing**: `repositary/` (package name), `JwtFillter` (class name) — do not rename these without updating all references.
