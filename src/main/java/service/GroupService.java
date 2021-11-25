package service;

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
        Group group = groupRepository.getById(id);
        setStudents(group);
        setCourses(group);
        return group;
    }

    public List<Group> getAll() {
        List<Group> groups = groupRepository.getAll();
        setStudents(groups);
        setCourses(groups);
        return groups;
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
        List<Group> groups = groupRepository.getByCourse(course);
        setStudents(groups);
        setStudents(groups);
        return groups;
    }

    public void assignToLesson(Lesson lesson, List<Group> groups) {
        groupRepository.assignToLesson(lesson, groups);
    }

    public void deleteFromLesson(Lesson lesson, Group group) {
        groupRepository.deleteFromLesson(lesson, group);
    }

    public List<Group> getByLesson(Lesson lesson) {
        List<Group> groups = groupRepository.getByLesson(lesson);
        setStudents(groups);
        setStudents(groups);
        return groups;
    }

    public Group getByStudent(Student student) {
        Group group = groupRepository.getByStudent(student);
        setStudents(group);
        setStudents(group);
        return group;
    }

    private void setStudents(Group group) {
        group.setStudents(studentService.getByGroup(group.getId()));
    }

    private void setStudents(List<Group> groups) {
        for (Group group:groups) {
            setStudents(group);
        }
    }

    private void setCourses(Group group) {
        group.setCourses(courseService.getByGroupId(group.getId()));
    }

    private void setCourses(List<Group> groups) {
        for (Group group:groups) {
            setCourses(group);
        }
    }
}
