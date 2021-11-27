package ru.fmt.university.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fmt.university.dao.exceptions.DaoException;
import ru.fmt.university.dao.exceptions.MessagesConstants;
import ru.fmt.university.dto.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LessonRepositoryTest extends RepositoryTest {
    private static final Lesson FOR_UPDATE = new Lesson(2, new Course(2), new Teacher(1), 10,
            DayOfWeek.THURSDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE);

    private static final Lesson lesson = new Lesson(4, new Course(2), new Teacher(1), 10,
            DayOfWeek.THURSDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE);

    private static final List<Lesson> testLessonList = new LinkedList<>();
    private static final List<Course> testCourseList = new LinkedList<>();
    private static final List<Group> testGroupList = new LinkedList<>();
    private static final List<Teacher> testTeacherList = new LinkedList<>();

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 3; i++) {
            testCourseList.add(new Course(i, "Course-" + i, "forTest"));
            testGroupList.add(new Group(i, "Group-" + i));
        }

        testTeacherList.add(new Teacher(1, "T-" + 1, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(2, "T-" + 2, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(3, "T-" + 3, "Teacher", testCourseList.get(1)));

        testLessonList.add(new Lesson(1, new Course(1), new Teacher(1), 10,
                DayOfWeek.MONDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE));
        testLessonList.add(new Lesson(2, new Course(2), new Teacher(1), 10,
                DayOfWeek.TUESDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE));
        testLessonList.add(new Lesson(3, new Course(2), new Teacher(2), 20,
                DayOfWeek.FRIDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE));
    }

    @Test
    public void create() {
        lessonRepository.create(lesson);
        assertNotEquals(testLessonList, lessonRepository.getAll());

        assertEquals(lesson,lessonRepository.getById(lesson.getId()));
    }

    @Test
    public void getAll_shouldReturnAllLessonsFromDb() {
        assertEquals(testLessonList, lessonRepository.getAll());
    }

    @Test
    public void getById() {
        assertEquals(testLessonList.get(0), lessonRepository.getById(1));
    }

    @Test
    public void getById_shouldThrowDaoException() {
        Throwable exception = assertThrows(DaoException.class,
                () -> lessonRepository.getById(10));

        assertEquals(MessagesConstants.CANNOT_GET_LESSON_BY_ID, exception.getMessage());
    }

    @Test
    public void update_shouldUpdateLessonInDb() {
        lessonRepository.update(FOR_UPDATE);
        assertEquals(FOR_UPDATE, lessonRepository.getById(2));
    }

    @Test
    public void delete_shouldDeleteLessonFromDb() {
        lessonRepository.delete(3);
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getAll());
    }

    @Test
    public void getByTeacher() {
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getByTeacher(testTeacherList.get(0)));
    }

    @Test
    public void getByCourse() {
        assertEquals(testLessonList.subList(1, 3), lessonRepository.getByCourse(testCourseList.get(1)));
    }

    @Test
    public void getByStudent() {
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getByStudent(new Student(1)));
    }

    @Test
    public void getByGroup() {
        assertEquals(testLessonList.subList(0, 2), lessonRepository.getByGroup(testGroupList.get(0)));
    }
}
