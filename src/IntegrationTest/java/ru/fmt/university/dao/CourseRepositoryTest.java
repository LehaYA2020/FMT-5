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
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Group;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class CourseRepositoryTest {
    private static final List<Course> testCourseList = new LinkedList<>();
    private static final List<Group> testGroupList = new LinkedList<>();
    @Autowired
    DataSource dataSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GroupRepository groupRepository;
    private ScriptRunner scriptRunner;

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 3; i++) {
            testCourseList.add(new Course(i, "Course-" + i, "forTest"));
            testGroupList.add(new Group(i, "Group-" + i));
        }
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
    public void getAll_shouldReturnAllCourses() {
        assertEquals(testCourseList, courseRepository.getAll());
    }

    @Test
    public void getById_shouldReturnCourseById() {
        Course expected = testCourseList.get(0);

        assertEquals(expected, courseRepository.getById(1));
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

    @AfterEach
    public void clearDatabase() throws Exception {
        Reader reader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("clearDatabase.sql").getFile()));
        scriptRunner.runScript(reader);
    }
}
