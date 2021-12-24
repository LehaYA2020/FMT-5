package ru.fmt.university.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dao.sources.Query;
import ru.fmt.university.dao.util.GroupMapper;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Student;

import java.util.List;

@Repository
@Log4j2
public class GroupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GroupMapper groupMapper;

    public Group create(Group group) {
        log.trace("create({}).", group);
        try {
            jdbcTemplate.update(Query.INSERT_GROUP.getText(), group.getName());

        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_INSERT_GROUPS, e);
            throw new DaoException(MessagesConstants.CANNOT_INSERT_GROUPS, e);
        }
        log.debug("Group {} created.", group);
        return group;
    }

    public List<Group> getAll() {
        log.trace("getAll()");
        List<Group> groups;
        try {
            groups = jdbcTemplate.query(Query.GET_ALL_GROUPS.getText(), groupMapper);
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_GET_GROUPS, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_GROUPS, e);
        }
        log.debug("Found {}.", groups);
        return groups;
    }

    public Group getById(int id) {
        log.trace("getById({})", id);
        Group group;
        try {
            group = jdbcTemplate.queryForObject(Query.GET_GROUP_BY_ID.getText(), groupMapper, id);
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_GET_GROUP_BY_ID, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_GROUP_BY_ID, e);
        }
        log.debug("Found {}.", group);
        return group;
    }

    public void delete(int id) {
        log.trace("delete({})", id);
        try {
            jdbcTemplate.update(Query.DELETE_GROUP.getText(), id);
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_DELETE_GROUP, e);
            throw new DaoException(MessagesConstants.CANNOT_DELETE_GROUP, e);
        }
        log.debug("Group with id={}.", id);
    }

    public void assignToCourse(Group group, Course course) {
        log.trace("assignToCourse({},{})", group, course);
        try {
            jdbcTemplate.update(Query.ASSIGN_GROUP_TO_COURSE.getText(), group.getId(), course.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_ASSIGN_GROUP_TO_COURSE, e);
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_GROUP_TO_COURSE, e);
        }
        log.debug("Group {} assigned to course {}", group, course);
    }

    public void deleteFromCourse(Group group, Course course) {
        log.trace("deleteFromCourse({}, {})", group, course);
        try {
            jdbcTemplate.update(Query.DELETE_GROUP_FROM_COURSE.getText(), group.getId(), course.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_DELETE_GROUP_FROM_COURSE, e);
            throw new DaoException(MessagesConstants.CANNOT_DELETE_GROUP_FROM_COURSE, e);
        }
        log.debug("Course {} deleted from Course {}", group, course);
    }

    public List<Group> getByLesson(Lesson lesson) {
        log.trace("getByLesson({})", lesson.getId());
        List<Group> groups;
        try {
            groups = jdbcTemplate.query(Query.GET_GROUPS_BY_LESSON.getText(), groupMapper, lesson.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_GET_BY_LESSON, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_BY_LESSON, e);
        }
        log.debug("Found {} by {}", groups, lesson);
        return groups;
    }

    public Group getByStudent(Student student) {
        log.trace("getByStudent({})", student);
        Group group;
        try {
            group = jdbcTemplate.queryForObject(Query.GET_GROUPS_BY_STUDENT.getText(), groupMapper, student.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_GET_BY_STUDENT, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_BY_STUDENT, e);
        }
        log.debug("Found {} by {}", group, student);
        return group;
    }

    public List<Group> getByCourse(Course course) {
        log.trace("getByCourse({})", course);
        List<Group> groups;
        try {
            groups = jdbcTemplate.query(Query.GET_GROUPS_BY_COURSE.getText(), groupMapper, course.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_GET_BY_LESSON, e);
            throw new DaoException(MessagesConstants.CANNOT_GET_BY_LESSON, e);
        }
        log.debug("Found {} by {}", groups, course);
        return groups;
    }

    public Group update(Group group) {
        log.debug("update({}).", group);
        try {
            jdbcTemplate.update(Query.UPDATE_GROUP.getText(), group.getName(), group.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_UPDATE_GROUP, e);
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_GROUP, e);
        }
        log.debug("Group {} updated", group);
        return group;
    }

    public void assignToLesson(Lesson lesson, List<Group> groups) {
        log.debug("assignToLesson({}, {})", lesson, groups);
        try {
            for (Group group : groups) {
                jdbcTemplate.update(Query.ASSIGN_GROUP_TO_LESSON.getText(), lesson.getId(), group.getId());
            }
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_ASSIGN_GROUPS_TO_LESSON, e);
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_GROUPS_TO_LESSON, e);
        }
        log.debug("Groups {} assigned to lesson {})", groups, lesson);
    }

    public void deleteFromLesson(Lesson lesson, Group group) {
        log.debug("deleteFromLesson({}, {})", lesson, group);
        try {
            jdbcTemplate.update(Query.DELETE_GROUP_FROM_LESSON.getText(), lesson.getId(), group.getId());
        } catch (DataAccessException e) {
            log.error(MessagesConstants.CANNOT_DELETE_GROUP_FROM_LESSON, e);
            throw new DaoException(MessagesConstants.CANNOT_DELETE_GROUP_FROM_LESSON, e);
        }
        log.debug("Group {} deleted from lesson {})", group, lesson);
    }
}