# Jakarta Contexts and Dependency Injection(CDI)
* Spring framework v1 was released in 2004.
* **CDI specification** introduced into java EE 6 platform in december 2009.
* Now called **Jakarta Contexts and Dependency Injection**(CDI).
* CDI is a **specification** (interface).
  * Spring framework implements CDI.
* Important Inject API annotations:
  * Inject (~Autowired in spring)
  * Named (~Component in spring)
  * Qualifier
  * Scope
  * Singleton

## Example
```java
@Component
class A{
    private B b;

    @Autowired
    public void setB(B b) {
        System.out.println("setter injection");
        this.b = b;
    }

    public B getB() {
        return b;
    }
}

@Component
class B{

}

@Configuration
@ComponentScan
public class CDI {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CDI.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(A.class).getB());
        }
    }
}
```
## Output
```bash
setter injection
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
CDI
a
b
com.ayush953.exercise_1.B@26e356f0

Process finished with exit code 0
```
We can make use of CDI injection (you need to add jakarta-inject dependency to use CDI annotations) which is alternative to spring annotations.
```java
import jakarta.inject.Named;
import jakarta.inject.Inject;
@Named
class A{
    private B b;

    @Inject
    public void setB(B b) {
        System.out.println("setter injection");
        this.b = b;
    }

    public B getB() {
        return b;
    }
}

@Named
class B{

}
```
rest of the code will be same and we will get the same output.