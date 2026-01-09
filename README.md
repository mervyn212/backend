# Secure Backend System (Work in Progress)

⚠️ **Work in Progress**  
This repository is part of an ongoing backend research and engineering project
(completion target: March 2026).

## Overview
Backend system built using Kotlin and Spring Boot, focusing on performance,
security, and scalability.

## Tech Stack
- Kotlin, Spring Boot
- PostgreSQL
- Redis (cache-aside strategy)
- JWT Authentication + RBAC
- GraphQL & REST APIs
- Docker, Docker Compose

## Features
- Secure JWT-based authentication
- Role-based access control (RBAC)
- Redis caching integrated at service layer
- REST and GraphQL APIs sharing business logic
- Dockerized local development environment

## Current Status
- Core REST APIs implemented
- JWT authentication working
- Redis caching integrated
- Ongoing refactoring for DTO usage and security hardening

## How to Run (Local)
```bash
docker compose up --build
