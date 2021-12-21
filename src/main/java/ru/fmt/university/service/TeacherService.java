package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.TeacherRepository;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;

import java.util.List;

@Component
@Log4j2
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LessonService lessonService;

    public void create(Teacher teacher) {
        log.debug("TeacherService calls teacherRepository.create({}).", teacher.getId());
        teacherRepository.create(teacher);
    }

    public Teacher getById(int id) {
        log.debug("TeacherService calls teacherRepository.getById({}).", id);
        return teacherRepository.getById(id);
    }

    public List<Teacher> getAll() {
        log.debug("TeacherService calls teacherRepository.getAll().");
        return teacherRepository.getAll();
    }

    public Teacher update(Teacher forUpdate) {
        log.debug("TeacherService calls teacherRepository.update({}).", forUpdate.getId());
        return teacherRepository.update(forUpdate);
    }

    public void delete(int id) {
        log.debug("TeacherService calls teacherRepository.delete({}).", id);
        teacherRepository.delete(id);
    }

    public Teacher getByLesson(Lesson lesson) {
        log.debug("TeacherService calls teacherRepository.getByLesson({}).", lesson);
        return teacherRepository.getByLesson(lesson);
    }

    public List<Teacher> getByCourse(Course course) {
        log.debug("TeacherService calls teacherRepository.getByCourse({}).", course);
        return teacherRepository.getByCourse(course);
    }

    public List<Lesson> getSchedule(Teacher teacher) {
        log.debug("StudentService calls lessonService.getLessonsByStudent({}).", teacher.getId());
        return lessonService.getLessonsByTeacher(teacher);
    }
}
