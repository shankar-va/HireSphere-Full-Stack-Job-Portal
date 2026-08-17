# HireSphere — Full-Stack Job Portal

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.12-6DB33F?style=for-the-badge&logo=springboot)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?style=for-the-badge&logo=hibernate)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?style=for-the-badge&logo=postgresql)
![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-0F766E?style=for-the-badge)
![Lombok](https://img.shields.io/badge/Lombok-1.18.30-BC4521?style=for-the-badge)
![REST API](https://img.shields.io/badge/API-REST-0F172A?style=for-the-badge)

### A backend-first job portal built from scratch using Spring Boot, Hibernate/JPA, and PostgreSQL.

</div>

---

## 📖 Overview

**HireSphere** is a job portal backend developed from the ground up to model the core workflows of a modern recruitment platform.

The application manages:

- Users
- Companies
- Recruiters
- Job seekers
- Resumes
- Jobs
- Job applications

The project focuses on building a clean, maintainable backend using **layered architecture**, **DTO-based API design**, **Hibernate/JPA relationships**, **MapStruct**, **Spring Data JPA Specifications**, validation, pagination, sorting, transactions, and custom exception handling.

> Authentication and authorization are planned as a separate next stage.

---

# ✨ Features

## 👤 User Management

- User registration
- User profile retrieval
- Partial profile updates
- Email and phone uniqueness validation
- Custom validation for password and phone number
- User deletion

## 🏢 Company Management

- Company registration
- Company retrieval and listing
- Company updates
- Email and phone uniqueness checks
- Company deletion
- Company–Recruiter and Company–Job relationships

## 👨‍💼 Recruiter Management

- Recruiter registration
- User and company relationship validation
- Unique employee-code validation
- Recruiter profile updates
- Recruiter deletion
- One recruiter profile per user

## 💼 Job Management

- Create job postings
- Assign jobs to companies and recruiters
- Validate recruiter/company relationship
- Salary-range validation
- Update job details
- Close jobs
- Delete jobs
- Dynamic job search
- Filtering by title, description, employment mode, location, experience, salary, vacancies, company, and recruiter
- Pagination
- Sorting
- Automatic exclusion of closed and expired jobs from normal search

## 🎓 Job Seeker Management

- Create job seeker profiles
- One job seeker profile per user
- Profile updates
- Resume association
- Search and filtering
- Experience and salary range filtering
- Preferred-location filtering
- Availability filtering
- Pagination and sorting
- Profile deletion

## 📄 Resume Management

- Create structured resumes
- Education and summary information
- Project information stored as a key-value collection
- Resume updates
- Fetch resume by ID
- Fetch resume by job seeker
- One resume per job seeker
- Resume ownership validation
- Resume deletion

## 📨 Job Applications

- Apply for jobs
- Prevent duplicate applications
- Validate job availability
- Validate application deadline
- Validate expected joining date
- Application status workflow
- Withdraw applications
- Application search
- Filtering by job, job seeker, status, and applied date
- Pagination and sorting

### Application Status Workflow

```text
APPLIED
   │
   ├──→ UNDER_REVIEW
   │       │
   │       ├──→ SHORTLISTED
   │       │       │
   │       │       ├──→ INTERVIEW
   │       │       │       │
   │       │       │       ├──→ SELECTED
   │       │       │       └──→ REJECTED
   │       │       │
   │       │       └──→ REJECTED
   │       │
   │       └──→ REJECTED
   │
   └──→ REJECTED

APPLIED / UNDER_REVIEW / SHORTLISTED / INTERVIEW
                    │
                    └──→ WITHDRAWN
```

---

# 🏗 Architecture

```text
                    REST Client / Postman
                            │
                            ▼
                    ┌───────────────┐
                    │  Controllers  │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │ Service Layer │
                    └───────┬───────┘
                            │
               ┌────────────┴────────────┐
               │                         │
               ▼                         ▼
        ┌──────────────┐        ┌─────────────────┐
        │   MapStruct  │        │  Specifications │
        │    Mappers   │        │ Dynamic Search  │
        └──────────────┘        └─────────────────┘
               │
               ▼
        ┌───────────────┐
        │ Spring Data   │
        │ JPA Repository│
        └───────┬───────┘
                │
                ▼
        ┌───────────────┐
        │ Hibernate/JPA │
        └───────┬───────┘
                │
                ▼
        ┌───────────────┐
        │  PostgreSQL   │
        └───────────────┘
```

---

# 🧩 Core Domain Relationships

```text
                         ┌────────────┐
                         │    User    │
                         └─────┬──────┘
                               │
                    ┌──────────┴──────────┐
                    │                     │
                    ▼                     ▼
             ┌────────────┐       ┌────────────┐
             │ Recruiter  │       │ JobSeeker  │
             └─────┬──────┘       └─────┬──────┘
                   │                    │
                   │                    ├──────────► Resume
                   │                    │
                   │                    └──────────► JobApplication
                   │
                   ▼
             ┌────────────┐
             │   Company  │
             └─────┬──────┘
                   │
                   ▼
                  Jobs
                   │
                   ▼
            JobApplication
```

### Main relationships

- `User` → `Recruiter`: One-to-One
- `User` → `JobSeeker`: One-to-One
- `Company` → `Recruiter`: One-to-Many
- `Company` → `Job`: One-to-Many
- `Recruiter` → `Job`: One-to-Many
- `JobSeeker` → `Resume`: One-to-One
- `JobSeeker` → `JobApplication`: One-to-Many
- `Job` → `JobApplication`: One-to-Many
- `JobApplication` → `Job`: Many-to-One
- `JobApplication` → `JobSeeker`: Many-to-One

---

# 🛠 Tech Stack

## Backend

- Java 17
- Spring Boot 3.3.12
- Spring Web
- Spring Data JPA
- Hibernate/JPA
- Jakarta Bean Validation
- PostgreSQL
- Maven

## Development Utilities

- Lombok
- MapStruct 1.6.3
- Spring Boot DevTools

## Testing

- Postman
- Spring Boot Test

---

# 🔧 Backend Concepts Implemented

- RESTful API design
- Layered architecture
- DTO pattern
- Repository pattern
- Service layer
- Service interfaces and implementations
- MapStruct entity/DTO mapping
- Lombok
- JPA entity relationships
- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@ElementCollection`
- Enum persistence with `EnumType.STRING`
- Cascade types
- Orphan removal
- Lazy loading
- Transactions
- Spring Data JPA
- Derived query methods
- JPA Specifications
- Dynamic filtering
- Pagination
- Sorting
- Validation
- Custom exceptions
- Global exception handling
- Database constraints and unique relationships

---

# 📂 Project Structure

```text
src/
└── main/
    └── java/
        └── com/HireSphere — Full-Stack Job Portal/driver/
            │
            ├── Main.java
            │
            ├── controller/
            │   ├── CompanyController.java
            │   ├── RecruiterController.java
            │   ├── JobController.java
            │   ├── JobApplicationController.java
            │   ├── JobSeekerController.java
            │   ├── ResumeController.java
            │   └── UserController.java
            │
            ├── dto/
            │   ├── request/
            │   └── response/
            │
            ├── model/
            │   └── entity/
            │       ├── User.java
            │       ├── Company.java
            │       ├── Recruiter.java
            │       ├── Job.java
            │       ├── JobSeeker.java
            │       ├── JobApplication.java
            │       └── Resume.java
            │
            ├── mapper/
            ├── repository/
            ├── service/
            │   └── impl/
            ├── specification/
            ├── enumeration/
            ├── exception/
            │   ├── GlobalExceptionHandler.java
            │   └── customExceptions/
            └── validation/
```

---

# 🗄️ Data Model

Core PostgreSQL-backed tables include:

```text
user_info
Organisations
recruiter_info
job_info
job_seeker_info
resume_info
resume_projects
job_application_info
```

The model uses generated sequences for identifiers and database-level uniqueness constraints for important relationships such as preventing duplicate applications.

---

# 🔎 Dynamic Search

Dynamic filtering is implemented with **Spring Data JPA Specifications**.

### Jobs

Search/filter by:

- Title
- Description
- Employment mode
- Preferred location
- Experience
- Salary
- Vacancies
- Company
- Recruiter

### Job Seekers

Search/filter by:

- Headline
- Experience
- Current salary
- Expected salary
- Preferred location
- Qualification
- Availability

Search results support **pagination and sorting**.

---

# ✅ Validation & Exception Handling

The API uses:

- Jakarta Bean Validation
- Custom validation annotations
- Domain-specific runtime exceptions
- REST exception handlers
- Structured error responses

Examples include:

```text
CompanyNotFoundException
JobNotFoundException
RecruiterNotFoundException
JobSeekerNotFoundException
JobApplicationDuplicationException
JobApplicationStatusUnchangeableException
JobInvalidSalaryRangeException
ResumeNotFoundException
ResumeOwnershipException
```

---

# 🚀 Getting Started

## Prerequisites

- Java 17+
- Maven
- PostgreSQL
- IntelliJ IDEA / Eclipse / VS Code
- Postman

## 1. Clone the Repository

```bash
git clone https://github.com/shankar-va/Naukri-Clone.git
cd Naukri-Clone
```

## 2. Configure PostgreSQL

Create a PostgreSQL database and configure the datasource in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hiresphere
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Never commit real database credentials to source control.

## 3. Build

```bash
mvn clean install
```

## 4. Run

```bash
mvn spring-boot:run
```

Or run the `Main` class from your IDE.

---

# 🧪 API Testing

The REST API was tested using **Postman**.

Main endpoint groups include:

```text
/api/user
/api/companies
/api/recruiter
/api/job
/api/jobApplication
/api/jobSeeker
/api/resume
```

Typical operations include:

```text
POST   /api/user
GET    /api/user/{id}
PATCH  /api/user

POST   /api/companies
GET    /api/companies/{id}
PUT    /api/companies
DELETE /api/companies/{id}

POST   /api/job/register
GET    /api/job/get/{id}
GET    /api/job/get/search
PATCH  /api/job/update
PATCH  /api/job/close/{id}

POST   /api/jobApplication/apply
GET    /api/jobApplication/get/{id}
GET    /api/jobApplication/get/search
PATCH  /api/jobApplication/withdraw/{id}

POST   /api/jobSeeker
GET    /api/jobSeeker/{id}
PUT    /api/jobSeeker
GET    /api/jobSeeker/search

POST   /api/resume/register
GET    /api/resume/get/{id}
GET    /api/resume/get/jobSeeker/{id}
PUT    /api/resume/update
DELETE /api/resume/delete/{id}
```

---

# 🧠 What This Project Taught Me

The project was built incrementally rather than generated as a complete application.

The main learning areas were:

- Designing entity relationships
- Understanding bidirectional JPA mappings
- Working with cascade types and orphan removal
- DTO-to-entity mapping with MapStruct
- Partial updates using `@MappingTarget`
- Dynamic JPA Specifications
- Filtering `@ElementCollection`
- Pagination and sorting
- Business-driven application status transitions
- Relationship ownership validation
- Transaction management
- Validation and custom exception handling

The goal was not simply to create endpoints, but to understand how a structured Spring Boot backend works from the HTTP layer down to PostgreSQL.

---

# 🔐 Security Roadmap

Authentication and authorization with **Spring Security and JWT** are intentionally planned as a separate next stage.

Planned additions:

- Authentication
- JWT-based authorization
- Role-based access control
- Recruiter-specific permissions
- Job seeker-specific permissions
- Protected endpoints

---

# 🚧 Future Enhancements

- Spring Security + JWT
- Role-based authorization
- Frontend integration
- Automated unit and integration tests
- OpenAPI / Swagger documentation
- Docker support
- Production configuration profiles
- Cloud deployment
- Improved logging and observability
- Advanced job search and recommendation features

---

# 👨‍💻 Author

### Shankar V

Aspiring Software Developer | Java | DSA | Backend Development

- GitHub: https://github.com/shankar-va
- LinkedIn: https://www.linkedin.com/in/shankar-v-6306a32a0/
- LeetCode: https://leetcode.com/u/D77YQlo87I/e

---

# ⭐ Support

If you find the project useful or interesting, consider giving the repository a ⭐.

**Repository:**  
https://github.com/shankar-va/Naukri-Clone

---

<div align="center">

### Built from the ground up with Java, Spring Boot, Hibernate/JPA & PostgreSQL.

</div>
