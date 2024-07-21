# Spring beans Scopes

* Spring Beans are defined to be used in a specific scopre :
    + **Singleton** - One object instance per Spring IOC container.
    + **Prototype** - Possibly many object instances per Spring IOC container.
    + Scopes applicable only for web-aware Spring ApplicationContext
        + **Request** - one object instance per HTTP request.
        + **Session** - One object instance per user HTTP session.
        + **Application** - One object instance per web application runtime.
        + **Websocket** - One object instance per WebSocket instance.
* **Java Singleton** vs **Spring singleton**
    + **Spring Singleton** - One object instance per Spring IOC container.
    + **Java Singleton** - One object instance per JVM.

## Code :
```java
@Component
class NormalClass{

}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class PrototypeClass{

}

@Configuration
@ComponentScan
public class SingletonPrototypeSpring {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingletonPrototypeSpring.class)) {
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));

        }
    }
}
```
* We have NormalClass which has singleton bean scope and PrototypeClass which has prototype bean scope.
* If we look at the output the context will give the same bean(object instance) 3 times for the NormalClass but 3 different beans for the PrototypeClass.
```bash
com.ayush953.exercise_1.NormalClass@543588e6
com.ayush953.exercise_1.NormalClass@543588e6
com.ayush953.exercise_1.NormalClass@543588e6
com.ayush953.exercise_1.PrototypeClass@f5acb9d
com.ayush953.exercise_1.PrototypeClass@4fb3ee4e
com.ayush953.exercise_1.PrototypeClass@5d7148e2

Process finished with exit code 0
```
* the reason is that each time we ask the context for the new bean of the prototype class it will create new object instance and return it but for the normal class it will return the same object instance.

## Prototype vs Singleton Bean Scope
<table>
<tr>
<th>Heading</th>
<th>Prototype</th>
<th>Singleton</th>
</tr>
<tr>
<th>Instances</th>
<th>Possibly many per Spring IOC container</th>
<th>One per Spring IOC container</th>
</tr>
<tr>
<th>default</th>
<th>Not default</th>
<th>Default</th>
</tr>
<tr>
<th>Code snippet</th>
<th>@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)</th>
<th>@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) or default</th>
</tr>
<tr>
<th>Usage</th>
<th>Rarely used</th>
<th>Very frequently used</th>
</tr>
<tr>
<th>Recommended Scenario</th>
<th>stateful beans(if bean contains some information)</th>
<th>Stateless beans(if bean does not contain any information)</th>
</tr>
</table>