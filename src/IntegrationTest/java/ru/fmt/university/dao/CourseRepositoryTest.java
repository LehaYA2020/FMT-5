package ru.fmt.university.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Group;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CourseRepositoryTest extends RepositoryTest {
    private static List<Course> testCourseList = new LinkedList<>();
    private static List<Group> testGroupList = new LinkedList<>();

    private static final Course FOR_CREATION = new Course(4, "Course-" + 4, "forTest");

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 3; i++) {
            testCourseList.add(new Course(i, "Course-" + i, "forTest"));
            testGroupList.add(new Group(i, "Group-" + i));
        }
    }

    @Test
    public void create() {
        courseRepository.create(FOR_CREATION);
        assertNotEquals(testCourseList, courseRepository.getAll());

        assertEquals(FOR_CREATION,courseRepository.getById(FOR_CREATION.getId()));
    }

    @Test
    public void getAll_shouldReturnAllCourses() {
        assertEquals(testCourseList, courseRepository.getAll());
    }

    @Test
    public void getById_shouldReturnCourseById() {
        Course expected = testCourseList.get(0);

        assertEquals(expected, courseRepository.getById(1));
    }

    @Test
    public void getById_shouldThrowDaoException() {


        Throwable exception = assertThrows(DaoException.class,
                () -> courseRepository.getById(10));

        assertEquals(MessagesConstants.CANNOT_GET_COURSE_BY_ID, exception.getMessage());
    }

    @Test
    public void update_shouldUpdateCourse() {
        Course expected = new Course(1, "Course-" + 1, "updated");
        courseRepository.update(new Course(1, "Course-1", "updated"));
        assertEquals(expected, courseRepository.getById(1));
    }

    @Test
    public void delete_shouldDeleteCourse() {
        List<Course> expected = testCourseList.subList(0, 2);

        courseRepository.delete(3);

        assertEquals(expected, courseRepository.getAll());
    }

    @Test
    public void getByGroupId() {
        List<Course> expected = testCourseList.subList(0, 2);

        assertEquals(expected, courseRepository.getByGroupId(1));
    }
}
