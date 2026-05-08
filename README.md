# Creator Settlement API

## Project Overview

This project is a backend API for creator settlement management.

It calculates monthly settlements for creators based on:

* Course sales
* Refunds
* Platform fee deduction

The system is built using Spring Boot, JPA, and H2 Database.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 Database (In-Memory)
* JUnit 5

---

## Features

* Load JSON mock data into H2 database
* Manage Creator / Course / Sale / Refund relationships
* Monthly sale/refund count calculation
* Validation for invalid creator and invalid month
* Partial refund support
* Cross-month refund settlement handling
* REST API endpoint
* Service layer testing

---

## Project Structure

```text
controller/
service/
repository/
entity/
dto/
config/
```

---

## Entity Relationships

```text
Creator 1:N Course
Course 1:N SaleRecord
SaleRecord 1:N Refund
```

---

## Settlement Calculation

### Net Revenue

```text
net = totalSale - totalRefund
```

### Platform Fee (20%)

```text
fee = net * 0.2
```

### Final Settlement

```text
settlement = net - fee
```

---

## API

### Get Monthly Settlement

```http
GET /creators/{creatorId}/settlements?year=2025&month=3
```

Example:

```http
GET /creators/creator-1/settlements?year=2025&month=3
```

---

## Example Response

```json
{
  "totalSale": 260000,
  "totalRefund": 110000,
  "net": 150000,
  "fee": 30000,
  "settlement": 120000,
  "saleCount": 4,
  "refundCount": 2
}
```

---

## How to Run

### Run Application

```bash
./gradlew bootRun
```

Server:

```text
http://localhost:8080
```

---

## H2 Console

```text
http://localhost:8080/h2-console
```

---

## Run Tests

```bash
./gradlew test
```

---

## Assumptions

* Fee rate is fixed at 20%
* Refunds reduce creator revenue
* Settlement is calculated monthly
* Data is loaded from `data.json`

---

## Test Coverage

* Monthly settlement calculation
* Refund deduction
* Fee calculation
* Partial refund calculation
* Cross-month refund handling
* Empty settlement month handling
* Invalid creator validation
* Invalid month validation
* Sale/refund count calculation

---

## Additional Test Scenarios

Additional test cases were added beyond the provided sample data:

- Partial refund case
  - Refund amount smaller than original payment
- Cross-month refund case
  - Sale in January and refund in February
- Empty settlement month
  - Creator with no sales/refunds in requested month
- Invalid creator request
- Invalid month request
- Sale/refund count verification

These scenarios were added to ensure settlement accuracy and edge-case handling.

---

## Design Decisions

* JPA entity relationships are used instead of manual mapping
* DTO is used for API responses
* Business logic is separated into Service layer
* H2 in-memory database is used for simplicity

---

## Future Improvements

* Authentication / Authorization
* Pagination
* Production database support
* Advanced refund policies
* Settlement history management
* Validation & exception handling
