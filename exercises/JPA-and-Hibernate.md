# Getting started with JPA and Hibernate
In this project we will be learning JPA and Hibernate.

### Approach :
1. Create a Spring boot project with H2(in memory database).
2. Create Course table
3. Use Spring JDBC to play with Course table
4. Use JPA and Hibernate to play with Course table
5. Use Spring Data JPA to play with Course table

### 1 
**application.properties**
```properties
spring.application.name=Jpa-and-Hibernate
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
```
it will enable h2 console in browser, and we are creating a database named testdb.