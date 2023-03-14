# spring-boot-multi-module-maven

This module will showcase how to use the spring-boot with multi-module configuration. Here, I am also using controllerAdvice in case of any exception occurs, so how we can manipulate the response and send it back.

### Endpoints

- [GET] http://localhost:9090/demo/v1/health
- [POST] http://localhost:9090/demo/v1/card/add_card_details
- [GET] http://localhost:9090/demo/v1/promotions/:userId
- [POST] http://localhost:9090/demo/v1/purchases/accept
- [GET] http://localhost:9090/demo/v1/transaction/:userId
- [GET] http://localhost:9090/demo/v1/transaction/filter/:userId

#### AdHoc

- [GET] http://localhost:9090/demo/swagger-ui/index.html
- [GET] http://localhost:9090/demo/h2

### Technology

- Java 17
- Embedded Tomcat
- JUnit 5
- Mockito
- Spring Boot 3
- Spring Data JPA
- H2 Database (In-Memory)
- Swagger Open API

### Headers

- Accept: 'application/json'
- Content-Type: 'application/json'

### Directory Structure

- #### application
  This directory contains all the controllers and application boot specific classes, configurations and settings.

- #### repository
  This directory contains all the repositories classes or database specific code like JpaRepositories needed to connect
  to any type of database.

- #### service
  This directory contains all the service classes where we will write the complex logic or this could be a intermediary
  layer where we will be calling to repositories API.

- #### domain
  This directory contains all the Value Objects or Domain or DTO or any other type of POJOS.

### Steps To run the app

We have provided an `init.sh` scripts to bootstrap the application. This scripts will `clean`, `compile`, `package`
, `run unit tests` and finally `launch` the spring boot app. execute

```
$ sh scripts/init.sh
```

Or

- Run ```./mvnw clean package``` this will delete all/any `target` existing folders and install any needed dependencies
  and package the final fat jar with named as `demo-${project.version}`.
- Run ```java -jar application/target/demo-1.0.0-SNAPSHOT.jar```
