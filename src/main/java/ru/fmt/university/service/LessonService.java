package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.LessonRepository;
import ru.fmt.university.dto.*;

import java.util.List;

@Component
@Log4j2
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson create(Lesson lesson) {
        log.debug("LessonService calls lessonRepository.create({}).", lesson.getId());
        return lessonRepository.create(lesson);
    }

    public Lesson getById(int id) {
        log.debug("LessonService calls lessonRepository.getById({}).", id);
        return lessonRepository.getById(id);
    }

    public List<Lesson> getAll() {
        log.debug("LessonService calls lessonRepository.getAll().");
        return lessonRepository.getAll();
    }

    public Lesson update(Lesson forUpdate) {
        log.debug("LessonService calls lessonRepository.update({}).", forUpdate.getId());
        return lessonRepository.update(forUpdate);
    }

    public boolean delete(int id) {
        log.debug("LessonService calls lessonRepository.delete({}).", id);
        return lessonRepository.delete(id);
    }

    public List<Lesson> getLessonsByStudent(Student student) {
        log.debug("LessonService calls lessonRepository.getByStudent({}).", student);
        return lessonRepository.getByStudent(student);
    }

    public List<Lesson> getLessonsByCourse(Course course) {
        log.debug("LessonService calls lessonRepository.getByCourse({}).", course);
        return lessonRepository.getByCourse(course);
    }

    public List<Lesson> getLessonsByGroup(Group group) {
        log.debug("LessonService calls lessonRepository.getByGroup({}).", group);
        return lessonRepository.getByGroup(group);
    }

    public List<Lesson> getLessonsByTeacher(Teacher teacher) {
        log.debug("LessonService calls lessonRepository.getByTeacher({}).", teacher);
        return lessonRepository.getByTeacher(teacher);
    }
}
