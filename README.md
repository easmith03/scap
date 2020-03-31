# SCAP - Student CRUD Application 

### Steps to Run

* Clone repository to your local machine.
* Open terminal window and set directory to the root of the project
  * cd scap
* Build the API
  * ./mvnw clean package
* Start the in memory DB and the API 
  * ./mvnw spring-boot:run  
* When the application is running the database console can be accessed at: http://localhost:7080/h2
  * jdbc url: dbc:h2:mem:studentdb
  * user: SA
  * password:  (empty string)
  
* Start the UI - Open a new terminal window change directory to scapui under the project root
  * cd scapui  
* The first running the application build the ui dependencies. In the terminal execute the npm command 
  * npm install
* Start the UI.  In the terminal window execute the command 
  * yarn start  
* Open http://localhost:3000 in a browser (if not already opened by the yarn command).  The application should be running.