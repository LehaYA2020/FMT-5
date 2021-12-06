package ru.fmt.university.service;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class CourseServiceTest extends ServiceTest {

    @Test
    public void create_shouldCallCourseRepositoryCreateMethod() {
        courseService.create(course);
        verify(courseRepository).create(course);
    }

    @Test
    public void getAll_shouldCallCourseRepositoryGetAllMethod() {
        when(courseRepository.getAll()).thenReturn(courses);

        List<Course> actualCourses = courseService.getAll();

        verify(courseRepository).getAll();
        assertEquals(courses, actualCourses);
    }

    @Test
    public void getById_shouldCallCourseRepositoryGetByIdMethod() {
        when(courseRepository.getById(1)).thenReturn(course);

        Course actualCourse = courseService.getById(1);

        verify(courseRepository).getById(1);
        assertEquals(course, actualCourse);
    }

    @Test
    public void update_shouldCallCourseRepositoryUpdateMethod() {
        when(courseRepository.update(course)).thenReturn(course);
        Course updatedCourse = courseService.update(course);
        verify(courseRepository).update(course);
        assertEquals(course, updatedCourse);
    }

    @Test
    public void delete_shouldCallCourseRepositoryDeleteMethod() {
        doNothing().when(courseRepository).delete(1);
        courseService.delete(1);
        verify(courseRepository).delete(1);
    }

    @Test
    public void getByGroupId_shouldCallCourseRepositoryGetByGroupIdMethod() {
        when(courseRepository.getByGroupId(1)).thenReturn(courses);
        List<Course> courses1 = courseService.getByGroupId(1);
        verify(courseRepository).getByGroupId(1);
        assertEquals(courses, courses1);
    }
}
