package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.sources.Query;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Student;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student create(Student student) {
        try {
            jdbcTemplate.update(Query.INSERT_STUDENT.getText(), student.getFirstName(), student.getLastName());
            if (student.getGroup() != null) {
                assignToGroup(student, student.getGroup());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_INSERT_STUDENT, e);
        }
        return student;
    }

    public List<Student> getAll() {
        List<Student> students;
        try {
            students = jdbcTemplate.query(Query.GET_ALL_STUDENTS.getText(), new BeanPropertyRowMapper<>(Student.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_STUDENTS, e);
        }
        return students;
    }

    public Student getById(int id) {
        Student student;
        try {
            student = jdbcTemplate.queryForObject(Query.GET_STUDENT_BY_ID.getText(), new BeanPropertyRowMapper<>(Student.class), id);
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_STUDENT_BY_ID, e);
        }
        return student;
    }

    public Student update(Student student) {
        try {
            jdbcTemplate.update(Query.UPDATE_STUDENT.getText(), student.getFirstName(), student.getLastName(), student.getId());
            if (student.getGroup() != null) {
                assignToGroup(student, student.getGroup());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_STUDENT, e);
        }
        return student;
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update(Query.DELETE_STUDENT.getText(), id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_STUDENT, e);
        }
    }

    public void assignToGroup(Student student, Group group) {
        try {
            jdbcTemplate.update(Query.ASSIGN_STUDENT_TO_GROUP.getText(), student.getId(), group.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_TO_GROUP, e);
        }
    }

    public void updateGroupAssignment(Student student, Group group) {
        try {
            jdbcTemplate.update(Query.UPDATE_STUDENT_ASSIGNMENTS.getText(), group.getId(), student.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_STUDENT_ASSIGNMENTS, e);
        }
    }

    public List<Student> getByGroupId(int groupId) {
        List<Student> students;
        try {
            students = jdbcTemplate.query(Query.GET_STUDENT_BY_GROUP.getText(), new BeanPropertyRowMapper<>(Student.class), groupId);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_STUDENT_ASSIGNMENTS, e);
        }
        return students;
    }

    public void deleteFromGroup(Student student, Group group) {
        try {
            jdbcTemplate.update(Query.DELETE_STUDENT_FROM_GROUP.getText(), student.getId(), group.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_STUDENT_FROM_GROUP, e);
        }
    }
}
