package ru.fmt.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.fmt.university.dto.Course;
import ru.fmt.university.service.CourseService;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping(value = "/courses")
    public ResponseEntity<?> create(@RequestBody Course course) {
        courseService.create(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAll() {
        final List<Course> courses = courseService.getAll();

        return courses != null &&  !courses.isEmpty()
                ? new ResponseEntity<>(courses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getById(@PathVariable(name = "id") int id) {
        final Course course = courseService.getById(id);

        return course != null
                ? new ResponseEntity<>(course, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/courses/{groupId}")
    public ResponseEntity<List<Course>> getByGroupId(@PathVariable(name = "groupId") int groupId) {
        final List<Course> courses = courseService.getByGroupId(groupId);

        return courses != null
                ? new ResponseEntity<>(courses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/courses/{id}")
    public ResponseEntity<?> update(@RequestBody Course course) {
        final Course updatedCourse = courseService.update(course);
        return updatedCourse != null
                ? new ResponseEntity<>(course, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/courses/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = courseService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
