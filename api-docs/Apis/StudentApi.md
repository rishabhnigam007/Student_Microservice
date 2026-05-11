# StudentApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createNewStudent**](StudentApi.md#createNewStudent) | **POST** /students | Create a new student |
| [**deleteAStudent**](StudentApi.md#deleteAStudent) | **DELETE** /students/{id} | Delete student |
| [**getAllStudents**](StudentApi.md#getAllStudents) | **GET** /students | Get all students |
| [**getStudentById**](StudentApi.md#getStudentById) | **GET** /students/{id} | Get student by ID |
| [**updatePartialStudent**](StudentApi.md#updatePartialStudent) | **PATCH** /students/{id} | Update student (Partial) |
| [**updateStudent**](StudentApi.md#updateStudent) | **PUT** /students/{id} | Update student (Full) |


<a name="createNewStudent"></a>
# **createNewStudent**
> StudentDto createNewStudent(AddStudentRequestDto)

Create a new student

    Creates and saves a new student in the system

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **AddStudentRequestDto** | [**AddStudentRequestDto**](../Models/AddStudentRequestDto.md)| Student creation request payload | |

### Return type

[**StudentDto**](../Models/StudentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

<a name="deleteAStudent"></a>
# **deleteAStudent**
> deleteAStudent(id)

Delete student

    Deletes a student using their ID

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the student to delete | [default to null] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="getAllStudents"></a>
# **getAllStudents**
> List getAllStudents()

Get all students

    Fetches the list of all registered students

### Parameters
This endpoint does not need any parameter.

### Return type

[**List**](../Models/StudentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="getStudentById"></a>
# **getStudentById**
> StudentDto getStudentById(id)

Get student by ID

    Fetches a student using their unique ID

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| Unique ID of the student | [default to null] |

### Return type

[**StudentDto**](../Models/StudentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="updatePartialStudent"></a>
# **updatePartialStudent**
> StudentDto updatePartialStudent(id, AddStudentRequestDto)

Update student (Partial)

    Updates selected fields of an existing student

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the student to update | [default to null] |
| **AddStudentRequestDto** | [**AddStudentRequestDto**](../Models/AddStudentRequestDto.md)| Fields to update (key-value pairs) | |

### Return type

[**StudentDto**](../Models/StudentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

<a name="updateStudent"></a>
# **updateStudent**
> StudentDto updateStudent(id, AddStudentRequestDto)

Update student (Full)

    Completely updates an existing student

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the student to update | [default to null] |
| **AddStudentRequestDto** | [**AddStudentRequestDto**](../Models/AddStudentRequestDto.md)|  | |

### Return type

[**StudentDto**](../Models/StudentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

