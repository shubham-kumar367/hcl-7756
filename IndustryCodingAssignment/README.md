# Industry Coding Assignment

## HRMS Module 1: Employee Management and Time & Attendance

### 1. Technology Stack
- Java 17
- Spring Boot 3.5.6
- Maven
- Spring Web
- Jakarta Validation
- Spring Boot Test
- In-memory storage using `LinkedHashMap`

### 2. Project Structure

- `model`: Employee and Attendance data classes
- `repository`: In-memory data storage
- `service`: Business logic and validation
- `controller`: REST API endpoints
- `exception`: Global exception handling

### 3. Application Configuration

Application name: `hrms-app`

| Profile | Environment | Port |
|---|---|---|
| `dev` | development | 8080 |
| `prod` | production | 8081 |

#### Run Development Profile

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Run Production Profile

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

In IntelliJ IDEA, the active profile can also be configured in the Spring Boot run configuration.

### 4. Employee Management APIs

Base path: `/api/v1/employees`

| Method | Endpoint | Description | Success response |
|---|---|---|---|
| POST | `/api/v1/employees` | Create employee | `201 Created` |
| GET | `/api/v1/employees` | List employees | `200 OK` |
| GET | `/api/v1/employees/{id}` | Get employee by ID | `200 OK` |
| PUT | `/api/v1/employees/{id}` | Update employee fields | `200 OK` |
| PATCH | `/api/v1/employees/{id}` | Partially update employee | `200 OK` |
| DELETE | `/api/v1/employees/{id}` | Delete employee | `204 No Content` |

Employee fields:
- `empId`: Employee identifier
- `name`: Employee name
- `department`: Department name
- `city`: Employee city
- `active`: Active status, defaulting to `true`

Name, department, and city are required. Duplicate employee IDs return `409 Conflict`. Invalid input returns `400 Bad Request`. A missing employee returns `404 Not Found`.

### 5. Time and Attendance APIs

Base path: `/api/v1/attendance`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/attendance/check-in` | Record employee check-in |
| PATCH | `/api/v1/attendance/{attendanceId}/check-out` | Record check-out |
| GET | `/api/v1/attendance/employee/{employeeId}` | Get an employee's attendance history |
| GET | `/api/v1/attendance?employeeId=E101&date=2026-10-22` | Filter attendance by employee and date |

#### Check-in Request

```json
{
  "employeeId": "E101"
}
```

The server records the attendance date and check-in time. A successful check-in returns `201 Created`.

#### Attendance Rules

- The employee must exist before checking in.
- An employee can check in only once per calendar date.
- Check-out is allowed only for an existing attendance record that has not already been checked out.
- The server records check-in and check-out times.
- Duplicate attendance actions return `409 Conflict`.
- Invalid input returns `400 Bad Request`.
- Missing employees or attendance records return `404 Not Found`.

### 6. REST Semantics

- **GET:** Retrieves resources without modifying them.
- **POST:** Creates a resource and is not inherently idempotent.
- **PUT:** Updates the employee's editable fields and is intended to be idempotent.
- **PATCH:** Updates supplied non-null fields. Omitted and `null` fields are treated the same. Blank strings are rejected for required text fields.
- **DELETE:** Returns `204 No Content` after successful deletion. Repeated deletion returns `404 Not Found`.

### 7. Spring Boot Fundamentals

- `@SpringBootApplication` combines configuration, component scanning, and auto-configuration.
- Spring Web auto-configuration sets up the embedded web server and Spring MVC infrastructure.
- Constructor injection supplies required dependencies to services and controllers.
- `@RestController` exposes REST endpoints.
- `@RestControllerAdvice` provides centralized exception handling.
- Jakarta Validation checks required employee fields.

### 8. Storage Limitation

This assignment uses in-memory collections only. Data is lost when the application stops or restarts. No database, JPA, or external persistence is configured.

### 9. Testing

The project includes the Spring Boot Test dependency. API testing and the required Postman evidence should be completed before final submission.
### Statelessness and Idempotency

#### Statelessness
Each API request contains the information needed to process it. The application does not depend on hidden client state stored in controller fields. Employee and attendance records are maintained in server-side in-memory repositories.

#### Idempotency
- **GET:** Idempotent because repeated requests do not modify data.
- **POST:** Not inherently idempotent because repeating a creation request may create a resource or return a duplicate-ID conflict.
- **PUT:** Intended to be idempotent because repeating the same valid update produces the same intended employee state.
- **PATCH:** Depends on the requested changes. Setting a field to the same value repeatedly is idempotent for this implementation.
- **DELETE:** Idempotent in its intended effect because the employee remains deleted after the first successful request. A repeated request returns `404 Not Found`.