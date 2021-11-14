package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DAOException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.models.Course;

import java.util.List;

@Repository
public class CourseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Course insert(Course course) {
        try {
            jdbcTemplate.update(Query.INSERT_COURSE.getText(), course.getName(), course.getDescription());
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_INSERT_COURSE, e);
        }

        return course;
    }

    public List<Course> getAll() {
        List<Course> courses;

        try {
            courses = jdbcTemplate.query(Query.GET_ALL_COURSES.getText(), new BeanPropertyRowMapper<>(Course.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_COURSES, e);
        }

        return courses;
    }

    public Course getById(int id) {
        try {
            return jdbcTemplate.queryForObject(Query.GET_COURSE_BY_ID.getText(), new Object[]{id},
                    new BeanPropertyRowMapper<>(Course.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_COURSE_BY_ID, e);
        }
    }

    public Course update(Course course) {
        try {
            jdbcTemplate.update(Query.UPDATE_COURSE.getText(), course.getName(), course.getDescription(), course.getId());
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_UPDATE_COURSE, e);
        }
        return course;
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update(Query.DELETE_COURSE.getText(), id);
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_DELETE_COURSE, e);
        }
    }

    public List<Course> getByGroupId(int id) {
        List<Course> courses;

        try {
            courses = jdbcTemplate.query(Query.GET_COURSES_BY_GROUP_ID.getText(),
                    new Object[]{id}, new BeanPropertyRowMapper<>(Course.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_DELETE_COURSE, e);
        }
        return courses;
    }
}
