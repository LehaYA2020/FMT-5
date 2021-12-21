package ru.fmt.university.service;

import org.junit.jupiter.api.Test;
import ru.fmt.university.dto.Group;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GroupServiceTest extends ServiceTest{

    @Test
    public void create_shouldCallGroupRepositoryCreateMethod() {
        groupService.create(expectedGroup);
        verify(groupRepository).create(expectedGroup);
    }

    @Test
    public void getAll_shouldCallGroupRepositoryGetAllMethod() {
        when(groupRepository.getAll()).thenReturn(expectedGroups);

        List<Group> actualGroup = groupService.getAll();

        verify(groupRepository).getAll();
        assertEquals(expectedGroups, actualGroup);
    }

    @Test
    public void getById_shouldCallGroupRepositoryGetByIdMethod() {
        when(groupRepository.getById(1)).thenReturn(expectedGroup);

        Group actualGroup = groupService.getById(1);

        verify(groupRepository).getById(1);
        assertEquals(expectedGroup, actualGroup);
    }

    @Test
    public void update_shouldCallGroupRepositoryUpdateMethod() {
        when(groupRepository.update(expectedGroup)).thenReturn(expectedGroup);

        Group updatedGroup = groupService.update(expectedGroup);

        verify(groupRepository).update(expectedGroup);
        assertEquals(expectedGroup, updatedGroup);
    }

    @Test
    public void delete_shouldCallGroupRepositoryDeleteMethod() {
        groupService.delete(1);

        verify(groupRepository).delete(1);
    }

    @Test
    public void getByCourse_shouldCallGroupRepositoryGetByCourseMethod() {
        when(groupRepository.getByCourse(expectedCourse)).thenReturn(expectedGroups);

        List<Group> actualGroups = groupService.getByCourse(expectedCourse);

        verify(groupRepository).getByCourse(expectedCourse);
        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void getByStudent_shouldCallGroupRepositoryGetByStudentMethod() {
        when(groupRepository.getByStudent(expectedStudent)).thenReturn(expectedGroup);

        Group actualGroup = groupService.getByStudent(expectedStudent);

        verify(groupRepository).getByStudent(expectedStudent);
        assertEquals(expectedGroup, actualGroup);
    }

    @Test
    public void getByLesson_shouldCallGroupRepositoryGetByLessonMethod() {
        when(groupRepository.getByLesson(expectedLesson)).thenReturn(expectedGroups);

        List<Group> actualGroups = groupService.getByLesson(expectedLesson);

        verify(groupRepository).getByLesson(expectedLesson);
        assertEquals(expectedGroups, actualGroups);
    }

    @Test
    public void assignToCourse_shouldCallGroupRepositoryAssignToCourseMethod() {
        groupService.assignToCourses(expectedGroup, expectedCourses);

        verify(groupRepository).assignToCourse(expectedGroup, expectedCourse);
    }

    @Test
    public void deleteFromCourse_shouldCallGroupRepositoryDeleteFromCourseMethod() {
        groupService.deleteFromCourse(expectedGroup, expectedCourse);

        verify(groupRepository).deleteFromCourse(expectedGroup, expectedCourse);
    }

    @Test
    public void assignToLesson_shouldCallGroupRepositoryAssignToLessonMethod() {
        groupService.assignToLesson(expectedLesson, expectedGroups);

        verify(groupRepository).assignToLesson(expectedLesson, expectedGroups);
    }

    @Test
    public void deleteFromLesson_shouldCallGroupRepositoryDeleteFromLessonMethod() {
        groupService.deleteFromLesson(expectedLesson, expectedGroup);

        verify(groupRepository).deleteFromLesson(expectedLesson, expectedGroup);
    }
}
