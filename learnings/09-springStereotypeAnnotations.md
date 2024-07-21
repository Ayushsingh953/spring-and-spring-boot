# Spring Stereotype annotations - @Component and more

* **@Component** - Generic annotation applicable for any class.
  * Base for all spring Stereotype Annotations
  * Specializations of **@Component**:
    * **@Service** - Indicates that an annotated class has business logic.
    * **@Controller** - Indicates that an annotated class is a "Controller"(e.g a web controller).
      * Used to define controllers in your web applications and REST API.
    * **@Repository** - Indicates that an annotated class is used to retrieve and/or manipulate data in database.

### We should use the most specific annotation Because -
* By using specific annotation, you are giving more information to the framework about your intentions.
* You can use AOP(aspect oriented programming) at a later point to add additional behavior.
  * **Example** - For **@Repository**, Spring automatically wires *JDBC Exception translation features.

*JDBC - Java Database Connectivity is an API which allows developers to interact with databases.

## Spring Annotations :
<table>
<tr>
<th>Annotation</th>
<th>Description</th>
</tr>
<tr>
<th>@Configuration</th>
<td>Indicates that a class declares one or more **@Bean** methods and may be processed by the Spring container to generate bean definitions</td>
</tr>
<tr>
<th>@ComponentScan</th>
<td>Define specific packages to scan for components. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation</td>
</tr>
<tr>
<th>@Bean</th>
<td>Indicates that a method produces a bean to be managed by the spring container</td>
</tr>
<tr>
<th>@Component</th>
<td>Indicates that an annotated class is a component which will be utilised by spring to create bean</td>
</tr>
<tr>
<th>@Service</th>
<th>Specialization of **@Component** indicating that an annotated class has business logic</th>
</tr>
<tr>
<th>@Controller</th>
<td>Specialization of **@Component** indicating that an annotated class is a controller</td>
</tr>
<tr>
<th>@Repository</th>
<td>Specialization of **@Component** indicating that an annotated class is used to retrieve and/or manipulate data in database</td>
</tr>
<tr>
<th>@Primary</th>
<td>Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single valued dependency</td>
</tr>
<tr>
<th>@Qualifier</th>
<td>Used on a field or parameter as a qualifier for candidate bean when autowiring</td>
</tr>
<tr>
<th>@Lazy</th>
<td>Indicates that the bean has to be lazily initialized. Absence of it will lead to eager initialization</td>
</tr>
<tr>
<th>@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)</th>
<td>Defines a bean to be prototyped - new instance will be created everytime you refer to bean. Default scope is singleton - one instance per IOC container</td>
</tr>
<tr>
<th>@PreDestroy</th>
<td>Identifies the method that will receive the callback notification to signal that the instance is in the process of being removed by the container. The method annotated with PreDestroy is typically used to release resources that it has been holding.</td>
</tr>
<tr>
<th>@PostConstruct</th>
<td>Identifies the method that needs to be executed after dependency injection is done to perform any initialization.</td>
</tr>
<tr>
<th>@Named</th>
<td>Jakarta Contexts and Dependency Injection(CDI) Annotation similar to Component</td>
</tr>
<tr>
<th>@Inject</th>
<th>Jakarta Contexts and Dependency Injection(CDI) Annotation similar to Autowired</th>
</tr>
</table>