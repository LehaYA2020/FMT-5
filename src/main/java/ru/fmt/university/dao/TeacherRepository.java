package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DAOException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.models.Teacher;

import java.util.List;

@Repository
public class TeacherRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Teacher insert(Teacher teacher) {
        try {
            jdbcTemplate.update(Query.INSERT_TEACHER.getText(), teacher.getFirstName(), teacher.getCourse());
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_INSERT_TEACHERS_LIST);
        }
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers;
        try {
            teachers = jdbcTemplate.query(Query.GET_ALL_TEACHERS.getText(), new BeanPropertyRowMapper<>(Teacher.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_ALL_TEACHERS);
        }
        return teachers;
    }

    public Teacher getById(int id) {
        try {
            return jdbcTemplate.queryForObject(Query.GET_TEACHER_BY_ID.getText(), new Object[]{id}, new BeanPropertyRowMapper<>(Teacher.class));
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_TEACHER_BY_ID);
        }
    }

    public Teacher update(Teacher teacher) {
        try {
            jdbcTemplate.update(Query.UPDATE_TEACHER_BY_ID.getText(), teacher.getFirstName(), teacher.getLastName(), teacher.getCourse().getId(), teacher.getId());
        } catch (DataAccessException e) {
            throw new DAOException(MessagesConstants.CANNOT_UPDATE_TEACHER_BY_ID);
        }
        return teacher;
    }
}
