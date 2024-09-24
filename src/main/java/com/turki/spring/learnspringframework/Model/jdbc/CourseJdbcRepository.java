package com.turki.spring.learnspringframework.Model.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.turki.spring.learnspringframework.Model.Course;

@Repository
public class CourseJdbcRepository {
    // This is how to use Spring JDBC not JDBC ( Spring JDBC depends on JdbcTemplate)
    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY = """
            INSERT INTO COURSE (id, name, author)
            VALUES(?, ? , ?);
            """;

    private static String DELETE_QUERY = """
            DELETE FROM COURSE WHERE id = ?;
            """;
    private static String UPDATE_QUERY = """
            UPDATE COURSE
            SET name=?, author=?
            WHERE id = ? ;
            """;
    private static String SELECT_QUERY = """
            SELECT * FROM COURSE WHERE id = ?;
            """;

    public void insertCourse(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public void updateCourse(long id, Course course) {
        springJdbcTemplate.update(UPDATE_QUERY, course.getName(), course.getAuthor(), id);
    }

    public Course findById(long id){
        //ResultSet --> Bean Mapper:
        Course course = springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
        return course;
    }

}
