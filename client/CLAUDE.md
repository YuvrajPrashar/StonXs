# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Start dev server (hot reload)
npm run dev

# Production build
npm run build

# Lint (zero warnings policy — --max-warnings 0)
npm run lint

# Preview production build
npm run preview
```

There are no tests in the client. The backend lives in `../datalayer` and must be running for API calls to work (see `../datalayer/CLAUDE.md`).

## Architecture

**StonXs client** is a React 18 SPA built with Vite, Tailwind CSS, MUI components, React Router DOM v6, and Axios. It is the frontend for a stock trading simulation platform.

### Routing

All routes are defined in `src/App.jsx` as a single `routes` array. The layout (`<Navbar />`, `<Footer />`) wraps all routes globally.

### Directory layout

- `src/pages/` — full-page route components (one per route)
- `src/Components/` — reusable UI components
- `src/Utils/Data.js` — static list of 20 Indian stocks (used as local fallback/seed data, not from the API)
- `src/assets/` — PNG images for mutual fund / investment category cards

### API & authentication

The backend base URL is hardcoded as `http://localhost:8080/stonks/`.

- **Public endpoints**: `/api-v1/...` (no auth required — stock lookups, login, signup)
- **Protected endpoints**: `/auth/api-v1/...` (require JWT)

After login, four values are stored in `localStorage`: `token`, `userId`, `watchlistId`, `portfolioId`. Protected pages guard themselves with a direct `window.location.href = "/login"` redirect (not React Router) when these are missing.

JWT is sent as a bare `Authorization` header (not `Bearer` prefixed):
```js
const config = { headers: { Authorization: localStorage.getItem("token") } };
```

### UI conventions

- Styling is Tailwind-first; MUI (`@mui/material`, `@mui/icons-material`) is used for icons and select inputs.
- `src/Components/Modal.jsx` is the shared feedback modal — all pages use it to display API success/error messages.
- Trade buy/sell state in `TradeForm` uses `useRef` for quantity/price inputs (not controlled state).
