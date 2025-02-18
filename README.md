# Event-Driven Microservices with Apache Kafka

# Overview

This project demonstrates event-driven communication between two Spring Boot 3.4.2-based microservices—Product Service and Notification Service—using Apache Kafka (KRaft mode) for asynchronous messaging and decoupled interactions.


# Technology Stack
~~~
Java 17
Spring Boot 3.4.2
Apache Kafka (KRaft mode)
Spring Kafka
Spring Data JPA
H2 Database (for local development)
~~~

# Architecture Overview
Product Service:

Produces events when a product is created or updated.
Publishes events to the Kafka topic.

Notification Service:

Consumes product-related events from Kafka.
Processes and logs notifications based on received messages.

# Setup & Installation
Prerequisites
Ensure you have the following installed:

Java 17
Apache Kafka (or use Docker-based setup)
Maven
# Step 1: Start Kafka in KRaft Mode (Without Zookeeper)
~~~

bin/kafka-server-start.sh config/kraft/server.properties

~~~
# Step 2: Clone the Repository
~~~
https://github.com/pushpendrayadav/kafka_EDA_APP.git
~~~

# Step 3: Run Product Service

~~~
cd product-service  
mvn spring-boot:run  

~~~

# Step 4: Run notification Service

~~~
cd notification-service  
mvn spring-boot:run  

~~~

# API End Point
~~~
POST /api/products
Content-Type: application/json
{
  "title": "New Product",
  "price": 100,
  "quantity": 5
}

~~~
