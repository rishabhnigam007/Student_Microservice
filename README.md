# Documentation for Student API Services

For detailed API documentation, see [API Docs](api-docs/README.md).

## Project Implementation Details

This Student Microservice project includes the following implementations:

- **SonarQube** - Integrated for code quality analysis and continuous inspection
- **JaCoCo** - Configured for code coverage reporting and test coverage metrics
- **OpenAPI Documentation** - Implemented to automatically generate API documentation
- **Swagger UI** - Integrated for interactive API exploration and testing
- **Markdown Documentation** - Generated markdown files with all configurations managed in `pom.xml`

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/api*

| Class | Method | HTTP request | Description |
|------------ | ------------- | ------------- | -------------|
| *StudentApi* | [**createNewStudent**](Apis/StudentApi.md#createnewstudent) | **POST** /students | Create a new student |
*StudentApi* | [**deleteAStudent**](Apis/StudentApi.md#deleteastudent) | **DELETE** /students/{id} | Delete student |
*StudentApi* | [**getAllStudents**](Apis/StudentApi.md#getallstudents) | **GET** /students | Get all students |
*StudentApi* | [**getStudentById**](Apis/StudentApi.md#getstudentbyid) | **GET** /students/{id} | Get student by ID |
*StudentApi* | [**updatePartialStudent**](Apis/StudentApi.md#updatepartialstudent) | **PATCH** /students/{id} | Update student (Partial) |
*StudentApi* | [**updateStudent**](Apis/StudentApi.md#updatestudent) | **PUT** /students/{id} | Update student (Full) |


<a name="documentation-for-models"></a>
## Documentation for Models

 - [AddStudentRequestDto](./Models/AddStudentRequestDto.md)
 - [ApiError](./Models/ApiError.md)
 - [StudentDto](./Models/StudentDto.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
