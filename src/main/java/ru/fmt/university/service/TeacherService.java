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
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;

    public void create(Teacher teacher) {
        teacherRepository.create(teacher);
    }

    public Teacher getById(int id) {
        Teacher teacher = teacherRepository.getById(id);
        setCourse(teacher);
        return teacher;
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers = teacherRepository.getAll();
        setCourse(teachers);
        return teachers;
    }

    public Teacher update(Teacher forUpdate) {
        Teacher teacher = teacherRepository.update(forUpdate);
        setCourse(teacher);
        return teacher;
    }

    public void delete(int id) {
        teacherRepository.delete(id);
    }

    public Teacher getByLesson(Lesson lesson) {
        Teacher teacher = teacherRepository.getByLesson(lesson);
        setCourse(teacher);
        return teacher;
    }

    public List<Teacher> getByCourse(Course course) {
        List<Teacher> teachers = teacherRepository.getByCourse(course);
        setCourse(teachers);
        return teachers;
    }

    public List<Lesson> getSchedule(Teacher teacher) {
        return lessonService.getLessonsByTeacher(teacher);
    }

    private void setCourse(Teacher teacher) {
        teacher.setCourse(courseService.getById(teacher.getCourse().getId()));
    }

    private void setCourse(List<Teacher> teachers) {
        for (Teacher teacher:teachers) {
            setCourse(teacher);
        }
    }
}
