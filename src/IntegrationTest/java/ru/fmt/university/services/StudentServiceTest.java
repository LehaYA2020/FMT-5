package ru.fmt.university.services;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest extends ServiceTest{

    @Test
    public void create() {
        studentService.create(student);
        verify(studentRepository).create(student);
    }

    @Test
    public void getAll() {
        when(studentRepository.getAll()).thenReturn(students);
        when(groupServiceMock.getByStudent(student)).thenReturn(group);

        List<Student> actual = studentService.getAll();

        verify(studentRepository).getAll();
        verify(groupServiceMock).getByStudent(student);
        assertEquals(students, actual);
    }

    @Test
    public void getById() {
        when(studentRepository.getById(1)).thenReturn(student);
        when(groupServiceMock.getByStudent(student)).thenReturn(group);

        Student actual = studentService.getById(1);

        verify(studentRepository).getById(1);
        verify(groupServiceMock).getByStudent(student);
        assertEquals(student, actual);
    }

    @Test
    public void update() {
        when(studentRepository.update(student)).thenReturn(student);
        when(groupServiceMock.getByStudent(student)).thenReturn(group);

        Student actual = studentService.update(student);

        verify(studentRepository).update(student);
        verify(groupServiceMock).getByStudent(student);
        assertEquals(student, actual);
    }

    @Test
    public void delete() {
        studentService.delete(1);
        verify(studentRepository).delete(1);
    }

    @Test
    public void assignToGroup() {
        studentService.assignStudentToGroup(student, group);
        verify(studentRepository).assignToGroup(student, group);
    }

    @Test
    public void deleteFromGroup() {
        studentService.updateGroupAssignment(student, group);
        verify(studentRepository).updateGroupAssignment(student, group);
    }

    @Test
    public void getSchedule() {
        when(lessonServiceMock.getLessonsByStudent(student)).thenReturn(lessons);

        List<Lesson> actual = studentService.getSchedule(student);

        verify(lessonServiceMock).getLessonsByStudent(student);
        assertEquals(lessons, actual);
    }

    @Test
    public void getByGroup() {
        when(studentRepository.getByGroupId(1)).thenReturn(students);
        when(groupServiceMock.getByStudent(student)).thenReturn(group);

        List<Student> actual = studentService.getByGroup(1);

        verify(studentRepository).getByGroupId(1);
        verify(groupServiceMock).getByStudent(student);
        assertEquals(students, actual);
    }
}
