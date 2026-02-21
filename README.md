# OAuth Project

This project is a full-stack web application demonstrating OAuth2 authentication. It consists of a Spring Boot backend acting as a resource server and a Nuxt.js frontend.

## Architecture

The project is a monorepo containing two main parts:

-   `oauth-backend`: A Spring Boot application that provides a REST API and is secured using OAuth2.
-   `oauth-frontend`: A Nuxt.js single-page application that consumes the backend API.

Authentication is handled via an external OAuth2 provider. The presence of `client_secret.json` suggests integration with a provider like Google.

## Backend (`oauth-backend`)

The backend is a Spring Boot application with the following key features:

-   **Framework**: Spring Boot
-   **Language**: Java 21
-   **Authentication**: Spring Security with OAuth2 Resource Server. It validates JWTs on incoming requests.
-   **API**: Exposes a REST API for the frontend.
-   **Database**: Uses Spring Data JPA for data persistence with a PostgreSQL database.

### Getting Started (Backend)

1.  **Configuration**:
    -   Make sure you have a `client_secret` from your OAuth provider in the root directory.
    -   Configure the database connection in `src/main/resources/application.properties`.
2.  **Run the application**:
    ```bash
    cd oauth-backend
    ./mvnw spring-boot:run
    ```

## Frontend (`oauth-frontend`)

The frontend is a Nuxt.js application.

-   **Framework**: Nuxt.js (with Vue.js)
-   **HTTP Client**: `axios` is used to make requests to the backend.

### Getting Started (Frontend)

1.  **Install dependencies**:
    ```bash
    cd oauth-frontend
    npm install
    ```
2.  **Run the development server**:
    ```bash
    npm run dev
    ```
    The application will be available at `http://localhost:3000`.

## Authentication Flow

1.  The user initiates the login process from the frontend.
2.  The user is redirected to the OAuth provider's login page.
3.  After successful authentication, the provider redirects the user back to the frontend with an authorization code.
4.  The frontend sends this code to the backend.
5.  The backend exchanges the code for an access token and a refresh token from the OAuth provider. It likely creates a user session and may store the tokens.
6.  The backend issues a JWT to the frontend.
7.  The frontend includes this JWT in the `Authorization` header for all subsequent requests to the backend.
8.  The backend, configured as a resource server, validates the JWT on each request to protect its API endpoints.
