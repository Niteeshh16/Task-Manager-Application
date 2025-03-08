This is a full-stack task management application built using Spring Boot (Microservices), React, MySQL,Tailwind CSS, and Redux Toolkit. The application allows users to create, update, delete, and manage tasks with authentication and additional features.

Tech Stack
Backend:
Spring Boot (Microservices Architecture)
Spring Security & JWT (Authentication)
Spring Cloud Eureka & API Gateway (Service Discovery & Routing)
MySQL (Database - Separate databases for each microservice)
JPA/Hibernate (ORM for database interactions)

Frontend:
React.js (UI Framework)
Redux Toolkit (State Management)
Material-UI (MUI) (UI Components)
Tailwind CSS (Styling)
Setup Instructions

1. Fork and Clone the Repository

Fork the repository and clone it to your local machine:

 git clone https://github.com/your-username/task-manager.git

Navigate to the project directory:

 cd task-manager

2. Backend Setup

2.1 Create MySQL Databases

Ensure MySQL is installed and running. Create a separate database for each microservice:

 CREATE DATABASE user_service;
 CREATE DATABASE task_service;
 CREATE DATABASE submission_service;

2.2 Configure application.yml in Each Module

Update database configurations in application.yml (or .properties) in each microservice module to match your MySQL credentials:

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/user_service
    username: root
    password: your_password

(Similarly, update for task_service and submission_service.)

2.3 Start Eureka Server

Navigate to the Eureka Server directory and run:

 cd eureka-server
 mvn spring-boot:run

2.4 Start API Gateway

 cd api-gateway
 mvn spring-boot:run

2.5 Start Microservices

Run the following commands for each microservice (User Service, Task Service, Submission Service):

 cd service-name
 mvn spring-boot:run

3. Frontend Setup

3.1 Install Dependencies

Navigate to the frontend directory:

 cd frontend

Install required dependencies:

 npm install

3.2 Install Material-UI and Tailwind CSS

 npm install @mui/material @emotion/react @emotion/styled tailwindcss

3.3 Start the Frontend Server

 npm start

The frontend will be available at: http://localhost:3000/

Running the Full Application

Start MySQL Server.

Run Eureka Server (mvn spring-boot:run in eureka-server directory).

Run API Gateway.

Run each Microservice.

Start the Frontend (npm start).

Once all services are running, visit: http://localhost:3000/

Deployment

Frontend can be deployed using Vercel/Netlify.

Backend can be deployed using Render/Heroku.

Assumptions

The user has Node.js (v16+) and npm installed.

MySQL is installed and accessible on localhost:3306.

Ports are properly configured in the API Gateway and Microservices.

Challenges & Solutions

1. Spring Boot Learning Curve

Being new to Spring Boot, faced challenges while setting up the project.

Resolved issues using AI platforms and YouTube tutorials.

2. JWT Authentication Issues

Faced errors in JWT authentication setup.

Solved by debugging and following various online resources.

3. Task Service Database Connection Issue

Despite identical configurations, Task Service failed to connect to the database.

Solved by switching to a different MySQL Connector/J version.

4. React Development Challenges

Faced React-specific issues while working with state management and UI.

Resolved by referencing other projects and YouTube tutorials.

Special Features

✅ Task Image Upload (Attach photos to tasks)

✅ Dark Mode (Black theme for better UX)

✅ Task Prioritization (High, Medium, Low)

✅ Task Deadline Management

✅ Fully Responsive UI (Material-UI + Tailwind CSS)

Contribution Guidelines

Fork the repository.

Create a new branch (feature-branch-name).

Commit changes and push to your fork.

Create a Pull Request (PR) to the main repository.
