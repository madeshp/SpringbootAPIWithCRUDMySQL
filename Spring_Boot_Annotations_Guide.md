# Spring Boot Annotations Guide - Student Management System

This guide provides detailed explanations of all Spring Boot annotations used in the Student Management System project, organized by category and purpose.

## 📚 Table of Contents

1. [Core Spring Boot Annotations](#core-spring-boot-annotations)
2. [Spring Data JPA Annotations](#spring-data-jpa-annotations)
3. [Spring Web Annotations](#spring-web-annotations)
4. [Spring Validation Annotations](#spring-validation-annotations)
5. [Spring Dependency Injection Annotations](#spring-dependency-injection-annotations)
6. [Spring Lifecycle Annotations](#spring-lifecycle-annotations)
7. [Complete Annotation Reference](#complete-annotation-reference)

---

## 🚀 Core Spring Boot Annotations

### `@SpringBootApplication`
**Location**: `StudentManagementApplication.java`

**Purpose**: This is the main annotation that marks a class as a Spring Boot application. It's a convenience annotation that adds all of the following:
- `@Configuration`: Tags the class as a source of bean definitions
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings
- `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the `com.example.studentmanagement` package

**Example**:
```java
@SpringBootApplication
public class StudentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }
}
```

**What it does**:
- Automatically configures the application context
- Scans for components in the specified package
- Enables auto-configuration based on dependencies
- Sets up the main application class

---

## 🗄️ Spring Data JPA Annotations

### `@Entity`
**Location**: `Student.java`

**Purpose**: Specifies that the class is a JPA entity and should be mapped to a database table.

**Example**:
```java
@Entity
@Table(name = "students")
public class Student {
    // Entity fields and methods
}
```

**What it does**:
- Marks the class as a JPA entity
- Enables JPA to manage the object's persistence
- Allows the class to be mapped to a database table

### `@Table`
**Location**: `Student.java`

**Purpose**: Specifies the name of the database table to which the entity is mapped.

**Example**:
```java
@Table(name = "students")
public class Student {
    // Entity fields and methods
}
```

**What it does**:
- Maps the entity to a specific table name
- If not specified, the table name defaults to the class name
- Useful for customizing table names

### `@Id`
**Location**: `Student.java`

**Purpose**: Specifies the primary key of the entity.

**Example**:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**What it does**:
- Marks a field as the primary key
- Required for all JPA entities
- Used by JPA for entity identification and relationship mapping

### `@GeneratedValue`
**Location**: `Student.java`

**Purpose**: Specifies the strategy for generating primary key values.

**Example**:
```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**What it does**:
- Automatically generates primary key values
- `GenerationType.IDENTITY` uses auto-increment (MySQL)
- Other strategies include `SEQUENCE`, `TABLE`, and `AUTO`

### `@Column`
**Location**: `Student.java`

**Purpose**: Specifies the mapping between a field and a database column.

**Example**:
```java
@Column(name = "first_name", nullable = false)
private String firstName;

@Column(name = "email", unique = true, nullable = false)
private String email;
```

**What it does**:
- Maps entity fields to specific database columns
- Allows customization of column properties (name, nullable, unique, length)
- If not specified, the column name defaults to the field name

### `@Repository`
**Location**: `StudentRepository.java`

**Purpose**: Indicates that the class is a repository, a mechanism for encapsulating storage, retrieval, and search behavior.

**Example**:
```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Repository methods
}
```

**What it does**:
- Marks the interface as a Spring Data repository
- Enables exception translation from data access exceptions
- Allows Spring to detect and manage the repository bean

---

## 🌐 Spring Web Annotations

### `@RestController`
**Location**: `StudentController.java`

**Purpose**: A convenience annotation that combines `@Controller` and `@ResponseBody`. It indicates that the class is a controller and that the return values from its methods should be written directly to the HTTP response body.

**Example**:
```java
@RestController
@RequestMapping("/api/students")
public class StudentController {
    // Controller methods
}
```

**What it does**:
- Marks the class as a Spring MVC controller
- Automatically serializes return values to JSON/XML
- Eliminates the need for `@ResponseBody` on each method

### `@RequestMapping`
**Location**: `StudentController.java`

**Purpose**: Maps web requests to specific handler methods. It can be applied at the class level for all methods or at the method level for specific endpoints.

**Example**:
```java
@RequestMapping("/api/students")
public class StudentController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        // Method implementation
    }
}
```

**What it does**:
- Defines the base URL path for all methods in the controller
- Can be customized with HTTP methods, headers, and parameters
- Provides a common prefix for all endpoints

### `@GetMapping`
**Location**: `StudentController.java`

**Purpose**: A composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)`.

**Example**:
```java
@GetMapping
public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
    // Method implementation
}

@GetMapping("/{id}")
public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
    // Method implementation
}
```

**What it does**:
- Maps HTTP GET requests to specific methods
- More concise than `@RequestMapping`
- Automatically handles GET method specification

### `@PostMapping`
**Location**: `StudentController.java`

**Purpose**: A composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.POST)`.

**Example**:
```java
@PostMapping
public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
    // Method implementation
}
```

**What it does**:
- Maps HTTP POST requests to specific methods
- Used for creating new resources
- Automatically handles POST method specification

### `@PutMapping`
**Location**: `StudentController.java`

**Purpose**: A composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.PUT)`.

**Example**:
```java
@PutMapping("/{id}")
public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
    // Method implementation
}
```

**What it does**:
- Maps HTTP PUT requests to specific methods
- Used for updating existing resources
- Automatically handles PUT method specification

### `@DeleteMapping`
**Location**: `StudentController.java`

**Purpose**: A composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.

**Example**:
```java
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
    // Method implementation
}
```

**What it does**:
- Maps HTTP DELETE requests to specific methods
- Used for removing resources
- Automatically handles DELETE method specification

### `@PatchMapping`
**Location**: `StudentController.java`

**Purpose**: A composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.PATCH)`.

**Example**:
```java
@PatchMapping("/{id}/deactivate")
public ResponseEntity<StudentResponseDTO> deactivateStudent(@PathVariable Long id) {
    // Method implementation
}
```

**What it does**:
- Maps HTTP PATCH requests to specific methods
- Used for partial updates to resources
- Automatically handles PATCH method specification

### `@PathVariable`
**Location**: `StudentController.java`

**Purpose**: Binds a method parameter to a path variable in the URL.

**Example**:
```java
@GetMapping("/{id}")
public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
    // Method implementation
}
```

**What it does**:
- Extracts values from the URL path
- Automatically converts the value to the specified type
- Binds the path variable to the method parameter

### `@RequestBody`
**Location**: `StudentController.java`

**Purpose**: Binds the HTTP request body to a method parameter.

**Example**:
```java
@PostMapping
public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
    // Method implementation
}
```

**What it does**:
- Deserializes the HTTP request body to a Java object
- Automatically converts JSON/XML to the specified type
- Used for POST and PUT requests with data

### `@RequestParam`
**Location**: Not used in current project (was in removed APIs)

**Purpose**: Binds a method parameter to a query parameter in the URL.

**Example**:
```java
@GetMapping("/search")
public ResponseEntity<List<StudentResponseDTO>> searchStudents(@RequestParam String name) {
    // Method implementation
}
```

**What it does**:
- Extracts values from query parameters
- Binds query parameters to method parameters
- Useful for search and filtering operations

### `@CrossOrigin`
**Location**: `StudentController.java`

**Purpose**: Enables Cross-Origin Resource Sharing (CORS) for the controller or specific methods.

**Example**:
```java
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    // Controller methods
}
```

**What it does**:
- Allows web applications to make requests from different origins
- Enables frontend applications to consume the API
- Configures CORS headers for cross-origin requests

---

## ✅ Spring Validation Annotations

### `@Valid`
**Location**: `StudentController.java`

**Purpose**: Triggers validation of the annotated object.

**Example**:
```java
@PostMapping
public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
    // Method implementation
}
```

**What it does**:
- Activates validation on the request body
- Triggers validation annotations in the DTO
- Throws validation exceptions if constraints are violated

### `@NotBlank`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates that a string is not null, not empty, and not just whitespace.

**Example**:
```java
@NotBlank(message = "First name is required")
@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
private String firstName;
```

**What it does**:
- Ensures the field has meaningful content
- Provides custom error messages
- Works with `@Valid` annotation

### `@Size`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates the size of a string, collection, or array.

**Example**:
```java
@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
private String firstName;

@Size(max = 200, message = "Address cannot exceed 200 characters")
private String address;
```

**What it does**:
- Sets minimum and maximum length constraints
- Provides custom error messages
- Ensures data integrity

### `@Email`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates that a string is a valid email address.

**Example**:
```java
@Email(message = "Please provide a valid email address")
private String email;
```

**What it does**:
- Ensures the field contains a valid email format
- Provides custom error messages
- Basic email format validation

### `@Pattern`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates that a string matches a specified regular expression.

**Example**:
```java
@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
private String phoneNumber;
```

**What it does**:
- Enforces specific format requirements
- Uses regex patterns for validation
- Provides custom error messages

### `@NotNull`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates that a field is not null.

**Example**:
```java
@NotNull(message = "Date of birth is required")
@Past(message = "Date of birth must be in the past")
private LocalDate dateOfBirth;
```

**What it does**:
- Ensures the field is not null
- Works with other validation annotations
- Provides custom error messages

### `@Past`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates that a date is in the past.

**Example**:
```java
@Past(message = "Date of birth must be in the past")
private LocalDate dateOfBirth;
```

**What it does**:
- Ensures the date is before the current date
- Useful for birth dates, start dates, etc.
- Provides custom error messages

### `@Min` and `@Max`
**Location**: `Student.java`, `StudentRequestDTO.java`

**Purpose**: Validates that a numeric value is within a specified range.

**Example**:
```java
@Min(value = 2000, message = "Enrollment year must be 2000 or later")
@Max(value = 2030, message = "Enrollment year cannot exceed 2030")
private Integer enrollmentYear;
```

**What it does**:
- Sets minimum and maximum value constraints
- Works with numeric types (int, long, Integer, Long)
- Provides custom error messages

---

## 🔧 Spring Dependency Injection Annotations

### `@Autowired`
**Location**: `StudentServiceImpl.java`, `DataLoader.java`

**Purpose**: Injects dependencies into the annotated field, constructor, or method.

**Example**:
```java
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    // Method implementations
}
```

**What it does**:
- Automatically injects Spring beans
- Resolves dependencies by type
- Eliminates the need for manual bean creation

### `@Service`
**Location**: `StudentServiceImpl.java`

**Purpose**: Indicates that the class is a service component in the business logic layer.

**Example**:
```java
@Service
public class StudentServiceImpl implements StudentService {
    // Service implementation
}
```

**What it does**:
- Marks the class as a Spring service component
- Enables component scanning and bean creation
- Indicates the class contains business logic

### `@Component`
**Location**: `DataLoader.java`

**Purpose**: Indicates that the class is a Spring component that should be managed by the Spring container.

**Example**:
```java
@Component
public class DataLoader implements CommandLineRunner {
    // Component implementation
}
```

**What it does**:
- Marks the class as a Spring-managed component
- Enables automatic bean creation and dependency injection
- Generic component annotation for any Spring-managed class

---

## ⏰ Spring Lifecycle Annotations

### `@PreUpdate`
**Location**: `Student.java`

**Purpose**: Specifies a method to be called before an entity is updated.

**Example**:
```java
@PreUpdate
public void preUpdate() {
    this.updatedAt = LocalDate.now();
}
```

**What it does**:
- Automatically executes before entity updates
- Useful for setting audit fields (updatedAt, updatedBy)
- JPA lifecycle callback method

---

## 🌟 Complete Annotation Reference

### **Core Annotations**
| Annotation | Purpose | Location |
|------------|---------|-----------|
| `@SpringBootApplication` | Main application class | `StudentManagementApplication.java` |

### **JPA Annotations**
| Annotation | Purpose | Location |
|------------|---------|-----------|
| `@Entity` | Marks class as JPA entity | `Student.java` |
| `@Table` | Specifies table name | `Student.java` |
| `@Id` | Marks primary key | `Student.java` |
| `@GeneratedValue` | Primary key generation strategy | `Student.java` |
| `@Column` | Column mapping configuration | `Student.java` |
| `@Repository` | Marks repository interface | `StudentRepository.java` |
| `@PreUpdate` | Pre-update lifecycle callback | `Student.java` |

### **Web Annotations**
| Annotation | Purpose | Location |
|------------|---------|-----------|
| `@RestController` | REST controller class | `StudentController.java` |
| `@RequestMapping` | Base URL mapping | `StudentController.java` |
| `@GetMapping` | GET request mapping | `StudentController.java` |
| `@PostMapping` | POST request mapping | `StudentController.java` |
| `@PutMapping` | PUT request mapping | `StudentController.java` |
| `@DeleteMapping` | DELETE request mapping | `StudentController.java` |
| `@PatchMapping` | PATCH request mapping | `StudentController.java` |
| `@PathVariable` | Path variable binding | `StudentController.java` |
| `@RequestBody` | Request body binding | `StudentController.java` |
| `@CrossOrigin` | CORS configuration | `StudentController.java` |

### **Validation Annotations**
| Annotation | Purpose | Location |
|------------|---------|-----------|
| `@Valid` | Triggers validation | `StudentController.java` |
| `@NotBlank` | String not blank validation | `Student.java`, `StudentRequestDTO.java` |
| `@Size` | String size validation | `Student.java`, `StudentRequestDTO.java` |
| `@Email` | Email format validation | `Student.java`, `StudentRequestDTO.java` |
| `@Pattern` | Regex pattern validation | `Student.java`, `StudentRequestDTO.java` |
| `@NotNull` | Not null validation | `Student.java`, `StudentRequestDTO.java` |
| `@Past` | Past date validation | `Student.java`, `StudentRequestDTO.java` |
| `@Min` | Minimum value validation | `Student.java`, `StudentRequestDTO.java` |
| `@Max` | Maximum value validation | `Student.java`, `StudentRequestDTO.java` |

### **Dependency Injection Annotations**
| Annotation | Purpose | Location |
|------------|---------|-----------|
| `@Autowired` | Dependency injection | `StudentServiceImpl.java`, `DataLoader.java` |
| `@Service` | Service component | `StudentServiceImpl.java` |
| `@Component` | Generic component | `DataLoader.java` |

---

## 🔍 How Annotations Work Together

### **Request Flow Example**
1. **HTTP Request** → `@RestController` + `@RequestMapping`
2. **Method Mapping** → `@GetMapping`, `@PostMapping`, etc.
3. **Parameter Binding** → `@PathVariable`, `@RequestBody`
4. **Validation** → `@Valid` + validation annotations
5. **Business Logic** → `@Service` + `@Autowired`
6. **Data Access** → `@Repository` + JPA annotations
7. **Response** → Automatic JSON serialization

### **Bean Lifecycle**
1. **Component Scanning** → `@Component`, `@Service`, `@Repository`
2. **Dependency Injection** → `@Autowired`
3. **Bean Creation** → Spring container manages lifecycle
4. **Method Execution** → Annotations provide behavior

---

## 💡 Best Practices

### **1. Use Appropriate Annotations**
- Choose the most specific annotation for your use case
- Avoid over-annotating classes
- Use composed annotations when possible (`@GetMapping` vs `@RequestMapping`)

### **2. Validation Strategy**
- Apply validation at the DTO level
- Use `@Valid` in controllers
- Provide meaningful error messages

### **3. Dependency Injection**
- Prefer constructor injection over field injection
- Use `@Autowired` sparingly
- Consider using `@RequiredArgsConstructor` for cleaner code

### **4. Naming Conventions**
- Use descriptive names for custom annotations
- Follow Spring naming conventions
- Document complex annotation usage

---

## 🚀 Advanced Usage Examples

### **Custom Validation**
```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### **Conditional Bean Creation**
```java
@ConditionalOnProperty(name = "app.feature.enabled", havingValue = "true")
@Component
public class ConditionalService {
    // Service implementation
}
```

### **Async Method Execution**
```java
@Async
public CompletableFuture<String> asyncMethod() {
    // Async implementation
}
```

---

## 📚 Additional Resources

- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Bean Validation Specification](https://beanvalidation.org/2.0/spec/)

---

This guide covers all the Spring Boot annotations used in the Student Management System project. Understanding these annotations is crucial for building robust Spring Boot applications and leveraging the framework's capabilities effectively.
