package com.ayush953.Jpa_and_Hibernate.course.Jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    private JdbcTemplate jdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String INSERT_QUERY = """
            INSERT INTO COURSE(id,name,author) VALUES (1,'Learn Spring boot','xyz');
            """;

    public void insert() {
        jdbcTemplate.update(INSERT_QUERY);
    }
}
