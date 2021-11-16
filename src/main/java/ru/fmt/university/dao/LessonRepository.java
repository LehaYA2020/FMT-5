package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.models.Group;
import ru.fmt.university.models.Lesson;
import ru.fmt.university.models.Student;
import ru.fmt.university.models.Teacher;

import java.util.List;

public class LessonRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Lesson create(Lesson lesson) {
        try {
            jdbcTemplate.update(Query.INSERT_LESSON.getText(), lesson.getCourse().getId(), lesson.getTeacher().getId(),
                    lesson.getClassRoom(), lesson.getDay().toString(), lesson.getStartTime(), lesson.getType().toString());
            if (lesson.getGroups() != null) {
                assignGroups(lesson, lesson.getGroups());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_INSERT_LESSON, e);
        }

        return lesson;
    }

    public List<Lesson> getAll() {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_ALL_LESSONS.getText(), new BeanPropertyRowMapper<>(Lesson.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_LESSONS, e);
        }

        return lessons;
    }

    public Lesson getById(int id) {
        Lesson lesson;
        try {
            lesson = jdbcTemplate.queryForObject(Query.GET_LESSON_BY_ID.getText(), new Object[]{id}, new BeanPropertyRowMapper<>(Lesson.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_ID, e);
        }

        return lesson;
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update(Query.DELETE_LESSON.getText(), id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_LESSON_BY_ID, e);
        }
    }

    public Lesson update(Lesson lesson) {
        try {
            jdbcTemplate.update(Query.UPDATE_LESSON.getText(), lesson.getCourse().getId(), lesson.getTeacher().getId(),
                    lesson.getClassRoom(), lesson.getDay().toString(), lesson.getStartTime(),
                    lesson.getType().toString(), lesson.getId());
            if (lesson.getGroups() != null) {
                assignGroups(lesson, lesson.getGroups());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_LESSON, e);
        }

        return lesson;
    }

    public List<Lesson> getByStudent(Student student) {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_STUDENT.getText(), new Object[]{student.getId()}, new BeanPropertyRowMapper<>(Lesson.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_STUDENT, e);
        }

        return lessons;
    }

    public List<Lesson> getByTeacher(Teacher teacher) {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_TEACHER.getText(), new Object[]{teacher.getId()}, new BeanPropertyRowMapper<>(Lesson.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_TEACHER, e);
        }

        return lessons;
    }

    public void assignGroups(Lesson lesson, List<Group> groups) {
        try {
            for (Group group : groups) {
                jdbcTemplate.update(Query.ASSIGN_GROUP_TO_LESSON.getText(), lesson.getId(), group.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_GROUPS_TO_LESSON, e);
        }
    }

    public void deleteGroup(Lesson lesson, Group group) {
        try {
            jdbcTemplate.update(Query.DELETE_GROUP_FROM_LESSON.getText(), lesson.getId(), group.getId());
        } catch (DataAccessException e) {
            throw  new DaoException(MessagesConstants.CANNOT_DELETE_GROUP_FROM_LESSON, e);
        }
    }
}
