package ru.fmt.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.GroupRepository;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Student;

import java.util.List;

@Component
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public Group create(Group group) {
        return groupRepository.create(group);
    }

    public Group getById(int id) {
        return groupRepository.getById(id);
    }

    public List<Group> getAll() {
        return groupRepository.getAll();
    }

    public Group update(Group forUpdate) {
        return groupRepository.update(forUpdate);
    }

    public void delete(int id) {
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
