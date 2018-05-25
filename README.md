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
todo

## Test the application
todo

## Tech stack
Web framework: Spring webflux  
Reactive library: Project reactor  
Test dependencies: Spring boot starter test - JUnit jupiter    
Build tool: Gradle  

### APIs
As springfox swagger is currently not available for spring webflux, we are describing the API via the readme.
#### Numbers API
##### Find all numbers (scenario "get all phone numbers")
- **URL**: /api/numbers
- **Method**: GET
- **Success Response**:   
    - **Code**: 200  
    - **Content**:   
    ```javascript
    todo: example of response
    ```
- **Error Response**:  
    **Code**: 404 Not found - If no numbers found.  

##### Activate a number (scenario: activate a phone number)
- **URL**: /api/numbers/{id}/activate
- **Method**: PATCH
- **URL Params**: 
    * *id*: Number ID (Long)
- **Success Response**:   
    - **Code**: 200  
    - **Content**:   
    ```javascript
    todo: example of response
    ```
- **Error Response**:  
    **Code**: 404 Not found - If number not found for the given id.
    
#### Customers API
##### Find all numbers for a given customer (scenario: get all phone numbers)
- **URL**: /api/customers/{id}/numbers
- **Method**: GET
- **URL Params**: 
    * *id*: Customer ID (Long)
- **Success Response**:   
    **Code**: 200  
    **Content**:   
    ```javascript
    todo: example of response
    ```
- **Error Response**:  
    **Code**: 404 Not found - If the customer is not found or don't have any numbers.  

### Class diagram
todo
