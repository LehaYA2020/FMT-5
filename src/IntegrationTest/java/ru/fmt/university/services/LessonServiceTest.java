package ru.fmt.university.services;

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
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.getAll()).thenReturn(lessons);

        List<Lesson> actual = lessonService.getAll();

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
        verify(lessonRepository).getAll();

        assertEquals(lessons, actual);
    }

    @Test
    public void getById() {
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.getById(1)).thenReturn(lesson);

        Lesson actual = lessonService.getById(1);

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
        verify(lessonRepository).getById(1);

        assertEquals(lesson, actual);
    }

    @Test
    public void update() {
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.update(lesson)).thenReturn(lesson);

        Lesson actual = lessonService.update(lesson);

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
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
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.getByStudent(student)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByStudent(student);

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
        verify(lessonRepository).getByStudent(student);

        assertEquals(lessons, actual);
    }

    @Test
    public void getByCourse() {
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.getByCourse(course)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByCourse(course);

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
        verify(lessonRepository).getByCourse(course);

        assertEquals(lessons, actual);
    }

    @Test
    public void getByGroup() {
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.getByGroup(group)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByGroup(group);

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
        verify(lessonRepository).getByGroup(group);

        assertEquals(lessons, actual);
    }

    @Test
    public void getByTeacher() {
        when(courseServiceMock.getById(1)).thenReturn(course);
        when(teacherServiceMock.getById(1)).thenReturn(teacher);
        when(groupServiceMock.getByLesson(lesson)).thenReturn(groups);
        when(lessonRepository.getByTeacher(teacher)).thenReturn(lessons);

        List<Lesson> actual = lessonService.getLessonsByTeacher(teacher);

        verify(courseServiceMock).getById(1);
        verify(teacherServiceMock).getById(1);
        verify(groupServiceMock).getByLesson(lesson);
        verify(lessonRepository).getByTeacher(teacher);

        assertEquals(lessons, actual);
    }
}
