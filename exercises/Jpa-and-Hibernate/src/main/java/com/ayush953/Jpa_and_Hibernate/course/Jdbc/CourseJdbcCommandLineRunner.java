package com.ayush953.Jpa_and_Hibernate.course.Jdbc;

import com.ayush953.Jpa_and_Hibernate.course.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    CourseJdbcRepository repository;

    public CourseJdbcCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1,"Docker","XYZ"));
        repository.insert(new Course(2,"Spring boot","XYZ"));
        repository.insert(new Course(3,"Apache kafka","XYZ"));

    }
}
