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
