# Getting started with JPA and Hibernate
In this project we will be learning JPA and Hibernate.

### Approach :
1. Create a Spring boot project with H2(in memory database).
2. Create course table
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

**CourseCommandLineRunner.java**
```java
package com.ayush953.Jpa_and_Hibernate.course.Jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    CourseJdbcRepository repository;

    public CourseCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        repository.insert();
    }
}
```
Let's add course using Course class.
```java
package com.ayush953.Jpa_and_Hibernate.course;

public class Course {
    private long id;
    private String name;
    private String author;

    public Course() {

    }
    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
}
```
We can use this class to insert data into database.
Modify **CourseJdbcRepository.java** class : 
```java
 private static String INSERT_QUERY = """
            INSERT INTO COURSE(id,name,author) VALUES (?,?,?);
            """;

    public void insert(Course course) {
        jdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }
```
And then while inserting new data we can pass Course object as an argument.
Modified **CourseCommandLineRunner.java** class :
```java
 @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1,"Docker","XYZ"));
        repository.insert(new Course(2,"Spring boot","XYZ"));
        repository.insert(new Course(3,"Apache kafka","XYZ"));

    }
```
#### To delete row by id : 
**CourseJdbcRepository.java** :
```java
private static String DELETE_QUERY = """
            DELETE FROM COURSE WHERE id = ?;
        """;
 public void deleteById(long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
```
**CourseCommandLineRunner.java** :
```java
repository.deleteById(1);
```
### Now let's query the data :
#### Define method to query the data 
**CourseJdbcRepository.java**
```java
private static String SELECT_QUERY = """
                SELECT * FROM COURSE WHERE id = ?;
            """;
public Course selectById(long id) {
    return jdbcTemplate.queryForObject(SELECT_QUERY,new BeanPropertyRowMapper<>(Course.class) ,id);
}
```

We are mapping the course returned from database to Course class bean. The data members of the Course class should match the column names in the table to be able to map.
Course Class Should also implement **toString** method and setters.

Modified **Course** class :
```java
public class Course {
    private long id;
    private String name;
    private String author;

    public Course() {

    }
    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }

    public String toString(){
        return "Course [ id = "+id + ", name = " + name + ", author = " + author+" ]";
    }
}
```
Print the courses

**CourseCommandLineRunner.java** :
```java
System.out.println(repository.selectById(2));
System.out.println(repository.selectById(3));
```
**Output** :
```bash
Course [ id = 2, name = Spring boot, author = XYZ ]
Course [ id = 3, name = Apache kafka, author = XYZ ]
```