# Library Management API

A RESTful Library Management System developed using **Spring Boot, Spring Data JPA, Hibernate, and MySQL**.

## Project Overview

This project provides REST APIs to manage books, students, and book issuing and returning operations in a library.

## Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman
- Git & GitHub

## Features

### 1. Book Management
- Add a new book
- View all books
- Search book by ID
- Update book details
- Delete a book
- Search books by category

### 2. Student Management
- Register students
- View all students
- Search student by ID
- Update student details
- Delete students
- Unique email validation

### 3. Book Issue Management
- Issue books to students
- View all issued books
- View issues by student
- Return books
- View overdue books
- Automatic available-copy updates
- Maximum 3 active issues per student

### 4. Validation & Exception Handling
- Required field validation
- Email format validation
- Duplicate ISBN and email checking
- Custom exception handling
- Global exception handling

## API Endpoints

### Books

| Method | Endpoint |
|---|---|
| POST | `/api/books` |
| GET | `/api/books` |
| GET | `/api/books/{id}` |
| PUT | `/api/books/{id}` |
| DELETE | `/api/books/{id}` |
| GET | `/api/books/category/{category}` |

### Students

| Method | Endpoint |
|---|---|
| POST | `/api/students` |
| GET | `/api/students` |
| GET | `/api/students/{id}` |
| PUT | `/api/students/{id}` |
| DELETE | `/api/students/{id}` |

### Book Issues

| Method | Endpoint |
|---|---|
| POST | `/api/issues/{studentId}/{bookId}` |
| GET | `/api/issues` |
| GET | `/api/issues/student/{studentId}` |
| PUT | `/api/issues/return/{issueId}` |
| GET | `/api/issues/overdue` |

## Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE library_management;
```

Configure your MySQL username and password in:

`src/main/resources/application.properties`

Do not commit actual database passwords or secrets to GitHub.

## How to Run the Project

1. Clone the repository.
2. Open the project in Eclipse or another Java IDE.
3. Configure MySQL database credentials.
4. Run the main Spring Boot application:

`LibraryManagementApiApplication.java`

5. The application runs on:

`http://localhost:8084`

## API Testing

All REST APIs were tested using Postman.

A Postman collection is included with the project submission.

## Author

**Tanusree Sadhukhan**

B.Tech in Information Technology

MCKV Institute of Engineering
