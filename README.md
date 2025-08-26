# Student Management System - Spring Boot API

A comprehensive Spring Boot REST API for managing student information with full CRUD operations, built using Spring Boot 3.2.0 and MySQL database.

## 🚀 Features

- **Full CRUD Operations**: Create, Read, Update, and Delete student records
- **Data Validation**: Comprehensive input validation with custom error messages
- **Soft Delete**: Deactivate students instead of permanent deletion
- **Sample Data**: Pre-loaded sample student data for testing
- **Exception Handling**: Global exception handling with consistent error responses
- **Cross-Origin Support**: CORS enabled for frontend integration

## 🛠️ Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **Spring Data JPA**: For database operations
- **MySQL**: Database
- **Maven**: Build tool
- **Spring Validation**: Input validation

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🗄️ Database Setup

1. **Install MySQL** if not already installed
2. **Create Database** (optional - will be created automatically):
   ```sql
   CREATE DATABASE student_management_db;
   ```
3. **Update Configuration** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   ```

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd SpringbootAPIWithCRUDMySQL
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/students
```

### Endpoints

#### 1. Create Student
- **POST** `/api/students`
- **Body**: StudentRequestDTO
- **Response**: 201 Created with StudentResponseDTO

#### 2. Get All Students
- **GET** `/api/students`
- **Response**: 200 OK with List<StudentResponseDTO>

#### 3. Get Student by ID
- **GET** `/api/students/{id}`
- **Response**: 200 OK with StudentResponseDTO

#### 4. Get Student by Email
- **GET** `/api/students/email/{email}`
- **Response**: 200 OK with StudentResponseDTO

#### 5. Update Student
- **PUT** `/api/students/{id}`
- **Body**: StudentRequestDTO
- **Response**: 200 OK with StudentResponseDTO

#### 6. Delete Student
- **DELETE** `/api/students/{id}`
- **Response**: 204 No Content

#### 7. Deactivate Student
- **PATCH** `/api/students/{id}/deactivate`
- **Response**: 200 OK with StudentResponseDTO

#### 8. Activate Student
- **PATCH** `/api/students/{id}/activate`
- **Response**: 200 OK with StudentResponseDTO

#### 9. Get Active Students
- **GET** `/api/students/active`
- **Response**: 200 OK with List<StudentResponseDTO>

## 📝 Data Models

### StudentRequestDTO
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-05-15",
  "address": "123 Main St, City, State 12345",
  "department": "Computer Science",
  "enrollmentYear": 2022
}
```

### StudentResponseDTO
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "dateOfBirth": "2000-05-15",
  "address": "123 Main St, City, State 12345",
  "department": "Computer Science",
  "enrollmentYear": 2022,
  "isActive": true,
  "createdAt": "2024-01-01",
  "updatedAt": "2024-01-01"
}
```

## 🔍 Sample API Usage

### Create a New Student
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Johnson",
    "email": "alice.johnson@example.com",
    "phoneNumber": "5551234567",
    "dateOfBirth": "2001-06-20",
    "address": "789 Oak St, City, State 54321",
    "department": "Biology",
    "enrollmentYear": 2023
  }'
```

### Get All Students
```bash
curl -X GET http://localhost:8080/api/students
```

### Get Student by ID
```bash
curl -X GET http://localhost:8080/api/students/1
```

### Update Student
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe Updated",
    "email": "john.doe@example.com",
    "phoneNumber": "1234567890",
    "dateOfBirth": "2000-05-15",
    "address": "123 Main St Updated, City, State 12345",
    "department": "Computer Science",
    "enrollmentYear": 2022
  }'
```

### Delete Student
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

### Get Active Students
```bash
curl -X GET http://localhost:8080/api/students/active
```

## 🧪 Testing

### Using Postman
1. Import the collection (if available)
2. Set base URL to `http://localhost:8080`
3. Test all endpoints

### Using cURL
- All examples above use cURL commands
- Test different scenarios and edge cases

## 📊 Database Schema

The application automatically creates the following table:

```sql
CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(10),
    date_of_birth DATE NOT NULL,
    address VARCHAR(200),
    department VARCHAR(100),
    enrollment_year INT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at DATE,
    updated_at DATE
);
```

## 🔧 Configuration

### Application Properties
- **Server Port**: 8080
- **Database**: MySQL with auto-creation
- **JPA**: Hibernate with auto-DDL
- **Logging**: SQL queries and parameter binding

### Customization
- Modify `application.properties` for different database settings
- Update validation rules in DTOs
- Add new search criteria in repository

## 🚨 Error Handling

The application provides consistent error responses:

```json
{
  "status": 400,
  "message": "Validation failed",
  "timestamp": "2024-01-01T10:00:00",
  "errors": {
    "email": "Please provide a valid email address",
    "phoneNumber": "Phone number must be 10 digits"
  }
}
```

## 📈 Performance Features

- **Pagination**: Ready for implementation
- **Caching**: Can be added with Spring Cache
- **Database Indexing**: Automatic on primary and unique keys
- **Connection Pooling**: HikariCP by default

## 🔒 Security Considerations

- Input validation and sanitization
- SQL injection prevention through JPA
- CORS configuration for frontend integration
- Error message sanitization

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/student-management-system-0.0.1-SNAPSHOT.jar
```

### Docker (Future Enhancement)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/student-management-system-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the API documentation
- Review the sample data and examples

## 🔄 Future Enhancements

- [ ] Pagination support
- [ ] Advanced filtering and sorting
- [ ] File upload for student documents
- [ ] Authentication and authorization
- [ ] Audit logging
- [ ] API rate limiting
- [ ] Swagger/OpenAPI documentation
- [ ] Unit and integration tests
- [ ] Docker containerization
- [ ] CI/CD pipeline

---

**Happy Coding! 🎉**
