package ru.fmt.university.service;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeacherServiceTest extends ServiceTest {

    @Test
    public void create_shouldCallTeacherRepositoryCreateMethod() {
        teacherService.create(expectedTeacher);
        verify(teacherRepository).create(expectedTeacher);
    }

    @Test
    public void getAll_shouldCallTeacherRepositoryGetAllMethod() {
        when(teacherRepository.getAll()).thenReturn(expectedTeachers);

        List<Teacher> actualTeachers = teacherService.getAll();

        verify(teacherRepository).getAll();

        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    public void getById_shouldCallTeacherRepositoryGetByIdMethod() {
        when(teacherRepository.getById(1)).thenReturn(expectedTeacher);

        Teacher actualTeacher = teacherService.getById(1);

        verify(teacherRepository).getById(1);
        assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    public void update_shouldCallTeacherRepositoryUpdateMethod() {
        when(teacherRepository.update(expectedTeacher)).thenReturn(expectedTeacher);

        Teacher updatedTeacher = teacherService.update(expectedTeacher);

        verify(teacherRepository).update(expectedTeacher);
        assertEquals(expectedTeacher, updatedTeacher);
    }

    @Test
    public void delete_shouldCallTeacherRepositoryDeleteMethod() {
        teacherService.delete(1);
        verify(teacherRepository).delete(1);
    }

    @Test
    public void getByCourse_shouldCallTeacherRepositoryGetByCourseMethod() {
        when(teacherRepository.getByCourse(expectedCourse)).thenReturn(expectedTeachers);

        List<Teacher> actualTeachers = teacherService.getByCourse(expectedCourse);

        verify(teacherRepository).getByCourse(expectedCourse);
        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    public void getSchedule_shouldCallTeacherRepositoryGetScheduleMethod() {
        when(lessonServiceMock.getLessonsByTeacher(expectedTeacher)).thenReturn(expectedLessons);

        List<Lesson> actualTeachers = teacherService.getSchedule(expectedTeacher);

        verify(lessonServiceMock).getLessonsByTeacher(expectedTeacher);
        assertEquals(expectedLessons, actualTeachers);
    }

    @Test
    public void getByLesson_shouldCallTeacherRepositoryGetByLessonMethod() {
        when(teacherRepository.getByLesson(expectedLesson)).thenReturn(expectedTeacher);

        Teacher actualTeacher = teacherService.getByLesson(expectedLesson);

        verify(teacherRepository).getByLesson(expectedLesson);
        assertEquals(expectedTeacher, actualTeacher);
    }
}
