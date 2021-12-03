package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.sources.Query;
import ru.fmt.university.dao.util.CourseMapper;
import ru.fmt.university.dto.Course;

import java.util.List;

@Repository
public class CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CourseMapper courseMapper;

    public Course create(Course course) {
        try {
            jdbcTemplate.update(Query.INSERT_COURSE.getText(), course.getName(), course.getDescription());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_INSERT_COURSE, e);
        }

        return course;
    }

    public List<Course> getAll() {
        List<Course> courses;

        try {
            courses = jdbcTemplate.query(Query.GET_ALL_COURSES.getText(), courseMapper);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_COURSES, e);
        }

        return courses;
    }

    public Course getById(int id) {
        try {
            return jdbcTemplate.queryForObject(Query.GET_COURSE_BY_ID.getText(),
                    courseMapper,  id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_COURSE_BY_ID, e);
        }
    }

    public Course update(Course course) {
        try {
            jdbcTemplate.update(Query.UPDATE_COURSE.getText(), course.getName(), course.getDescription(), course.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_COURSE, e);
        }
        return course;
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update(Query.DELETE_COURSE.getText(), id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_COURSE, e);
        }
    }

    public List<Course> getByGroupId(int id) {
        List<Course> courses;

        try {
            courses = jdbcTemplate.query(Query.GET_COURSES_BY_GROUP_ID.getText(), courseMapper, id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_COURSE, e);
        }
        return courses;
    }
}
