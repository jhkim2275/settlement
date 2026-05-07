Creator Settlement API
Project Overview

This project is a backend API for creator settlement management.

It calculates monthly settlements for creators based on:

Course sales
Refunds
Platform fee

The system is built using Spring Boot, JPA, and H2 Database.

Tech Stack
Java 17
Spring Boot
Spring Data JPA
Hibernate
H2 Database (In-Memory)
JUnit 5
Features
Load JSON mock data into H2 database
Manage Creator / Course / Sale / Refund relationships
Calculate creator monthly settlements
Refund support
REST API endpoint
Service layer testing
Project Structure
controller/
service/
repository/
entity/
dto/
config/
Entity Relationships
Creator 1:N Course
Course 1:N SaleRecord
SaleRecord 1:N Refund
Settlement Calculation

Net Revenue:

\text{net}=\text{totalSale}-\text{totalRefund}

Fixed Platform Fee (20%):

\text{fee}=\text{net}\times0.2

Final Settlement:

\text{settlement}=\text{net}-\text{fee}

API
Get Monthly Settlement
GET /creators/{creatorId}/settlements?year=2025&month=3

Example:

GET /creators/creator-1/settlements?year=2025&month=3
Example Response
{
  "totalSale": 260000,
  "totalRefund": 80000,
  "net": 180000,
  "fee": 36000,
  "settlement": 144000
}
How to Run
Run Application
./gradlew bootRun

Server:

http://localhost:8080
H2 Console
http://localhost:8080/h2-console
Run Tests
./gradlew test
Assumptions
Fee rate is fixed at 20%
Refunds reduce creator revenue
Settlement is calculated monthly
Data is loaded from data.json
Test Coverage
Monthly settlement calculation
Refund deduction
Fee calculation
Creator-specific settlement filtering
Design Decisions
JPA entity relationships are used instead of manual mapping
DTO is used for API responses
Business logic is separated into Service layer
H2 in-memory database is used for simplicity
Future Improvements
Authentication / Authorization
Pagination
Production database support
Advanced refund policies
Settlement history management
Validation & exception handling