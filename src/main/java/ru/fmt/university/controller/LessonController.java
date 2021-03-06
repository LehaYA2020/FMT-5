package ru.fmt.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.service.implementation.LessonService;

import java.util.List;

@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @PostMapping(value = "/lessons")
    public ResponseEntity<?> create(@RequestBody Lesson lesson) {
        lessonService.create(lesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/lessons")
    public ResponseEntity<List<Lesson>> getAll() {
        final List<Lesson> lessons = lessonService.getAll();

        return lessons.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                :  new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> getById(@PathVariable(name = "id") int id) {
        final Lesson lesson = lessonService.getById(id);

        return lesson != null
                ? new ResponseEntity<>(lesson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/lessons/{id}")
    public ResponseEntity<?> update(@RequestBody Lesson lesson) {
        final Lesson updated = lessonService.update(lesson);
        return updated != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/lessons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = lessonService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/lessons/{studentId}")
    public ResponseEntity<List<Lesson>> getLessonByStudent(@PathVariable(name = "studentId") int studentId) {
        final List<Lesson> lessons = lessonService.getLessonsByStudent(studentId);

        return !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{groupId}")
    public ResponseEntity<List<Lesson>> getLessonByGroup(@PathVariable(name = "groupId") int groupId) {
        final List<Lesson> lessons = lessonService.getLessonsByGroup(groupId);

        return !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{teacherId}")
    public ResponseEntity<List<Lesson>> getLessonByTeacher(@PathVariable(name = "teacherId") int teacherId) {
        final List<Lesson> lessons = lessonService.getLessonsByTeacher(teacherId);

        return !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonByCourse(@PathVariable(name = "courseId") int courseId) {
        final List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);

        return !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
