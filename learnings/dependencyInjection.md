# Dependency injection types

1. **Constructor-based :** Dependencies are set by creating the bean using its constructor.
2. **Setter-based :** Dependencies are set by calling setter methods on your beans.
3. **Field :** No setter or constructor. Dependency is injected using reflection.

### Let's dive deeper with the help of an example :

**first let's create few beans :**
```java
@Component
class ExampleClass{
    Dependency1 dependency1;
    Dependency2 dependency2;

    public String toString(){
        return "Using Dependency 1: " + dependency1 + " and Dependency 2: " + dependency2;
    }
}

@Component
class Dependency1{

}

@Component
class Dependency2{

}
```

## Main Class
```java
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@ComponentScan
public class DependencyInjectionExample {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DependencyInjectionExample.class)){
            System.out.println(context.getBean(ExampleClass.class));

            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }

    }
}
```
1. We have main class which is set as a configuration class, and it will scan for the beans in the same package using component scan tag.
2. It will launch the spring context.
3. It will also print all the beans available in this spring context.

if we run our main class. the output would be : 
```bash
Using Dependency 1: null and Dependency 2: null
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
dependencyInjectionExample
dependency1
dependency2
exampleClass
```
the reason its giving both dependency as null because we haven't configured spring to wire dependency1 and dependency2 to example class.
We can do that by adding **@Autowired** tag to both our dependencies(field injection) as : 
## Field Injection
```java
@Component
class ExampleClass{
    @Autowired
    Dependency1 dependency1;
    @Autowired
    Dependency2 dependency2;

    public String toString(){
        return "Using Dependency 1: " + dependency1 + " and Dependency 2: " + dependency2;
    }
}
```
now the output : 
```bash
Using Dependency 1: com.ayush953.first_program.Dependency1@2ed2d9cb and Dependency 2: com.ayush953.first_program.Dependency2@d5b810e
```
rest of the output will be same.

## Setter injection
we can make use of setters to inject both dependencies to our example class : 
```java
@Component
class ExampleClass{

    Dependency1 dependency1;
    Dependency2 dependency2;

    @Autowired
    public void setDependency1(Dependency1 dependency1) {
        System.out.println("Setter injection - dependency1");
        this.dependency1 = dependency1;
    }
    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        System.out.println("Setter injection - dependency2");
        this.dependency2 = dependency2;
    }
    public String toString(){
        return "Using Dependency 1: " + dependency1 + " and Dependency 2: " + dependency2;
    }
}
```
**Output :**
```bash
 Setter injection - dependency1
Setter injection - dependency2
Using Dependency 1: com.ayush953.first_program.Dependency1@201a4587 and Dependency 2: com.ayush953.first_program.Dependency2@61001b64
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
dependencyInjectionExample
dependency1
dependency2
exampleClass
```

## Constructor Injection
```java
@Component
class ExampleClass{

    Dependency1 dependency1;
    Dependency2 dependency2;

    public ExampleClass(Dependency1 dependency1, Dependency2 dependency2) {
        System.out.println("Constructor Injection");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }
    public String toString(){
        return "Using Dependency 1: " + dependency1 + " and Dependency 2: " + dependency2;
    }
}
```
we don't need to specify **@Autowired**. Spring will automatically detect the constructor and create the objects and wire them for us.

**Output :**
```bash
Constructor Injection
Using Dependency 1: com.ayush953.first_program.Dependency1@5d7148e2 and Dependency 2: com.ayush953.first_program.Dependency2@25fb8912
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
dependencyInjectionExample
dependency1
dependency2
exampleClass
```

**Note :** It is recommended to use constructor based injection as dependencies are automatically set when an object is created.
