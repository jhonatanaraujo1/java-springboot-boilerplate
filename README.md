# Boilerplate JAVA 17 SpringBoot 

**Boilerplate JAVA 17 SpringBoot** - A boilerplate for Spring Boot with JWT authentication, email functionalities, and
much more. ⚡🚀⭐

## Overview

**Boilerplate JAVA 17 SpringBoot** is a Spring Boot boilerplate that
provides a solid foundation for building web applications with
JWT authentication, CORS filters, email functionalities, and more.
It comes with a set of features and configurations more fast as possible your development.
I will commit some improvements over time here and I will also
create a boilerplate in Kotlin as the next objective,
if possible send improvements to this project, I will insert crud abstraction here too and in Kotlin crud it will also
be something very similar to this ⚡🚀⭐

## Features

- JWT authentication
- CORS filter configuration
- Token provider and JWT authentication filter
- User roles with permissions
- Email functionalities (account creation, email verification, password reset)
- Example controller, service, repository, entity, and domain
- Exception handling
- Seeder for initial user creation with example permissions
- Lombok for simplified mapping and reduced boilerplate code
- Dockerfile for containerization
- Docker Compose for easy database setup
- Supports Java 17 and Spring Boot 2.5

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Docker (for database containerization)
- SMTP server credentials for email setup (optional)

### Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/jhonatanaraujo1/java-springboot-boilerplate.git

2. **Navigate to the project directory:**

   ```bash
   cd exemple.backend

3. **Build the project:**

   ```bash
   mvn clean install

4. **Execute Docker-compose:**

   ```bash
   docker-compose up

3. **Just Run :**

   ```bash
   mvn spring-boot:run

## Configuration

The configuration for the project can be found in the application.properties file. Update the necessary properties, such
as database connection details and SMTP server credentials,
as per your requirements.

## Usage

### API Endpoints

The application provides several API endpoints for authentication,
user management, and example functionalities.

### Example Controller

The project includes an example controller (ExampleController.java) that demonstrates the usage of JWT authentication
and basic CRUD operations.

### Seeder

The seeder (SeedEventListner.java) is responsible for creating initial users with example permissions. Modify it to suit
your needs.

### Exception Handling

The project includes a robust exception handling mechanism. Custom exceptions are thrown and handled appropriately to
provide meaningful responses.

### Libraries

The project uses various libraries to enhance functionality and simplify development. Key libraries include:

- Spring Boot
- Lombok
- Mapstruct
- Spring Security
- JWT
- Hibernate
- Thymeleaf
- and more...

### Mapper

The project includes an example mapper (ExampleMapper.java) that uses Mapstruct and Lombok for simplified mapping
between entities and DTOs.

### Docker

The Dockerfile (Dockerfile) is included for containerization. Build and run the Docker image to deploy the application
in a containerized environment.

### Email Setup

To enable email functionalities, provide the necessary SMTP server credentials in the application.properties file. Two
example server configurations are provided, choose one based on your requirements.

### Unit Testing

Unfortunately, the test for this section did not pass ❌, but it is crucial for all projects. I'll do it later.

### Contributing

If you find this project helpful or valuable, consider giving it a star! ⭐ Also, follow me on LinkedIn for updates and
more.

# IMPORTANT !!!!

If you encounter any bugs, please feel free to reach out by sending a message or creating a pull request.⚡🚀