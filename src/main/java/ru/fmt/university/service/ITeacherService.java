package ru.fmt.university.service;

import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;

import java.util.List;

public interface ITeacherService extends IService<Teacher, Integer> {
    Teacher getByLesson(Integer lessonId);
    List<Teacher> getByCourse(Integer courseId);
    List<Lesson> getSchedule(Integer teacherId);
}
