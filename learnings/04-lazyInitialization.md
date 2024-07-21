# Exploring Lazy Initialization of Spring beans.

* Default initialization for Spring beans : Eager i.e. they are initialized as soon as the spring application starts.

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
public class LazyInitializationSpring {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyInitializationSpring.class)) {
        }
    }
}
```
## Output :
```bash
initialization of B

Process finished with exit code 0
```
We can see that the beans are initialized even though we are not using them anywhere.

### We can make beans to be lazily initialized by annotating them **@Lazy**.

In this way they will be initialized only when we use them.

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

beans are not initialized because we haven't used them anywhere.
Let's call the method of class B to see if they get initialized or not.
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
public class LazyInitializationSpring {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LazyInitializationSpring.class)) {
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
B got initialized here.

## Notes :
* Eager initialization is recommended : 
    * Errors in the configuration are discovered immediately at application startup.
* Lazy annotation :
* can be used almost everywhere **@Component** and **@Bean** are used.
* Lazy-resolution proxy will be injected instead of actual dependency.
* can be used on configuration (**@Configuration**) class :
    * All **@Bean** methods within the class will be lazily initialized.

## Lazy vs eager initialization
<table>
<tr>
<th>Heading</th>
<th>Lazy initialization</th>
<th>Eager initialization</th>
</tr>
<tr>
<th>initialization time</th>
<th>Beans are initialized when it is first made use of in the application</th>
<th>Beans are initialized at the startup of the application</th>
</tr>
<tr>
<th>default</th>
<th>Not default</th>
<th>Default</th>
</tr>
<tr>
<th>Code snippet</th>
<th>@Lazy or @Lazy(value=true)</th>
<th>@Lazy(value=false) or Absence of @Lazy</th>
</tr>
<tr>
<th>Errors in initializing</th>
<th>Errors will result in runtime exceptions</th>
<th>Errors will prevent application from starting up</th>
</tr>
<tr>
<th>Usage</th>
<th>Rarely used</th>
<th>Very frequently used</th>
</tr>
<tr>
<th>Memory consumption</th>
<th>Less (until bean is initialized)</th>
<th>All beans are initialized at startup</th>
</tr>
<tr>
<th>Recommended Scenario</th>
<th>Beans very rarely used in your app</th>
<th>Most of your beans</th>
</tr>
</table>