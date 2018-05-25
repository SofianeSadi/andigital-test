# ANDigital - Coding challenge

Table of Contents
=================
* [About the project](#about-the-project)
* [Tech stack](#tech-stack)
* [Run the application](#run-the-application)
* [Test the application](#test-the-application)
* [APIs](#apis)
   * [Numbers API](#numbers-api)
      * [Find all numbers (scenario "get all phone numbers")](#find-all-numbers-scenario-get-all-phone-numbers)
      * [Activate a number (scenario: activate a phone number)](#activate-a-number-scenario-activate-a-phone-number)
   * [Customers API](#customers-api)
      * [Find all numbers for a given customer (scenario: get all phone numbers)](#find-all-numbers-for-a-given-customer-scenario-get-all-phone-numbers)
* [Test data](#test-data)
* [UML class diagram](#class-diagram)

## About the project
In the exercise we pretend that we are working for a telecom provider.  
The provider needs a new API with initially just three endpoints.  
We would like you to spec out those endpoints and provide sample implementations for those API
endpoints.  
In our (imaginary) database, we are starting to store phone numbers associated to customers (1
customer : N phone numbers) and we will provide an API to modify them.  
We need 3 APIs:  
- get all phone numbers
- get all phone numbers of a single customer
- activate a phone number  

## Tech stack
TDD project based on a reactive implementation.  
- **Web framework**: Spring webflux  
- **Reactive library**: Project reactor  
- **Test dependencies**: Spring boot starter test - JUnit jupiter  
- **Build tool**: Gradle  


## Run the application
```commandline
git clone https://github.com/SofianeSadiPro/andigital-test.git
cd andigital-test
./gradlew clean build
java -jar ./build/libs/sofianesadi-0.0.1-SNAPSHOT.jar
```

## Test the application
Using postman: Import the collection (documentation/test_andigital_sofianesadi.postman_collection.json) into postman 
to test the 3 endpoints.  
Using curl:

- Get all phone numbers:
```curl
curl -X GET \
  http://localhost:8080/numbers \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 2f846c86-bc41-252f-a294-bef2e0ba38e2'
```

- Get all phone numbers for a given customer:
```curl
curl -X GET \
  http://localhost:8080/customers/1/numbers \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 0dcf9504-f919-7024-a735-dc883e25c02d'
```

- Activate a phone number:
```curl
curl -X GET \
  http://localhost:8080/customers/1/numbers \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 0dcf9504-f919-7024-a735-dc883e25c02d'
```

## APIs
As springfox swagger is currently not available for spring webflux, we are describing the API via the readme.
### Numbers API
#### Find all numbers (scenario "get all phone numbers")
- **URL**: /api/numbers
- **Method**: GET
- **Success Response**:   
    - **Code**: 200  
    - **Content**:   
    ```javascript
    [
        {
            "id": 1,
            "ddi": "07492525820",
            "customerEntity": {
                "id": 1
            },
            "status": "ACTIVATED"
        },
        {
            "id": 2,
            "ddi": "07492525821",
            "customerEntity": {
                "id": 2
            },
            "status": "DEACTIVATED"
        },
        ...
    ]
    ```
- **Error Response**:  
    **Code**: 404 Not found - If no numbers found.  

#### Activate a number (scenario: activate a phone number)
- **URL**: /api/numbers/{id}/activate
- **Method**: PATCH
- **URL Params**: 
    * *id*: Number ID (Long)
- **Success Response**:   
    - **Code**: 200  
    - **Content**:   
    ```javascript
   {
       "id": 1,
       "ddi": "07492525820",
       "customerEntity": {
           "id": 1
       },
       "status": "ACTIVATED"
   }
    ```
- **Error Response**:  
    **Code**: 404 Not found - If number not found for the given id.
    
### Customers API
#### Find all numbers for a given customer (scenario: get all phone numbers)
- **URL**: /api/customers/{id}/numbers
- **Method**: GET
- **URL Params**: 
    * *id*: Customer ID (Long)
- **Success Response**:   
    **Code**: 200  
    **Content**:   
    ```javascript
    {
        "id": 1,
        "ddi": "07492525820",
        "customerEntity": {
            "id": 1
        },
        "status": "ACTIVATED"
    }
    ```
- **Error Response**:  
    **Code**: 404 Not found - If the customer is not found or don't have any numbers.  

## Test data
This is the test numbers used by the kata DAO:

|Id | DDI | Customer ID | Status
|---|--------------|---|------------|
| 1 | "07492525820"| 1 | DEACTIVATED|
| 2 | "07492525821"| 2 | DEACTIVATED|
| 3 | "07492525822"| 3 | DEACTIVATED|
| 4 | "07492525823"| 1 | DEACTIVATED|
| 5 | "07492525824"| 2 | DEACTIVATED|
| 6 | "07492525825"| 3 | DEACTIVATED|
| 7 | "07492525826"| 1 | DEACTIVATED|
| 8 | "07492525827"| 2 | DEACTIVATED|
| 9 | "07492525828"| 3 | DEACTIVATED|

After an application restart the test data are reset.

## Class diagram
Note: Following the specifications, we are not going to use any database, the DAO will be using a static list. 

![alt text](documentation/class-diagram.png "Class diagram")  
