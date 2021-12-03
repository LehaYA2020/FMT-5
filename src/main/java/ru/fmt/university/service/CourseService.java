package ru.fmt.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.CourseRepository;
import ru.fmt.university.dto.Course;

import java.util.List;

@Component
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private GroupService groupService;

    public void create(Course course) {
        courseRepository.create(course);
    }

    public List<Course> getAll() {
        List<Course> courses = courseRepository.getAll();
            fillGroups(courses);
            fillLessons(courses);
        return courses;
    }

    public Course getById(int id) {
        Course course = courseRepository.getById(id);
        fillGroups(course);
        fillLessons(course);
        return course;
    }

    public Course update(Course forUpdate) {
        courseRepository.update(forUpdate);
        return forUpdate;
    }

    public void delete(int id) {
        courseRepository.delete(id);
    }

    public List<Course> getByGroupId(int id) {
        List<Course> courses = courseRepository.getByGroupId(id);
            fillGroups(courses);
            fillLessons(courses);
        return courses;
    }

    private void fillLessons(Course course) {
        course.setLessons(lessonService.getLessonsByCourse(course));
    }

    private void fillGroups(Course course) {
        course.setGroups(groupService.getByCourse(course));
    }

    private void fillLessons(List<Course> courses) {
        for(Course course:courses) {
            fillLessons(course);
        }
    }

    private void fillGroups(List<Course> courses) {
        for(Course course:courses) {
            fillGroups(course);
        }
    }

}
