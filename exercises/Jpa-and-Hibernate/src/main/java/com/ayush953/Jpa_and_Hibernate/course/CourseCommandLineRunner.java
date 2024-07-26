package com.ayush953.Jpa_and_Hibernate.course;

import com.ayush953.Jpa_and_Hibernate.course.Jdbc.CourseJdbcRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    CourseJdbcRepository repository;

    public CourseCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    }
    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1,"Docker","XYZ"));
        repository.insert(new Course(2,"Spring boot","XYZ"));
        repository.insert(new Course(3,"Apache kafka","XYZ"));

        repository.deleteById(1);

        System.out.println(repository.selectById(2));
        System.out.println(repository.selectById(3));
    }
}
