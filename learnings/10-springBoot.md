# Spring boot
Before Spring boot we needed multiple frameworks to build application.
* Build a REST API - we needed Spring,Spring MVC,Tomcat,JSON conversion.
* Write unit tests - We needed Spring Test,JUnit,Mockito


  But now Spring boot has grouped them together into a bunch of starter projects which we can use to build different types of applications.

* Web Application & Rest API - Spring Boot starter web(consists - spring-webmvc,spring-web,spring-boot-starter-tomcat,spring-boot-starter-json)
* Unit Tests - Spring boot starter test
* Talk to database using JPA - Spring boot data JPA
* Talk to database using JDBC - Spring boot starter JDBC
* Secure your web application or REST API - spring boot starter security

## Spring Boot vs Spring MVC vs Spring

* **Spring Framework** - Dependency Injection
  * @Component, @Autowired, Component Scan
  * **Spring Modules and Spring Projects** - Extends Spring ecosystem
    * Provide good integration with other frameworks(Hibernate/JPA,JUnit & Mockito)

* **Spring MVC** (Spring Module) - Simplify building web apps and REST API
  * @Controller,@RestController, @RequestMapping()
* **Spring Boot** (Spring Project) - Build Production ready apps 
  * **Starter Projects** - Make it easy to build variety of applications
  * **Auto Configuration** - Eliminate configuration to set up Spring, Spring MVC and other frameworks!
  * Enables non functional requirements(NFRs) :
    * **Actuator** - Enables advanced monitoring of applications
    * **Embedded Server** - No need for separate application server
    * Logging and error handling
    * Profiles and configuration properties