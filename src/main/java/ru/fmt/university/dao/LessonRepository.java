package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.sources.Query;
import ru.fmt.university.dao.util.LessonMapper;
import ru.fmt.university.dto.*;

import java.util.List;

@Repository
public class LessonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private LessonMapper lessonMapper;

    public Lesson create(Lesson lesson) {
        try {
            jdbcTemplate.update(Query.INSERT_LESSON.getText(), lesson.getCourse().getId(), lesson.getTeacher().getId(),
                    lesson.getClassRoom(), lesson.getDay().toString(), lesson.getStartTime(), lesson.getType().toString());
            if (lesson.getGroups() != null) {
                groupRepository.assignToLesson(lesson, lesson.getGroups());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_INSERT_LESSON, e);
        }

        return lesson;
    }

    public List<Lesson> getAll() {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_ALL_LESSONS.getText(), lessonMapper);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_LESSONS, e);
        }

        return lessons;
    }

    public Lesson getById(int id) {
        Lesson lesson;
        try {
            lesson = jdbcTemplate.queryForObject(Query.GET_LESSON_BY_ID.getText(), lessonMapper, id);
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
                groupRepository.assignToLesson(lesson, lesson.getGroups());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_LESSON, e);
        }

        return lesson;
    }

    public List<Lesson> getByStudent(Student student) {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_STUDENT.getText(), lessonMapper, student.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_STUDENT, e);
        }

        return lessons;
    }

    public List<Lesson> getByTeacher(Teacher teacher) {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_TEACHER.getText(), lessonMapper, teacher.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_TEACHER, e);
        }

        return lessons;
    }

    public List<Lesson> getByGroup(Group group) {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_GROUP.getText(), lessonMapper, group.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_GROUP, e);
        }

        return lessons;
    }

    public List<Lesson> getByCourse(Course course) {
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_COURSE.getText(), lessonMapper, course.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_COURSE, e);
        }

        return lessons;
    }
}