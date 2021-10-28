# Blockserver API
Author: Joseph Donnelly. Created for Daydream Take Home Task

This API was built using the **Spring Boot** application framework. 
The underlying data persistence layer uses an **H2** SQL database.

## Usage
Run the following [Gradle](https://spring.io/guides/gs/gradle/#initial) command: ```gradle bootRun```

This will launch the Spring Boot application on your local machine, at the default port of 8080: `localhost:8080/block`

There are 5 API endpoints implemented in this exercise. Paths and behavior described below:
* `GET /block`: Retrieve a list all 'top-level' Blocks that have no parent as a JSON Array
* `GET /block/{id}`: Retrieve the Block with id = {id} as a JSON object
* `PUT /block`: Create a new top-level Block and retrieve it as a JSON object
* `PUT /block/{id}`: Create a new Block that is a child of the Block with id = {id}. Retrieve the newly created Block as a JSON object
* `POST /block/{id}`: Update the `properties` field of Block with id = {id}. The endpoint accepts a raw JSON Object in HTTP request body. This body will *replace* the existing `properties` value for the given Block. Returns 204 Status on success and 404 on failure

## Notes/Assumptions Made
* The API specification does not specify what to do when a POST request is made for a Block with existing `properties`. I chose to overwrite the existing properties as the POST verb typically is associated with 'creating' (not mutating) sub-resources in RESTful applications.
* The API specification also does not mention any error handling or how to handle invalid {id}s. If you try to `PUT` or `GET`, I return a dummy Block object, and no Database change is made. With `POST`, I return a standard 404 error status.
* Some H2 settings can be found in ./src/main/resources/application.properties. By default, data will be written to the H2 file created in ./data/