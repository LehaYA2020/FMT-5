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

    public void create(Course course) {
        courseRepository.create(course);
    }

    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    public Course getById(int id) {
        return courseRepository.getById(id);
    }

    public Course update(Course forUpdate) {
        courseRepository.update(forUpdate);
        return forUpdate;
    }

    public void delete(int id) {
        courseRepository.delete(id);
    }

    public List<Course> getByGroupId(int id) {
        return courseRepository.getByGroupId(id);
    }



}
