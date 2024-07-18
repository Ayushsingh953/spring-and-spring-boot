# hello-spring documentation

it explains necessary parts of hello-spring program

## Concepts :
**Bean :** bean is simply an object that is managed by the Spring IoC (Inversion of Control) container. Beans are defined in the Spring container configuration and are instantiated, assembled, and managed by the container.

**Configuration :** Configuration refers to how you provide instructions to the Spring IoC container about which objects (beans) to instantiate, configure, and assemble.

**@Primary :** The @Primary annotation in Spring is used to give a higher preference to a bean when multiple beans of the same type are candidates for autowiring.

**Qualifier :** The @Qualifier annotation in Spring is used to explicitly specify which bean should be injected when multiple beans of the same type are present.

## Codes :
## HelloSpringConfiguration Class
**Person and Address Records :**
```java
// Introduced in JDK 16
// Automatically creates constructor, getter, and setter
record Person(String name, int age, Address address) {}

// Address record
record Address(String street, String city) {}
```
**HelloSpringConfiguration Class Declaration :**
```java
@Configuration
public class HelloSpringConfiguration {
```
This annotates the class as a Spring configuration class, indicating that Spring will manage beans defined in this class.

**name() Bean :**
```java
@Bean
public String name() {
    return "XYZ";
}
```

**age() Bean :**
```java
@Bean
public int age() {
    return 10;
}
```

**person() Bean :**
```java
@Bean
public Person person() {
    return new Person("Sherlock Holmes", 25, new Address("Baker Street", "London"));
}
```
This method defines a bean named person that returns a Person object with specified values and an Address.

**person2() Bean :**
```java
// creating bean using existing bean - method call
@Bean
public Person person2() {
    return new Person(name(), age(), address());
}
```
This method defines a bean named person2 that uses existing beans (name() and age()) to construct a Person object.

**person3() Bean :**
```java
@Bean
public Person person3(String name, int age, Address addr2) {
    return new Person(name, age, addr2);
}
```
This method defines a bean named person3 that uses method parameters to construct a Person object.

**person4() Bean :**
```java
@Bean
public Person person4(String name, int age, @Qualifier("addr2qualifier") Address address) {
    return new Person(name, age, address);
}
```
This method defines a bean named person4 that uses method parameters and a @Qualifier to specify which Address bean to inject.

**address() Bean :**
```java
@Bean(name="addr")
@Primary
public Address address() {
    return new Address("Baker Street", "London");
}
```
This method defines a bean named address (also known as addr) as primary, which returns an Address object. The @Primary annotation marks this bean as the preferred choice when autowiring by type.

**address2() Bean :**
```java
@Bean(name="addr2")
@Qualifier("addr2qualifier")
public Address address2() {
    return new Address("Main Street", "London");
}
```
This method defines a bean named address2 with a qualifier addr2qualifier, which returns a different Address object. The @Qualifier annotation helps in disambiguating beans when there are multiple candidates for autowiring.

## HelloSpring Main Class
```java
// Launch Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloSpringConfiguration.class);
```
The AnnotationConfigApplicationContext in Spring is a class that allows you to bootstrap a Spring container based on Java configuration classes annotated with @Configuration.

```java
// Retrieve name context from spring
System.out.println(context.getBean("name"));
System.out.println(context.getBean("age"));
System.out.println(context.getBean("person"));
System.out.println(context.getBean("person2"));
System.out.println(context.getBean("person3"));
System.out.println(context.getBean("person4"));
System.out.println(context.getBean(Address.class));
```
Retrieve beans from context

```java
// get all beans managed by spring
Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
```
retrieves and prints the names of all beans managed by the Spring IoC container. It's a concise way to see which beans Spring has instantiated and is managing based on your configuration classes (HelloSpringConfiguration in this case).