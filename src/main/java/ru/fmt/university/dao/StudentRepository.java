package ru.fmt.university.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.sources.Query;
import ru.fmt.university.dao.util.StudentMapper;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Student;

import java.util.List;

@Repository
@Log4j2
public class StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentMapper studentMapper;

    public Student create(Student student) {
        log.trace("create({})", student);
        try {
            jdbcTemplate.update(Query.INSERT_STUDENT.getText(), student.getFirstName(), student.getLastName());
            if (student.getGroupId() != 0) {
                assignToGroup(student, student.getGroupId());
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_INSERT_STUDENT, e);
        }
        log.debug("{} created", student);
        return student;
    }

    public List<Student> getAll() {
        log.trace("getAll()");
        List<Student> students;
        try {
            students = jdbcTemplate.query(Query.GET_ALL_STUDENTS.getText(), studentMapper);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_STUDENTS, e);
        }
        log.debug("Found {}", students);
        return students;
    }

    public Student getById(int id) {
        log.trace("getById({})", id);
        Student student;
        try {
            student = jdbcTemplate.queryForObject(Query.GET_STUDENT_BY_ID.getText(), studentMapper, id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_STUDENT_BY_ID, e);
        }
        log.debug("Found {}", student);
        return student;
    }

    public Student update(Student student) {
        log.trace("update({})", student);
        try {
            jdbcTemplate.update(Query.UPDATE_STUDENT.getText(), student.getFirstName(), student.getLastName(), student.getId());
            if (student.getGroupId() != 0) {
                updateGroupAssignment(student, student.getGroupId());
            }
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_STUDENT, e);
        }
        log.debug("Student with id={} updated", student.getId());

        return student;
    }

    public void delete(int id) {
        log.trace("delete({})", id);
        try {
            jdbcTemplate.update(Query.DELETE_STUDENT.getText(), id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_DELETE_STUDENT, e);
        }
        log.debug("Student with id={} deleted", id);
    }

    public void assignToGroup(Student student, int groupId) {
        log.trace("assignToGroup({}, {})", student, groupId);
        try {
            jdbcTemplate.update(Query.ASSIGN_STUDENT_TO_GROUP.getText(), student.getId(), groupId);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_TO_GROUP, e);
        }
        log.debug("Student's {} assigned to group with id = {}", student, groupId);
    }

    public void updateGroupAssignment(Student student, int groupId) {
        log.trace("updateGroupAssignments({}, {})", student, groupId);
        try {
            jdbcTemplate.update(Query.UPDATE_STUDENT_ASSIGNMENTS.getText(), groupId, student.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_STUDENT_ASSIGNMENTS, e);
        }
        log.debug("Student's {} group assignments updated with {}", student, groupId);
    }

    public List<Student> getByGroupId(int groupId) {
        log.trace("getByGroupId({})", groupId);
        List<Student> students;
        try {
            students = jdbcTemplate.query(Query.GET_STUDENT_BY_GROUP.getText(), studentMapper, groupId);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_STUDENT_ASSIGNMENTS, e);
        }
        log.debug("Students {} found by {}", students, groupId);

        return students;
    }

    public void deleteFromGroup(Student student, Group group) {
        log.trace("deleteFromGroup({}, {})", student, group);
        try {
            jdbcTemplate.update(Query.DELETE_STUDENT_FROM_GROUP.getText(), student.getId(), group.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_DELETE_STUDENT_FROM_GROUP, e);
        }
        log.debug("Student {} deleted from Group {})", student, group);
    }
}
