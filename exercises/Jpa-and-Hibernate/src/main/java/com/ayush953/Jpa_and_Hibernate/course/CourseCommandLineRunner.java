package com.ayush953.Jpa_and_Hibernate.course;

import com.ayush953.Jpa_and_Hibernate.course.Jdbc.CourseJdbcRepository;
import com.ayush953.Jpa_and_Hibernate.course.Jpa.CourseJpaRepository;
import com.ayush953.Jpa_and_Hibernate.course.SpringDataJpa.CourseSpringDataJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    //CourseJdbcRepository repository;

    /* public CourseCommandLineRunner(CourseJdbcRepository repository) {
        this.repository = repository;
    } */

    /* CourseJpaRepository repository;
    public CourseCommandLineRunner(CourseJpaRepository repository) {
        this.repository = repository;
    } */

    CourseSpringDataJpaRepository repository;
    public CourseCommandLineRunner(CourseSpringDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1,"Docker","XYZ"));
        repository.save(new Course(2,"Spring boot","XYZ"));
        repository.save(new Course(3,"Apache kafka","XYZ"));

        repository.deleteById(1L);

        System.out.println(repository.findById(2L));
        System.out.println(repository.findById(3L));
    }
}
