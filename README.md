# Documentation for Student API Services

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
