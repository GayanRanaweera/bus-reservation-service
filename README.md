# Bus Reservation Service

## Overview
This project implements a simple REST API for bus ticket reservation using Java Servlets.

---

## Features
- Check seat availability
- Reserve seats
- Price calculation
- Input validation
- In-memory storage
- Thread-safe booking

---

## Technologies Used
- Java 17
- Maven
- Servlet API
- Gson
- Tomcat 9

---

## How to Run

### 1. Build WAR
mvn clean package

### 2. Deploy
Copy WAR to:
TOMCAT_HOME/webapps/

Start Tomcat:
./startup.sh

---

## API Endpoints

### 1. Check Availability
GET /api/availability

Example:
http://localhost:8080/bus-reservation-service/api/availability?passengers=2&origin=A&destination=C

Generic format:
http://<server-ip>:<port>/bus-reservation-service/api/availability?passengers=2&origin=A&destination=C
---

### 2. Reserve Seats
POST /api/reserve

http://localhost:8080/bus-reservation-service/api/reserve

Generic format:
http://<server-ip>:<port>/bus-reservation-service/api/reserve


Body:
{
"passengers": 2,
"origin": "A",
"destination": "C",
"confirmPrice": 200
}

---

## Client Execution

Run:
java -jar client.jar

---

## Unit Tests
Run:
mvn test

---

## Improvements
- Add database
  - Replace in-memory storage with a persistent database such as MySQL or PostgreSQL to ensure data durability and support real-world usage.
- Add authentication
  - Secure APIs using JWT-based authentication to restrict access and ensure only authorized users can perform reservations.
- Add API documentation
  - Integrate Swagger/OpenAPI to provide interactive API documentation and improve developer usability.
- Improve Concurrency Handling
  - Implement fine-grained locking or optimistic locking to handle concurrent seat booking safely in a distributed environment.