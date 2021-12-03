package ru.fmt.university.services;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class CourseServiceTest extends ServiceTest {

    @Test
    public void create() {
        courseService.create(course);
        verify(courseRepository).create(course);
    }

    @Test
    public void getAll() {
        when(courseRepository.getAll()).thenReturn(courses);
        when(lessonServiceMock.getLessonsByCourse(course)).thenReturn(lessons);
        when(groupServiceMock.getByCourse(course)).thenReturn(groups);

        List<Course> actual = courseService.getAll();

        verify(courseRepository).getAll();
        verify(lessonServiceMock).getLessonsByCourse(course);
        verify(groupServiceMock).getByCourse(course);
        assertEquals(courses, actual);
        assertEquals(lessons, actual.get(0).getLessons());
        assertEquals(groups, actual.get(0).getGroups());
    }

    @Test
    public void getById() {
        when(courseRepository.getById(1)).thenReturn(course);
        when(lessonServiceMock.getLessonsByCourse(course)).thenReturn(lessons);
        when(groupServiceMock.getByCourse(course)).thenReturn(groups);

        Course actual = courseService.getById(1);

        verify(courseRepository).getById(1);
        verify(lessonServiceMock).getLessonsByCourse(course);
        verify(groupServiceMock).getByCourse(course);
        assertEquals(course, actual);
        assertEquals(lessons, actual.getLessons());
        assertEquals(groups, actual.getGroups());
    }

    @Test
    public void update() {
        when(courseRepository.update(course)).thenReturn(course);
        Course course1 = courseService.update(course);
        verify(courseRepository).update(course);
        assertEquals(course, course1);
    }

    @Test
    public void delete() {
        doNothing().when(courseRepository).delete(1);
        courseService.delete(1);
        verify(courseRepository).delete(1);
    }

    @Test
    public void getByGroupId() {
        when(courseRepository.getByGroupId(1)).thenReturn(courses);
        when(lessonServiceMock.getLessonsByCourse(course)).thenReturn(lessons);
        when(groupServiceMock.getByCourse(course)).thenReturn(groups);
        List<Course> courses1 = courseService.getByGroupId(1);
        verify(courseRepository).getByGroupId(1);
        verify(courseRepository).getByGroupId(1);
        verify(lessonServiceMock).getLessonsByCourse(course);
        verify(groupServiceMock).getByCourse(course);
        assertEquals(courses, courses1);
    }
}
