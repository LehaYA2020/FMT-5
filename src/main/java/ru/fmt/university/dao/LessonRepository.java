package ru.fmt.university.dao;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class LessonRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private LessonMapper lessonMapper;

    public Lesson create(Lesson lesson) {
        log.trace("create({}).", lesson);
        try {
            jdbcTemplate.update(Query.INSERT_LESSON.getText(), lesson.getCourseId(), lesson.getTeacherId(),
                    lesson.getClassRoom(), lesson.getDay().toString(), lesson.getStartTime(), lesson.getType().toString());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_INSERT_LESSON, e);
        }
        log.debug("Created {}.", lesson);

        return lesson;
    }

    public List<Lesson> getAll() {
        log.trace("getAll().");
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_ALL_LESSONS.getText(), lessonMapper);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_ALL_LESSONS, e);
        }
        log.debug("Found {}.", lessons);

        return lessons;
    }

    public Lesson getById(int id) {
        log.trace("getById({}).", id);
        Lesson lesson;
        try {
            lesson = jdbcTemplate.queryForObject(Query.GET_LESSON_BY_ID.getText(), lessonMapper, id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_ID, e);
        }
        log.debug("Found {}.", lesson);

        return lesson;
    }

    public void delete(int id) {
        log.trace("delete({}).", id);
        try {
            jdbcTemplate.update(Query.DELETE_LESSON.getText(), id);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_DELETE_LESSON_BY_ID, e);
        }
        log.debug("Lesson with id={} deleted.", id);
    }

    public Lesson update(Lesson lesson) {
        log.trace("delete({})", lesson);
        try {
            jdbcTemplate.update(Query.UPDATE_LESSON.getText(), lesson.getCourseId(), lesson.getTeacherId(),
                    lesson.getClassRoom(), lesson.getDay().toString(), lesson.getStartTime(),
                    lesson.getType().toString(), lesson.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_LESSON, e);
        }
        log.debug("Lesson {} updated.", lesson);

        return lesson;
    }

    public List<Lesson> getByStudent(Student student) {
        log.trace("getByStudent({})", student);
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_STUDENT.getText(), lessonMapper, student.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_STUDENT, e);
        }
        log.debug("Found {} by student {}.", lessons, student);

        return lessons;
    }

    public List<Lesson> getByTeacher(Teacher teacher) {
        log.trace("getByTeacher({})", teacher);
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_TEACHER.getText(), lessonMapper, teacher.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_TEACHER, e);
        }
        log.debug("Found {} by teacher {}.", lessons, teacher);

        return lessons;
    }

    public List<Lesson> getByGroup(Group group) {
        log.trace("getByGroup({})", group);
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_GROUP.getText(), lessonMapper, group.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_GROUP, e);
        }
        log.debug("Found {} by group {}.", lessons, group);

        return lessons;
    }

    public List<Lesson> getByCourse(Course course) {
        log.trace("getByCourse({})", course);
        List<Lesson> lessons;
        try {
            lessons = jdbcTemplate.query(Query.GET_LESSON_BY_COURSE.getText(), lessonMapper, course.getId());
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new DaoException(MessagesConstants.CANNOT_GET_LESSON_BY_COURSE, e);
        }
        log.debug("Found {} by course {}.", lessons, course);

        return lessons;
    }
}