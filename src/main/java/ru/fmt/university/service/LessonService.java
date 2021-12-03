package ru.fmt.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.LessonRepository;
import ru.fmt.university.dto.*;

import java.util.List;

@Component
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    public Lesson create(Lesson lesson) {
        return lessonRepository.create(lesson);
    }

    public Lesson getById(int id) {
        Lesson lesson = lessonRepository.getById(id);
        setGroups(lesson);
        setCourse(lesson);
        setTeacher(lesson);
        return lesson;
    }

    public List<Lesson> getAll() {
        List<Lesson> lessons = lessonRepository.getAll();
        setGroups(lessons);
        setCourse(lessons);
        setTeacher(lessons);
        return lessons;
    }

    public Lesson update(Lesson forUpdate) {
        Lesson lesson = lessonRepository.update(forUpdate);

        setGroups(lesson);
        setCourse(lesson);
        setTeacher(lesson);

        return lesson;
    }

    public void delete(int id) {
        lessonRepository.delete(id);
    }

    public List<Lesson> getLessonsByStudent(Student student) {
        List<Lesson> lessons = lessonRepository.getByStudent(student);

        setGroups(lessons);
        setCourse(lessons);
        setTeacher(lessons);
        return lessons;
    }

    public List<Lesson> getLessonsByCourse(Course course) {
        List<Lesson> lessons = lessonRepository.getByCourse(course);

        setGroups(lessons);
        setCourse(lessons);
        setTeacher(lessons);
        return lessons;
    }

    public List<Lesson> getLessonsByGroup(Group group) {
        List<Lesson> lessons = lessonRepository.getByGroup(group);

        setGroups(lessons);
        setCourse(lessons);
        setTeacher(lessons);
        return lessons;
    }

    public List<Lesson> getLessonsByTeacher(Teacher teacher) {
        List<Lesson> lessons = lessonRepository.getByTeacher(teacher);

        setGroups(lessons);
        setCourse(lessons);
        setTeacher(lessons);
        return lessons;
    }

    private void setGroups(Lesson lesson) {
        lesson.setGroups(groupService.getByLesson(lesson));
    }

    private void setGroups(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            lesson.setGroups(groupService.getByLesson(lesson));
        }
    }

    private void setCourse(Lesson lesson) {
        lesson.setCourse(courseService.getById(lesson.getCourse().getId()));
    }

    private void setCourse(Lesson lesson, Course course) {
        lesson.setCourse(course);
    }

    private void setCourse(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            setCourse(lesson);
        }
    }

    private void setCourse(List<Lesson> lessons, Course course) {
        for (Lesson lesson : lessons) {
            setCourse(lesson, course);
        }
    }

    private void setTeacher(Lesson lesson) {
        lesson.setTeacher(teacherService.getById(lesson.getTeacher().getId()));
    }

    private void setTeacher(Lesson lesson, Teacher teacher) {
        lesson.setTeacher(teacher);
    }

    private void setTeacher(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            setTeacher(lesson);
        }
    }
}
