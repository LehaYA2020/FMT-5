package ru.fmt.university.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.fmt.university.dto.*;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class LessonRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private LessonRepository lessonRepository;
    private ScriptRunner scriptRunner;
    private static List<Lesson> testLessonList = new LinkedList<>();
    private static List<Course> testCourseList = new LinkedList<>();
    private static List<Group> testGroupList = new LinkedList<>();
    private static List<Teacher> testTeacherList = new LinkedList<>();
    private Lesson forUpdate = new Lesson(2,  new Course(2),new Teacher(1), 10,
            DayOfWeek.THURSDAY,  LocalTime.of(9,30, 0), LessonType.LECTURE);

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 3; i++) {
            testCourseList.add(new Course(i, "Course-" + i, "forTest"));
            testGroupList.add(new Group(i, "Group-" + i));
        }

        testTeacherList.add(new Teacher(1, "T-" + 1, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(2, "T-" + 2, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(3, "T-" + 3, "Teacher", testCourseList.get(1)));

        testLessonList.add(new Lesson(1, new Course(1),new Teacher(1), 10,
            DayOfWeek.MONDAY,  LocalTime.of(9,30, 0), LessonType.LECTURE));
        testLessonList.add(new Lesson(2,  new Course(2),new Teacher(1), 10,
            DayOfWeek.TUESDAY,  LocalTime.of(9,30, 0), LessonType.LECTURE));
        testLessonList.add(new Lesson(3, new Course(2),new Teacher(2), 20,
            DayOfWeek.FRIDAY,  LocalTime.of(9,30, 0), LessonType.LECTURE));
    }
    @BeforeEach
    public void fillDb() throws Exception {
        scriptRunner = new ScriptRunner(dataSource.getConnection());
        Reader createDatabaseReader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("createTables.sql").getFile()));
        scriptRunner.runScript(createDatabaseReader);

        Reader fillDatabaseReader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("fillDb.sql").getFile()));
        scriptRunner.runScript(fillDatabaseReader);
    }

    @Test
    public void getAll_shouldReturnAllLessonsFromDb() {
        assertEquals(testLessonList, lessonRepository.getAll());
    }

    @Test
    public void getById(){
        assertEquals(testLessonList.get(0), lessonRepository.getById(1));
    }

    @Test
    public void update_shouldUpdateLessonInDb() {
        lessonRepository.update(forUpdate);
        assertEquals(forUpdate, lessonRepository.getById(2));
    }

    @Test
    public void delete_shouldDeleteLessonFromDb() {
        lessonRepository.delete(3);
        assertEquals(testLessonList.subList(0,2), lessonRepository.getAll());
    }

    @Test
    public void getByTeacher() {
        assertEquals(testLessonList.subList(0,2),lessonRepository.getByTeacher(testTeacherList.get(0)));
    }

    @Test
    public void getByCourse() {
        assertEquals(testLessonList.subList(1,3),lessonRepository.getByCourse(testCourseList.get(1)));
    }

    @Test
    public void getByStudent() {
        assertEquals(testLessonList.subList(0,2),lessonRepository.getByStudent(new Student(1)));
    }

    @Test
    public void getByGroup() {
        assertEquals(testLessonList.subList(0,2),lessonRepository.getByGroup(testGroupList.get(0)));
    }

    @AfterEach
    public void clearDatabase() throws Exception {
        Reader reader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("clearDatabase.sql").getFile()));
        scriptRunner.runScript(reader);
    }
}
