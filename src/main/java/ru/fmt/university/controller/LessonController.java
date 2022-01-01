package ru.fmt.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmt.university.dto.*;
import ru.fmt.university.service.LessonService;

import java.util.List;

@RestController
public class LessonController {
    @Autowired
    LessonService lessonService;

    @PostMapping(value = "/lessons")
    public ResponseEntity<?> create(@RequestBody Lesson lesson) {
        lessonService.create(lesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/lessons")
    public ResponseEntity<List<Lesson>> getAll() {
        final List<Lesson> lessons = lessonService.getAll();

        return lessons != null &&  !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/lessons/{student}")
    public ResponseEntity<List<Lesson>> getByStudent(@PathVariable(name = "student") Student student) {
        final List<Lesson> lessons = lessonService.getLessonsByStudent(student);

        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{group}")
    public ResponseEntity<List<Lesson>> getByGroup(@PathVariable(name = "group") Group group) {
        final List<Lesson> lessons = lessonService.getLessonsByGroup(group);

        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{teacher}")
    public ResponseEntity<List<Lesson>> getByTeacher(@PathVariable(name = "teacher") Teacher teacher) {
        final List<Lesson> lessons = lessonService.getLessonsByTeacher(teacher);

        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lessons/{course}")
    public ResponseEntity<List<Lesson>> getByCourse(@PathVariable(name = "course") Course course) {
        final List<Lesson> lessons = lessonService.getLessonsByCourse(course);

        return lessons != null && !lessons.isEmpty()
                ? new ResponseEntity<>(lessons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
