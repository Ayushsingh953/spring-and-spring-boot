# Spring framework important terminology

* **@Bean :** an object that is managed by the Spring IoC (Inversion of Control) container. Beans are defined in the Spring container configuration and are instantiated, assembled, and managed by the container.
* **@Component (...) :** An instance of class will be managed by Spring.
* **Dependency :** if a class depends on one or more objects.
* **Component Scan :** Spring uses component scan to find component classes.
    * It scans packages! (@ComponentScan(com.ayush953))
* **Dependency Injection :** Identify beans, their dependencies and wire them together (provides IOC - Inversion of control i.e, takes control of creating and wiring of objects away from programmer).
    * **Spring Beans :** An object managed by spring.
    * **IOC container :** Manages the lifecycle of beans and dependencies.
        * **Types :** ApplicationContext(complex), BeanFactory(simple features - rarely used).
    * **Autowiring :** Process of wiring in dependencies for a spring bean. 

## @Component vs @Bean
<table>
<tr>
<th>Heading</th>
<th>@Component</th>
<th>@Bean</th>
</tr>
<tr>
<th>Where</th>
<th>Can be used on any java class</th>
<th>Typically used on methods in Spring Configuration classes</th>
</tr>
<tr>
<th>Ease of use</th>
<th>Very easy - just write annotation</th>
<th>you write all the code</th>
</tr>
<tr>
<th>Autowiring</th>
<th>Yes - Field, Setter or Constructor injection</th>
<th>Yes - method call or method parameters</th>
</tr>
<tr>
<th>Who creates beans?</th>
<th>Spring framework</th>
<th>you write bean creation code</th>
</tr>
<tr>
<th>Recommended for</th>
<th>Instantiating Beans for your Own Application Code : @Component</th>
<th>1. Custom Business logic
    2. Instantiating Beans for 3rd-party libraries: @Bean</th>
</tr>
</table>

# Evolution of Jakarta EE vs J2EE vs JAVA EE
* Enterprise capabilities were initially built into **JDK**.
* With time, they were separated out:
  * **J2EE** - Java 2 Platform enterprise edition.
  * **Java EE** - Java platform enterprise edition (Rebranding).
  * **Jakarta EE** - oracle gave Java EE rights to the Eclipse foundation.
    * **Important Specifications** :
      * Jakarta Server pages(JSP) - used to create views in web application.
      * Jakarta Standard tag library(JSTL) - used to show dynamic information.
      * Jakarta Enterprise Beans(EJB)
      * Jakarta RESTful Web Services(JAX-RS) - build RESTful APIs.
      * Jakarta Bean validation
      * Jakarta Contexts and Dependency Injection(CDI)
      * Jakarta Persistence API(JPA) - interact with relational databases.
    * Supported by Spring 6 and Spring boot 3