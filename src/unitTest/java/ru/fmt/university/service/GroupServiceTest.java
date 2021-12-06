package ru.fmt.university.service;

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
        when(groupRepository.getAll()).thenReturn(groups);

        List<Group> actual = groupService.getAll();


        verify(groupRepository).getAll();
        assertEquals(groups, actual);
    }

    @Test
    public void getById() {
        when(groupRepository.getById(1)).thenReturn(group);

        Group actual = groupService.getById(1);

        verify(groupRepository).getById(1);
        assertEquals(group, actual);
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

        List<Group> actual = groupService.getByCourse(course);


        verify(groupRepository).getByCourse(course);
        assertEquals(groups, actual);
    }

    @Test
    public void getByStudent() {
        when(groupRepository.getByStudent(student)).thenReturn(group);

        Group actual = groupService.getByStudent(student);


        verify(groupRepository).getByStudent(student);
        assertEquals(group, actual);
    }

    @Test
    public void getByLesson() {
        when(groupRepository.getByLesson(lesson)).thenReturn(groups);

        List<Group> actual = groupService.getByLesson(lesson);


        verify(groupRepository).getByLesson(lesson);
        assertEquals(groups, actual);
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
