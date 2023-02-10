# statistics-service
The statistics-service is a simple application whose purpose is to provide statistics of football teams based on information about them supplied

## Technologies used
* Java 17
* Spring Boot 3
* JUnit 5
* Mockito
* Maven
* Lombok
* MySQL
* Liquibase
* Docker
* Docker Compose
* Test Containers

## Prerequisites
- [_Docker_](https://docs.docker.com/get-docker/)
- [_Docker Compose_](https://docs.docker.com/compose/install/)


## Running the Project
1. Clone this repository to your local machine using `git clone https://github.com/DawidStuzynski/statistics-service.git`
2. Navigate to the project directory using `cd statistics-service`
3. Run the following command to start the project: `docker compose up`

This command will start the containers defined in the docker-compose.yaml file. You can now access the application in your browser at http://localhost:8080

## API Documentation
The API documentation can be accessed after starting the project at http://localhost:8080/swagger-ui/index.html

## Stopping the Project
To stop the project, you can use the following command: `docker compose down`
This will stop and remove all the containers, networks, and volumes defined in the docker-compose.yaml file.

## Note 
Since this project does not require any environment variables, you do not need to specify any additional parameters. However, if you need to specify any environment variables in the future, you can do so in the docker-compose.yaml file.