package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.LessonRepository;
import ru.fmt.university.dto.Lesson;

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

    public List<Lesson> getLessonsByStudent(int studentId) {
        log.debug("LessonService calls lessonRepository.getByStudent({}).", studentId);
        return lessonRepository.getByStudent(studentId);
    }

    public List<Lesson> getLessonsByCourse(int courseId) {
        log.debug("LessonService calls lessonRepository.getByCourse({}).", courseId);
        return lessonRepository.getByCourse(courseId);
    }

    public List<Lesson> getLessonsByGroup(int groupId) {
        log.debug("LessonService calls lessonRepository.getByGroup({}).", groupId);
        return lessonRepository.getByGroup(groupId);
    }

    public List<Lesson> getLessonsByTeacher(int teacherId) {
        log.debug("LessonService calls lessonRepository.getByTeacher({}).", teacherId);
        return lessonRepository.getByTeacher(teacherId);
    }
}
