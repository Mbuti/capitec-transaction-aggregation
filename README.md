Transaction Aggregation API (Event-Driven Banking System)

A Spring Boot 3.x event-driven backend system that aggregates, processes, and categorizes financial transactions from multiple sources using Kafka, MySQL, and Java 21. Designed with a banking contact center use case in mind.

🚀 Tech Stack
Java 21
Spring Boot 3.x
Spring Web
Spring Data JPA
Apache Kafka
MySQL 8
Lombok
OpenAPI (Swagger)
Docker & Docker Compose

🧠 System Overview

This system simulates a banking transaction aggregation platform where:

Customers are onboarded via REST APIs
Transactions are created via API
Transactions are published to Kafka
Kafka consumers process and categorize transactions
Processed data is stored in MySQL
APIs expose aggregated financial insights

🏗️ Architecture

Client Request
     |
     v
REST API (Spring Boot)
     |
     v
Kafka Producer
     |
     v
Kafka Topic (transactions-topic)
     |
     v
Kafka Consumer
     |
     v
Categorization Service
     |
     v
MySQL Database
     |
     v
Reporting APIs


📦 Core Features
👤 Customer Management
Create new customer
Retrieve all customers
💳 Transaction Processing
Create transaction (event published to Kafka)
Asynchronous processing via Kafka consumer
Automatic categorization (FOOD, TRANSPORT, ENTERTAINMENT, etc.)
📊 Aggregation
Customer transaction history
Spending summaries (future enhancement ready)

DB create Query

CREATE TABLE transactions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    customer_id BIGINT DEFAULT NULL,
    source VARCHAR(255) DEFAULT NULL,
    merchant VARCHAR(255) DEFAULT NULL,
    amount DECIMAL(19, 2) DEFAULT NULL,
    transaction_date DATETIME(6) DEFAULT NULL,
    category VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE customer (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_date DATETIME(6)
);

🔌 API Endpoints

👤 Customers
Create Customer
POST /api/v1/customers

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@gmail.com"
}

Get Customers
GET /api/v1/customers

💳 Transactions
Create Transaction (Kafka Event Published)

POST /api/v1/transactions
{
  "customerId": 1,
  "merchant": "Uber",
  "amount": 250.75
}

Response
{
  "transactionId": "uuid-generated-id",
  "status": "PUBLISHED"
}

⚙️ Configuration
bootstrap.properties

spring.application.name=transaction-aggregation-api

server.port=8080

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/bankingdb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update

# Kafka
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=aggregation-group
spring.kafka.consumer.auto-offset-reset=earliest

aggregation.kafka.topic=transactions-topic

🐳 Run with Docker
Start Infrastructure (MySQL + Kafka)

docker-compose up -d

Build Application
mvn clean package

🧪 Kafka Flow
Transaction API
      |
      v
Kafka Producer
      |
      v
transactions-topic
      |
      v
Kafka Consumer
      |
      v
Categorization Engine
      |
      v
MySQL Storage

🧩 Transaction Categories
FOOD
TRANSPORT
SHOPPING
UTILITIES
ENTERTAINMENT
OTHER

📘 Swagger / API Docs

Once the application is running:
http://localhost:8080/swagger-ui/index.html

🐳 Docker Compose 

version: '3.8'

services:

  mysql:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: bankingdb
    ports:
      - "3306:3306"

  zookeeper:
    image: confluentinc/cp-zookeeper:7.6.0
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181

  kafka:
    image: confluentinc/cp-kafka:7.6.0
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

📈 Future Enhancements
JWT Authentication (Bank-grade security)
Flyway database migrations
Redis caching layer
Dead Letter Queue (DLQ) for Kafka failures
Retry & Circuit Breaker (Resilience4j)
Audit logging for transactions
OpenTelemetry + Grafana monitoring
Kubernetes deployment (Helm charts)
Multi-source ingestion with WebClient/Feign

Multi-source ingestion with WebClient/Feign


👨‍💻 Author Notes

This project demonstrates a real-world banking-style event-driven architecture similar to systems used in contact centers and financial institutions for:

Transaction ingestion
Event streaming
Real-time processing
Scalable microservices design
