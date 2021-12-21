package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.GroupRepository;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Student;

import java.util.List;

@Component
@Log4j2
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Group create(Group group) {
        log.debug("GroupService calls groupRepository.create({}).", group.getId());
        return groupRepository.create(group);
    }

    public Group getById(int id) {
        log.debug("GroupService calls groupRepository.getById({}).", id);
        return groupRepository.getById(id);
    }

    public List<Group> getAll() {
        log.debug("GroupService calls groupRepository.getAll().");
        return groupRepository.getAll();
    }

    public Group update(Group forUpdate) {
        return groupRepository.update(forUpdate);
    }

    public void delete(int id) {
        log.debug("GroupService calls groupRepository.delete({}).", id);
        groupRepository.delete(id);
    }

    public void assignToCourses(Group group, List<Course> courses) {
        for (Course course:courses) {
            groupRepository.assignToCourse(group, course);
        }
    }

    public void deleteFromCourse(Group group, Course course) {
        groupRepository.deleteFromCourse(group, course);
    }

    public List<Group> getByCourse(Course course) {
        log.debug("GroupService calls groupRepository.getByCourse({}).", course);
        return groupRepository.getByCourse(course);
    }

    public void assignToLesson(Lesson lesson, List<Group> groups) {
        groupRepository.assignToLesson(lesson, groups);
    }

    public void deleteFromLesson(Lesson lesson, Group group) {
        groupRepository.deleteFromLesson(lesson, group);
    }

    public List<Group> getByLesson(Lesson lesson) {
        return groupRepository.getByLesson(lesson);
    }

    public Group getByStudent(Student student) {
        return groupRepository.getByStudent(student);
    }
}
