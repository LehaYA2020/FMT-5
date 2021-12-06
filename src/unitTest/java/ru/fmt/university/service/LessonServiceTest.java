package ru.fmt.university.service;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Lesson;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LessonServiceTest extends ServiceTest {

    @Test
    public void create() {
        lessonService.create(lesson);
        verify(lessonRepository).create(lesson);
    }

    @Test
    public void getAll() {
        when(lessonRepository.getAll()).thenReturn(lessons);

        List<Lesson> actual = lessonService.getAll();

        verify(lessonRepository).getAll();

        assertEquals(lessons, actual);
    }

    @Test
    public void getById() {
        when(lessonRepository.getById(1)).thenReturn(lesson);

        Lesson actual = lessonService.getById(1);

        verify(lessonRepository).getById(1);

        assertEquals(lesson, actual);
    }

    @Test
    public void update() {
        when(lessonRepository.update(lesson)).thenReturn(lesson);

        Lesson actual = lessonService.update(lesson);

        verify(lessonRepository).update(lesson);

        assertEquals(lesson, actual);
    }

    @Test
    public void delete() {
        lessonService.delete(1);
        verify(lessonRepository).delete(1);
    }

    @Test
    public void getByStudent() {
        when(lessonRepository.getByStudent(student)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByStudent(student);

        verify(lessonRepository).getByStudent(student);

        assertEquals(lessons, actual);
    }

    @Test
    public void getByCourse() {
        when(lessonRepository.getByCourse(course)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByCourse(course);

        verify(lessonRepository).getByCourse(course);

        assertEquals(lessons, actual);
    }

    @Test
    public void getByGroup() {
        when(lessonRepository.getByGroup(group)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByGroup(group);

        verify(lessonRepository).getByGroup(group);

        assertEquals(lessons, actual);
    }

    @Test
    public void getByTeacher() {
        when(lessonRepository.getByTeacher(teacher)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByTeacher(teacher);

        verify(lessonRepository).getByTeacher(teacher);

        assertEquals(lessons, actual);
    }
}
