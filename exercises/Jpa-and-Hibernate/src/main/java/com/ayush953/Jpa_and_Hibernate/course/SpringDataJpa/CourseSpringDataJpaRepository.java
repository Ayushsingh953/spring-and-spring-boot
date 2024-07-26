package com.ayush953.Jpa_and_Hibernate.course.SpringDataJpa;

import com.ayush953.Jpa_and_Hibernate.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course,Long> {
}
