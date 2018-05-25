# ANDigital - Coding challenge

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

## Run the application
```commandline
git clone https://github.com/SofianeSadiPro/andigital-test.git
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

## Tech stack
TDD project based on a reactive implementation.  
- **Web framework**: Spring webflux  
- **Reactive library**: Project reactor  
- **Test dependencies**: Spring boot starter test - JUnit jupiter  
- **Build tool**: Gradle  

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
            "status": "DESACTIVATED"
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

## Class diagram
Note: Following the specifications, we are not going to use any database, the DAO will be using a static list. 

![alt text](documentation/class-diagram.png "Class diagram")  
