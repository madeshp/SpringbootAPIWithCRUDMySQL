# Student Management System - PostgreSQL Version

This branch contains the Student Management System migrated from MySQL to PostgreSQL database.

## Changes Made

### 1. Dependencies Updated
- Replaced `mysql-connector-java` with `org.postgresql:postgresql`
- Updated Spring Boot JPA dialect to use PostgreSQL

### 2. Database Configuration
- **Database URL**: `jdbc:postgresql://localhost:5432/student_management_db`
- **Default Username**: `postgres`
- **Default Password**: `postgres`
- **Port**: `5432`

### 3. JPA Configuration
- **Dialect**: `org.hibernate.dialect.PostgreSQLDialect`
- **Database Platform**: `org.hibernate.dialect.PostgreSQLDialect`

## Prerequisites

1. **PostgreSQL Database Server** (version 12 or higher recommended)
2. **Java 17** or higher
3. **Maven 3.6** or higher

## Setup Instructions

### 1. Install PostgreSQL
- Download and install PostgreSQL from [https://www.postgresql.org/download/](https://www.postgresql.org/download/)
- During installation, note down the password you set for the `postgres` user

### 2. Create Database
```sql
-- Connect to PostgreSQL as postgres user
psql -U postgres

-- Create the database
CREATE DATABASE student_management_db;

-- Verify database creation
\l

-- Exit psql
\q
```

### 3. Update Configuration
If you need to change the default database credentials, update `src/main/resources/application.properties`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.url=jdbc:postgresql://localhost:5432/student_management_db
```

### 4. Run the Application
```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

## Database Schema

The application will automatically create the following table structure:

```sql
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(10),
    date_of_birth DATE,
    address VARCHAR(200),
    department VARCHAR(100),
    enrollment_year INTEGER,
    is_active BOOLEAN DEFAULT true,
    created_at DATE,
    updated_at DATE
);
```

## Sample Data

The application automatically loads sample student data on first run through the `DataLoader` class.

## API Endpoints

- **GET** `/api/students` - Get all students
- **GET** `/api/students/{id}` - Get student by ID
- **POST** `/api/students` - Create new student
- **PUT** `/api/students/{id}` - Update student
- **DELETE** `/api/students/{id}` - Delete student

## Testing

Use the provided Postman collection `Student_Management_API.postman_collection.json` to test the API endpoints.

## Migration Notes

- **ID Generation**: Changed from MySQL's `AUTO_INCREMENT` to PostgreSQL's `BIGSERIAL`
- **Data Types**: All existing data types are compatible with PostgreSQL
- **Constraints**: All validation constraints remain the same
- **Indexes**: Automatically created by Hibernate

## Troubleshooting

### Common Issues

1. **Connection Refused**: Ensure PostgreSQL service is running
2. **Authentication Failed**: Verify username/password in application.properties
3. **Database Not Found**: Create the database manually or check the URL
4. **Port Already in Use**: Change server.port in application.properties

### PostgreSQL Service Commands

**Windows:**
```cmd
# Start service
net start postgresql-x64-15

# Stop service
net stop postgresql-x64-15
```

**Linux/macOS:**
```bash
# Start service
sudo systemctl start postgresql

# Stop service
sudo systemctl stop postgresql

# Check status
sudo systemctl status postgresql
```

## Branch Information

- **Branch Name**: `postgresql`
- **Base Branch**: `main` (MySQL version)
- **Created**: [Current Date]
- **Purpose**: Database migration from MySQL to PostgreSQL
