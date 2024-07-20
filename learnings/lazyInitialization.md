# Exploring Lazy Initialization of Spring beans.

* Default initialization for Spring beans : Eager i.e. they are initialised as soon as the spring application starts.

## Code :
```java
@Component
class A{

}

@Component
class B{
    A classA;
    public B(A classA){
        System.out.println("initialization of B");
        this.classA = classA;
    }
}
@Configuration
@ComponentScan
public class BusinessSpring {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BusinessSpring.class)) {
        }
    }
}
```
## Output :
```bash
initialization of B

Process finished with exit code 0
```
We can see that the beans are initialised even though we are not using them anywhere.

### We can make beans to be lazily initialised by annotating them **@Lazy**.

In this way they will be initialised only when we use them.

## Code :
```java
@Component
@Lazy
class B{
    A classA;
    public B(A classA){
        System.out.println("initialization of B");
        this.classA = classA;
    }
}
```

Rest of the code will be same and we get the output as :
```bash

Process finished with exit code 0
```

beans are not initialised because we haven't used them anywhere.
Let's call the method of class B to see if they get initialised or not.
```java
@Component
@Lazy
class B{
    A classA;
    public B(A classA){
        System.out.println("initialization of B");
        this.classA = classA;
    }
    public void display(){
        System.out.println("display of B");
    }
}

@Configuration
@ComponentScan
public class BusinessSpring {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BusinessSpring.class)) {
            context.getBean(B.class).display();
        }
    }
}
```
## Output :
```bash
initialization of B
display of B

Process finished with exit code 0
```
B got initialised here.

## Notes :
* Eager initialization is recommended : 
    * Errors in the configuration are discovered immediately at application startup.
* Lazy annotation :
* can be used almost everywhere **@Component** and **@Bean** are used.
* Lazy-resolution proxy will be injected instead of actual dependency.
* can be used on configuration (**@Configuration**) class :
    * All **@Bean** methods within the class will be lazily initialised.