# SCAP - Student CRUD Application 

### Steps to Run

* Clone repository to your local machine.
* Start the DB and the API - Open terminal window and execute the command from the root project directory to build the project
  * ./mvnw clean package
* In the same terminal window execute the command to run the application.
  * ./mvnw spring-boot:run  
* When the application is running the database console can be accessed at: http://localhost:7080/h2
  * jdbc url: dbc:h2:mem:studentdb
  * user: SA
  * password:  (empty string)
* The application UI can be accessed at http://localhost:7780