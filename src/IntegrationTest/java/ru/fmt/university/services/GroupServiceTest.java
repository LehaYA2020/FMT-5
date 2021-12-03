package ru.fmt.university.services;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Group;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GroupServiceTest extends ServiceTest{

    @Test
    public void create() {
        groupService.create(group);
        verify(groupRepository).create(group);
    }

    @Test
    public void getAll() {
        when(studentServiceMock.getByGroup(1)).thenReturn(students);
        when(courseServiceMock.getByGroupId(1)).thenReturn(courses);
        when(groupRepository.getAll()).thenReturn(groups);

        List<Group> actual = groupService.getAll();


        verify(groupRepository).getAll();
        verify(courseServiceMock).getByGroupId(1);
        verify(courseServiceMock).getByGroupId(1);
        assertEquals(groups, actual);
        assertEquals(courses, actual.get(0).getCourses());
        assertEquals(students, actual.get(0).getStudents());
    }

    @Test
    public void getById() {
        when(groupRepository.getById(1)).thenReturn(group);
        when(courseServiceMock.getByGroupId(1)).thenReturn(courses);
        when(studentServiceMock.getByGroup(1)).thenReturn(students);

        Group actual = groupService.getById(1);

        verify(groupRepository).getById(1);
        verify(studentServiceMock).getByGroup(1);
        verify(courseServiceMock).getByGroupId(1);
        assertEquals(group, actual);
        assertEquals(courses, actual.getCourses());
        assertEquals(students, actual.getStudents());
    }

    @Test
    public void update() {
        when(groupRepository.update(group)).thenReturn(group);
        Group group1 = groupService.update(group);
        verify(groupRepository).update(group);
        assertEquals(group, group1);
    }

    @Test
    public void delete() {
        groupService.delete(1);
        verify(groupRepository).delete(1);
    }

    @Test
    public void getByCourse() {
        when(groupRepository.getByCourse(course)).thenReturn(groups);
        when(courseServiceMock.getByGroupId(1)).thenReturn(courses);
        when(studentServiceMock.getByGroup(1)).thenReturn(students);

        List<Group> actual = groupService.getByCourse(course);


        verify(groupRepository).getByCourse(course);
        verify(courseServiceMock).getByGroupId(1);
        verify(studentServiceMock).getByGroup(1);
        assertEquals(groups, actual);
        assertEquals(courses, actual.get(0).getCourses());
        assertEquals(students, actual.get(0).getStudents());
    }

    @Test
    public void getByStudent() {
        when(groupRepository.getByStudent(student)).thenReturn(group);
        when(courseServiceMock.getByGroupId(1)).thenReturn(courses);
        when(studentServiceMock.getByGroup(1)).thenReturn(students);

        Group actual = groupService.getByStudent(student);


        verify(groupRepository).getByStudent(student);
        verify(courseServiceMock).getByGroupId(1);
        verify(studentServiceMock).getByGroup(1);
        assertEquals(group, actual);
        assertEquals(courses, actual.getCourses());
        assertEquals(students, actual.getStudents());
    }

    @Test
    public void getByLesson() {
        when(groupRepository.getByLesson(lesson)).thenReturn(groups);
        when(courseServiceMock.getByGroupId(1)).thenReturn(courses);
        when(studentServiceMock.getByGroup(1)).thenReturn(students);

        List<Group> actual = groupService.getByLesson(lesson);


        verify(groupRepository).getByLesson(lesson);
        verify(courseServiceMock).getByGroupId(1);
        verify(studentServiceMock).getByGroup(1);
        assertEquals(groups, actual);
        assertEquals(courses, actual.get(0).getCourses());
        assertEquals(students, actual.get(0).getStudents());
    }

    @Test
    public void assignToCourse() {
        groupService.assignToCourses(group, courses);
        verify(groupRepository).assignToCourse(group, course);
    }

    @Test
    public void deleteFromCourse() {
        groupService.deleteFromCourse(group, course);
        verify(groupRepository).deleteFromCourse(group, course);
    }

    @Test
    public void assignToLesson() {
        groupService.assignToLesson(lesson, groups);
        verify(groupRepository).assignToLesson(lesson, groups);
    }

    @Test
    public void deleteFromLesson() {
        groupService.deleteFromLesson(lesson, group);
        verify(groupRepository).deleteFromLesson(lesson, group);
    }
}
