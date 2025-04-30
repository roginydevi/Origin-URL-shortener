# URL Shortener

A simple URL shortener application built with Spring Boot that allows to create shortened versions of long URLs.

## Features Included

1. Shorten URL when provided with a long URL
2. Redirect to Original URL when a short URL is requested
3. Get Original URL Info when a short URL is passed 
4. Use an in-memory store 
5. Validate the incoming URL format
6. Error handling 
7. Rest Assured test case

NOTE: Used same domain (localhost:8080 in built tomcat) for both 
long url and short url. Hence to differentiate long url 
should contain 4 path variables and short url will contain one variable.

## Technologies Used

- Java 17
- Spring Boot 
- Spring Data JPA
- H2 Database
- Rest Assured

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher

## Installation and Setup


1. Build the application:
   ```
   mvn clean install
   ```

## Running the Application

Run the application using Maven:
```
mvn org.springframework.boot:spring-boot-maven-plugin:run
```


The application will be available at: http://localhost:8080

## API Endpoints

- `POST /shorten-url` - Create a shortened URL
- `GET /{shortUrlId}` - Redirect to the original URL

