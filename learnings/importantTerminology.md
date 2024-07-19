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