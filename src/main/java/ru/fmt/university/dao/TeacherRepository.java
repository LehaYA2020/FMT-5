package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.mappers.TeacherMapper;
import ru.fmt.university.dao.sources.Query;
import ru.fmt.university.dto.Teacher;

import java.util.List;

@Repository
public class TeacherRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TeacherMapper teacherMapper;

    public Teacher create(Teacher teacher) {
        try {
            jdbcTemplate.update(Query.INSERT_TEACHER.getText(), teacher.getFirstName(), teacher.getLastName(), teacher.getCourse());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_INSERT_TEACHERS_LIST, e);
        }
        return teacher;
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers;
        try {
            teachers = jdbcTemplate.query(Query.GET_ALL_TEACHERS.getText(), teacherMapper);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_TEACHERS, e);
        }
        return teachers;
    }

    public Teacher getById(int id) {
        try {
            return jdbcTemplate.queryForObject(Query.GET_TEACHER_BY_ID.getText(), teacherMapper, id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_TEACHER_BY_ID, e);
        }
    }

    public Teacher update(Teacher teacher) {
        try {
            jdbcTemplate.update(Query.UPDATE_TEACHER_BY_ID.getText(), teacher.getFirstName(), teacher.getLastName(),
                    teacher.getCourse().getId(), teacher.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_TEACHER_BY_ID, e);
        }
        return teacher;
    }
}
