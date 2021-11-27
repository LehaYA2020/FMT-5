package ru.fmt.university.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Student;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StudentRepositoryTest extends RepositoryTest {
    private static final Student FOR_CREATION = new Student(5, "S-05", "Student");
    private static final List<Student> testStudentList = new LinkedList<>();
    private static final List<Group> testGroupList = new LinkedList<>();

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 4; i++) {
            testStudentList.add(new Student(i, "S-0" + i, "Student"));
            testGroupList.add(new Group(i, "Group-" + i));
        }
    }

    @Test
    public void create() {
        studentRepository.create(FOR_CREATION);
        assertNotEquals(testStudentList, studentRepository.getAll());

        assertEquals(FOR_CREATION,studentRepository.getById(FOR_CREATION.getId()));
    }

    @Test
    public void create_shouldThrow_DaoException() {
        assertEquals(4, studentRepository.getAll().size());
        Throwable exception = assertThrows(DaoException.class,
                () -> studentRepository.create(new Student(0, "", "234")));

        assertEquals(MessagesConstants.CANNOT_INSERT_STUDENT, exception.getMessage());
    }

    @Test
    public void getAll_shouldReturnAllStudentsFromDb() {
        assertEquals(testStudentList, studentRepository.getAll());
    }

    @Test
    public void getByID_shouldReturnStudentByIdFromDb() {
        assertEquals(testStudentList.get(0), studentRepository.getById(1));
    }

    @Test
    public void getById_shouldThrowDaoException() {


        Throwable exception = assertThrows(DaoException.class,
                () -> studentRepository.getById(10));

        assertEquals(MessagesConstants.CANNOT_GET_STUDENT_BY_ID, exception.getMessage());
    }

    @Test
    public void delete_shouldDeleteFromDb() {
        studentRepository.delete(1);
        assertEquals(testStudentList.subList(1, 4), studentRepository.getAll());
    }

    @Test
    public void update_shouldUpdateStudent() {
        Student forUpdate = new Student(1, "S-01", "UPDATED");
        studentRepository.update(forUpdate);
        assertEquals(forUpdate, studentRepository.getById(1));
    }

    @Test
    public void assignToGroup_shouldAssignToGroup() {
        studentRepository.assignToGroup(testStudentList.get(3), testGroupList.get(2));
        assertEquals(testStudentList.subList(3, 4), studentRepository.getByGroupId(3));
    }

    @Test
    public void updateGroupAssignment_shouldUpdateGroupAssignment() {
        studentRepository.updateGroupAssignment(testStudentList.get(1), testGroupList.get(1));
        assertEquals(testStudentList.subList(1, 3), studentRepository.getByGroupId(2));
    }

    @Test
    public void getByGroup_shouldReturnStudentsByGroup() {
        assertEquals(testStudentList.subList(0, 2), studentRepository.getByGroupId(1));
    }

    @Test
    public void deleteFromGroup_shouldDeleteFromGroup() {
        studentRepository.deleteFromGroup(testStudentList.get(0), testGroupList.get(0));
        assertEquals(testStudentList.subList(1, 2), studentRepository.getByGroupId(1));
    }
}
