### Let's create our first spring boot project.
We are going to create a simple REST API to understand the core concepts of spring boot.

### First let's create a controller which will retrieve the courses and return it in json format.
```java
package com.ayush953.hello_spring_boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    @RequestMapping("/courses")
    public List<Course> getCourses() {
        return Arrays.asList(
                new Course(1,"learn spring","xyz"),
                new Course(2,"learn docker","xyz")
        );
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }
}
```
We have created a mapping of /courses to a method which return a list of courses.
when we hit an endpoint(http://localhost:8080/courses) it will return the courses in JSON format.

### We have also created one simple class course.
```java
package com.ayush953.hello_spring_boot;

public class Course {
    private long id;
    private String name;
    private String author;

    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
}
```
there isn't much to explain in this class. it has a constructor and getter methods only and few variables which is used by CourseController class to retrieve the courses.

## We can change the logging level of our application to get more information 
we can do this in application.properties file
```properties
logging.level.org.springframework="DEBUG"
```
levels of logging - 
* trace
* debug
* info
* warning
* error
* off

## using different profiles for development or production environment
By default, the application uses application.properties, but we can configure different profiles based on our environment.

Let's create dev and prod profiles - application-dev.properties and application-prod.properties

**application-dev.properties**
```properties
logging.level.org.springframework=trace
```
**application-prod.properties**
```properties
logging.level.org.springframework=info
```

Tell **application.properties** which profile to use :

**application.properties**
```properties
spring.application.name=hello-spring-boot
logging.level.org.springframework=debug
spring.profiles.active=dev
```
For now, we are only using different logging levels, but we can do a lot more.