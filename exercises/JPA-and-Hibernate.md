# Getting started with JPA and Hibernate
In this project we will be learning JPA and Hibernate.

### Approach :
1. Create a Spring boot project with H2(in memory database).
2. Create com.ayush953.Jpa_and_Hibernate.course.Course table
3. Use Spring JDBC to play with com.ayush953.Jpa_and_Hibernate.course.Course table
4. Use JPA and Hibernate to play with com.ayush953.Jpa_and_Hibernate.course.Course table
5. Use Spring Data JPA to play with com.ayush953.Jpa_and_Hibernate.course.Course table

### 1 
**application.properties**
```properties
spring.application.name=Jpa-and-Hibernate
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
it will enable h2 console in browser, and we are creating a database named testdb.

### 2
To create table we need to create schema.sql file in resources directory.

**schema.sql**
```sql
CREATE TABLE course(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL ,
    author VARCHAR(255) NOT NULL
);
```
When we restart the application, it will automatically pick up the sql file and create our table in h2 database.

### 3
Let's insert data in our course table using Spring JDBC.
we will create a class to perform the operation.

**CourseJDBCRepository.java**
```java
package com.ayush953.Jpa_and_Hibernate.course.Jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    private JdbcTemplate jdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String INSERT_QUERY = """
            INSERT INTO COURSE(id,name,author) VALUES (1,'Learn Spring boot','xyz');
            """;

    public void insert() {
        jdbcTemplate.update(INSERT_QUERY);
    }
}
```
this class uses JDBC template to perform insert query.Now we need to run this when our application starts up. We can use CommandLineRunner interface to run something when the application starts.

**CourseJdbcCommandLineRunner.java**
```java
package com.ayush953.Jpa_and_Hibernate.course.Jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    CourseJdbcRepository repository;

    public CourseJdbcCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        repository.insert();
    }
}
```