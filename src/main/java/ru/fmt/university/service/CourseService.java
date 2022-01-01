package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.CourseRepository;
import ru.fmt.university.dto.Course;

import java.util.List;

@Component
@Log4j2
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public void create(Course course) {
        log.debug("CourseService calls c0pourseRepository.create({}).", course.getId());
        courseRepository.create(course);
    }

    public List<Course> getAll() {
        log.debug("CourseService calls courseRepository.getAll().");
        return courseRepository.getAll();
    }

    public Course getById(int id) {
        log.debug("CourseService calls courseRepository.getById({}).", id);
        return courseRepository.getById(id);
    }

    public Course update(Course forUpdate) {
        log.debug("CourseService calls courseRepository.update({}).", forUpdate.getId());
        return courseRepository.update(forUpdate);
    }

    public boolean delete(int id) {
        log.debug("CourseService calls courseRepository.delete({}).", id);
        return  courseRepository.delete(id);
    }

    public List<Course> getByGroupId(int id) {
        log.debug("CourseService calls courseRepository.getByGroupId({}).", id);
        return courseRepository.getByGroupId(id);
    }
}
