package com.ayush953.Jpa_and_Hibernate.course.Jdbc;

import com.ayush953.Jpa_and_Hibernate.course.Course;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {
    private JdbcTemplate jdbcTemplate;

    public CourseJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String INSERT_QUERY = """
            INSERT INTO COURSE(id,name,author) VALUES (?,?,?);
            """;

    private static String DELETE_QUERY = """
                DELETE FROM COURSE WHERE id = ?;
            """;

    private static String SELECT_QUERY = """
                SELECT * FROM COURSE WHERE id = ?;
            """;

    public void insert(Course course) {
        jdbcTemplate.update(INSERT_QUERY,course.getId(),course.getName(),course.getAuthor());
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course selectById(long id) {
        return jdbcTemplate.queryForObject(SELECT_QUERY,new BeanPropertyRowMapper<>(Course.class) ,id);
    }
}
