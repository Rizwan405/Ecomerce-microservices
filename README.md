# Ecomerce-microservices
This project implements a modern e-commerce system using microservices architecture. Built with Spring Boot 3 , Spring Cloud , and integrated with Keycloak  for secure authentication and authorization, this system showcases best practices in microservices development. 
Architecture 

The architecture is divided into several key components: 
1. API Gateway 

    Purpose : Single entry point for all client requests.
    Technology : Spring Cloud Gateway.
    Features :
        Routes requests to microservices.
        Integrates with Keycloak for OAuth2-based security.
         
     

2. Microservices 

    Customer Service : Manages customer profiles (MongoDB).
    Product Service : Handles product catalog (Dockerized PostgreSQL).
    Order Service : Manages orders (Dockerized PostgreSQL).
    Payment Service : Processes payments and communicates asynchronously (Dockerized PostgreSQL).
    Notification Service: Send email when order and payment is confirmed 
     

4. Message Broker (Kafka) 

    Purpose : Asynchronous communication between services.
    Topics :
        Send Payment Confirmation
        Send Order Confirmation
         
     

5. Notification Service 

    Purpose : Sends notifications to customers.
    Integration : Consumes Kafka messages.
     

6. Service Discovery (Eureka Server) 

    Purpose : Dynamic service registration and discovery.
    Technology : Spring Cloud Netflix Eureka.
     

7. Configuration Server 

    Purpose : Centralized configuration management.
    Technology : Spring Cloud Config Server.
     

8. Distributed Tracing (Zipkin) 

    Purpose : Request monitoring and tracing.
    Technology : OpenTracing with Zipkin.
     

9. Databases 

    MongoDB : Used by Customer Service.
    PostgreSQL (Dockerized) : Used by Product, Order, and Payment Services.
     

10. Security (Keycloak) 

    Purpose : OAuth2-based authentication and authorization.
    Integration : Secures API Gateway and microservices.
     

Workflow 

    Order Placement : 
        Client → API Gateway → Order Service → Payment Service → Kafka → Notification Service.
         

    Asynchronous Updates : 
        Kafka → Order Service → Customer Service.
         

    Monitoring : 
        Zipkin collects and visualizes request traces.
         
     

Technologies 

    Backend : Spring Boot 3, Spring Cloud
    Database : MongoDB, PostgreSQL (Dockerized)
    Message Broker : Apache Kafka
    Service Discovery : Eureka
    Configuration : Spring Cloud Config Server
    Distributed Tracing : Zipkin
    Security : Keycloak
    Containerization : Docker
     
