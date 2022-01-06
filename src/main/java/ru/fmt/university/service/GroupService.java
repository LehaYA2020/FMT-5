package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.GroupRepository;
import ru.fmt.university.dto.Group;

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
        log.debug("GroupService calls groupRepository.update({}).", forUpdate);
        return groupRepository.update(forUpdate);
    }

    public boolean delete(int id) {
        log.debug("GroupService calls groupRepository.delete({}).", id);
        return groupRepository.delete(id);
    }

    public boolean assignToCourse(int groupId, int courseId) {
        log.debug("GroupService calls groupRepository.assignToCourse({}, {}).", groupId, courseId);
        return groupRepository.assignToCourse(groupId, courseId);
    }

    public boolean deleteFromCourse(int groupId, int courseId) {
        log.debug("GroupService calls groupRepository.deleteFromCourse({}, {}).", groupId, courseId);
        return groupRepository.deleteFromCourse(groupId, courseId);
    }

    public List<Group> getByCourse(int courseId) {
        log.debug("GroupService calls groupRepository.getByCourse({}).", courseId);
        return groupRepository.getByCourse(courseId);
    }

    public boolean assignToLesson(int lessonId, int groupId) {
        log.debug("GroupService calls groupRepository.deleteFromLesson({}, {}).", lessonId, groupId);
        return groupRepository.assignToLesson(lessonId, groupId);
    }

    public boolean deleteFromLesson(int lessonId, int groupId) {
        log.debug("GroupService calls groupRepository.deleteFromLesson({}, {}).", lessonId, groupId);
        return groupRepository.deleteFromLesson(lessonId, groupId);
    }

    public List<Group> getByLesson(int lessonId) {
        log.debug("GroupService calls groupRepository.getByCourse({}).", lessonId);
        return groupRepository.getByLesson(lessonId);
    }

    public Group getByStudent(int studentId) {
        return groupRepository.getByStudent(studentId);
    }
}
