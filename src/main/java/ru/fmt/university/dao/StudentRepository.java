package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.exceptions.DAOException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.models.Course;
import ru.fmt.university.models.Group;
import ru.fmt.university.models.Student;

import java.util.List;

@Component
public class StudentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Student> insertStudents(List<Student> students) {
        try {
            for (Student s : students) {
                jdbcTemplate.update(Query.INSERT_STUDENT.getText(), s.getName(), s.getGroup());
            }
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_INSERT_STUDENTS_LIST);
        }
        return students;
    }

    public List<Student> getAllStudents() {
        List<Student> students;
        try {
            students = jdbcTemplate.query(Query.GET_ALL_STUDENTS.getText(), new BeanPropertyRowMapper<>(Student.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_ALL_STUDENTS, e);
        }
        return students;
    }

    public Student getStudentById(int id) {
        Student student;
        try {
            student = jdbcTemplate.queryForObject(Query.GET_STUDENT_BY_ID.getText(), new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
        } catch (EmptyResultDataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_STUDENT_BY_ID, e);
        }
        return student;
    }

    public void deleteById(int id) {
        try {
            jdbcTemplate.update(Query.DELETE_STUDENT.getText(), id);
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_DELETE_STUDENT, e);
        }
    }

    public void assignToGroup(Student student, Group group) {
        try {
            jdbcTemplate.update(Query.ASSIGN_STUDENT_TO_GROUP.getText(), student.getId(), group.getId());
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_ASSIGN_TO_GROUP, e);
        }
    }

    public void assignToCourse(Student student, Course course) {
        try {
            jdbcTemplate.update(Query.ASSIGN_TO_COURSE.getText(), student.getId(), course.getId());
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_ASSIGN_TO_COURSE, e);
        }
    }

    public List<Course> getStudentAssignments(Student student) {
        List<Course> courses;
        try {
            courses = jdbcTemplate.query(Query.GET_STUDENT_ASSIGNMENTS.getText(), new Object[]{student.getId()}, new BeanPropertyRowMapper<>(Course.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_ASSIGNMENTS, e);
        }
        return courses;
    }
}
