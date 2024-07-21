# Exploring PreDestroy and PostConstruct annotation

## PreDestroy :
this annotation is used on a method as a callback notification to signal that the instance is in the process of being removed by the container. The method annotated with PreDestroy is typically used to release resources that it has been holding.

## PostConstruct :
this annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization. This method must be invoked before the class is put into service. This annotation must be supported on all classes that support dependency injection. Only one method in a given class can be annotated with this annotation.

**Let's look at the example :**
```java

@Component
class A{
    private Dependency dependency;
    public A(Dependency dependency){
        super();
        this.dependency = dependency;
        System.out.println("All dependencies are ready!");
    }

    @PostConstruct
    public void initialize(){
        dependency.ready();
    }

    @PreDestroy
    public void cleanup(){
        System.out.println("Clean up!");
    }
}

@Component
class Dependency{
    public void ready(){
        System.out.println("Some initialization logic!");
    }
}

@Configuration
@ComponentScan
public class PostConstructPreDestroySpring {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PostConstructPreDestroySpring.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
```
If we look at the output of this code **initialize** method is invoked just after the dependencies are wired and **cleanup** method is invoked just before the beans are destroyed.
```bash
All dependencies are ready!
Some intialization logic!
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
PostConstructPreDestroySpring
a
dependency
Clean up!

Process finished with exit code 0
```