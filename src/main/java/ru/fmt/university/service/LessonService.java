package ru.fmt.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.LessonRepository;
import ru.fmt.university.dto.*;

import java.util.List;

@Lazy
@Component
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public Lesson create(Lesson lesson) {
        return lessonRepository.create(lesson);
    }

    public Lesson getById(int id) {
        return lessonRepository.getById(id);
    }

    public List<Lesson> getAll() {
        return lessonRepository.getAll();
    }

    public Lesson update(Lesson forUpdate) {
        return lessonRepository.update(forUpdate);
    }

    public void delete(int id) {
        lessonRepository.delete(id);
    }

    public List<Lesson> getLessonsByStudent(Student student) {
        return lessonRepository.getByStudent(student);
    }

    public List<Lesson> getLessonsByCourse(Course course) {
        return lessonRepository.getByCourse(course);
    }

    public List<Lesson> getLessonsByGroup(Group group) {
        return lessonRepository.getByGroup(group);
    }

    public List<Lesson> getLessonsByTeacher(Teacher teacher) {
        return lessonRepository.getByTeacher(teacher);
    }
}
