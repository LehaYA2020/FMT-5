package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.models.Course;
import ru.fmt.university.models.Group;
import ru.fmt.university.models.Lesson;

import java.util.List;

@Repository
public class GroupRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public Group create(Group group) {
        try {
            jdbcTemplate.update(Query.INSERT_GROUP.getText(), group.getName());

        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_INSERT_GROUPS);
        }
        return group;
    }

    public List<Group> getAll() {
        List<Group> groups;
        try {
            groups = jdbcTemplate.query(Query.GET_ALL_GROUPS.getText(), new BeanPropertyRowMapper<>(Group.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_GROUPS, e);
        }
        return groups;
    }

    public Group getById(int id) {
        Group group;
        try {
            group = jdbcTemplate.queryForObject(Query.GET_GROUP_BY_ID.getText(), new Object[]{id}, new BeanPropertyRowMapper<>(Group.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_GROUP_BY_ID, e);
        }
        return group;
    }

    public void delete(int id) {
        try {
            jdbcTemplate.update(Query.DELETE_GROUP.getText(), id);
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_GROUP, e);
        }
    }

    public void assignToCourse(Group group, Course course) {
        try {
            jdbcTemplate.update(Query.ASSIGN_GROUP_TO_COURSE.getText(), group.getId(), course.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_GROUP_TO_COURSE, e);
        }
    }

    public void deleteFromCourse(Group group, Course course) {
        try {
            jdbcTemplate.update(Query.DELETE_GROUP_FROM_COURSE.getText(), group.getId(), course.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_GROUP_FROM_COURSE, e);
        }
    }

    public List<Group> getByLesson(Lesson lesson) {
        List<Group> groups;
        try {
            groups = jdbcTemplate.query(Query.GET_GROUPS_BY_LESSON.getText(), new Object[]{lesson.getId()}, new BeanPropertyRowMapper<>(Group.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_BY_LESSON, e);
        }
        return groups;
    }

    public List<Group> getByCourse(Course course) {
        List<Group> groups;
        try {
            groups = jdbcTemplate.query(Query.GET_GROUPS_BY_COURSE.getText(), new Object[]{course.getId()}, new BeanPropertyRowMapper<>(Group.class));
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_GET_BY_LESSON, e);
        }
        return groups;
    }

    public Group update(Group group) {
        try {
            jdbcTemplate.update(Query.UPDATE_GROUP.getText(), group.getName(), group.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_UPDATE_GROUP, e);
        }
        return group;
    }

    public void assignToLesson(Lesson lesson, List<Group> groups) {
        try {
            for (Group group : groups) {
                jdbcTemplate.update(Query.ASSIGN_GROUP_TO_LESSON.getText(), lesson.getId(), group.getId());
            }
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_ASSIGN_GROUPS_TO_LESSON, e);
        }
    }

    public void deleteFromLesson(Lesson lesson, Group group) {
        try {
            jdbcTemplate.update(Query.DELETE_GROUP_FROM_LESSON.getText(), lesson.getId(), group.getId());
        } catch (DataAccessException e) {
            throw new DaoException(MessagesConstants.CANNOT_DELETE_GROUP_FROM_LESSON, e);
        }
    }
}
