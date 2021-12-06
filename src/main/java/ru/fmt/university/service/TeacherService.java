package ru.fmt.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.TeacherRepository;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;

import java.util.List;

@Component
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LessonService lessonService;

    public void create(Teacher teacher) {
        teacherRepository.create(teacher);
    }

    public Teacher getById(int id) {
        return teacherRepository.getById(id);
    }

    public List<Teacher> getAll() {
        return teacherRepository.getAll();
    }

    public Teacher update(Teacher forUpdate) {
        return teacherRepository.update(forUpdate);
    }

    public void delete(int id) {
        teacherRepository.delete(id);
    }

    public Teacher getByLesson(Lesson lesson) {
        return teacherRepository.getByLesson(lesson);
    }

    public List<Teacher> getByCourse(Course course) {
        return teacherRepository.getByCourse(course);
    }

    public List<Lesson> getSchedule(Teacher teacher) {
        return lessonService.getLessonsByTeacher(teacher);
    }
}
