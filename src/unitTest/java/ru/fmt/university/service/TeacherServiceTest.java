package ru.fmt.university.service;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeacherServiceTest extends ServiceTest{

    @Test
    public void create() {
        teacherService.create(teacher);

        verify(teacherRepository).create(teacher);
    }

    @Test
    public void getAll() {
        when(teacherRepository.getAll()).thenReturn(teachers);

        List<Teacher> actual = teacherService.getAll();

        verify(teacherRepository).getAll();

        assertEquals(teachers, actual);
    }

    @Test
    public void getById() {
        when(teacherRepository.getById(1)).thenReturn(teacher);

        Teacher actual = teacherService.getById(1);

        verify(teacherRepository).getById(1);
         assertEquals(teacher, actual);
    }

    @Test
    public void update() {
        when(teacherRepository.update(teacher)).thenReturn(teacher);

        Teacher actual = teacherService.update(teacher);

        verify(teacherRepository).update(teacher);
        assertEquals(teacher, actual);
    }

    @Test
    public void delete() {
        teacherService.delete(1);

        verify(teacherRepository).delete(1);
    }

    @Test
    public void getByCourse() {
        when(teacherRepository.getByCourse(course)).thenReturn(teachers);

        List<Teacher> actual = teacherService.getByCourse(course);

        verify(teacherRepository).getByCourse(course);

        assertEquals(teachers, actual);
    }

    @Test
    public void getSchedule() {
        when(lessonServiceMock.getLessonsByTeacher(teacher)).thenReturn(lessons);

        List<Lesson> actual = teacherService.getSchedule(teacher);

        verify(lessonServiceMock).getLessonsByTeacher(teacher);
        assertEquals(lessons, actual);
    }

    @Test
    public void grtByLesson() {
        when(teacherRepository.getByLesson(lesson)).thenReturn(teacher);

        Teacher actual = teacherService.getByLesson(lesson);

        verify(teacherRepository).getByLesson(lesson);

        assertEquals(teacher, actual);
    }
}
