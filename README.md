# Simple Cash
Univesp Integrator Project - Backend responsible for managing financial transactions per user.

---------------

#### Settings  
* Java 11
  
* Environment variables  

| Variable name          | Description                                  |  
| ---------------------- | -------------------------------------------- |  
| ${ENV}                 | Environment where the application is running |  
| ${DATASOURCE_URL}      | Database connection address                  |  
| ${DATASOURCE_USERNAME} | Database username                            |  
| ${DATASOURCE_PASSWORD} | Database password                            |  
| ${ENCRYPT_KEY}         | Key for encrypt/decrypt token                |  
  
* Build project with unit tests  

At the root of the project run the command
> ./mvnw clean install

#### Endpoints  

| Method | Endpoint                             | Description                      |  
| ------ | ------------------------------------ | -------------------------------- |  
| [GET]  | /swagger-ui.html                     | Application Doc's                |  
| [POST] | /login                               | User login                       |  
| [POST] | /api/v1/users                        | User registration                |  
| [POST] | /api/v1/users/{userCode}/accounts    | Account registration for a user  |  
| [POST] | /api/v1/users/{userCode}/categories  | Category registration for a user |  
