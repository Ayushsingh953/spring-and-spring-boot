package com.ayush953.Jpa_and_Hibernate.course.Jpa;

import com.ayush953.Jpa_and_Hibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public Course selectById(long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(long id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }
}
