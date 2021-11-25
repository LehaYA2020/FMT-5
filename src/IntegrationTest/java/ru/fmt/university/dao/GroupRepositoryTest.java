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
import ru.fmt.university.dto.Student;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class GroupRepositoryTest {
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
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LessonRepository lessonRepository;
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
    public void getAll_shouldReturnAllGroups() {
        assertEquals(testGroupList, groupRepository.getAll());
    }

    @Test
    public void getById() {
        assertEquals(testGroupList.get(0), groupRepository.getById(1));
    }

    @Test
    public void getByStudent() {
        assertEquals(testGroupList.get(0), groupRepository.getByStudent(new Student(1)));
    }

    @Test
    public void delete() {
        groupRepository.delete(3);
        assertEquals(testGroupList.subList(0, 2), groupRepository.getAll());
    }

    @Test
    public void update() {
        Group expected = new Group(1, "updated");
        groupRepository.update(expected);

        assertEquals(expected, groupRepository.getById(1));
    }

    @Test
    public void deleteFromCourse() {
        groupRepository.deleteFromCourse(testGroupList.get(0), testCourseList.get(0));
        assertEquals(testCourseList.subList(1, 2), courseRepository.getByGroupId(1));
    }

    @Test
    public void getByLesson() {
        assertEquals(testGroupList.subList(0, 2), groupRepository.getByLesson(lessonRepository.getById(2)));
    }

    @Test
    public void getByCourse() {
        assertEquals(testGroupList.subList(0, 2), groupRepository.getByCourse(courseRepository.getById(2)));
    }

    @Test
    public void deleteFromLesson() {
        groupRepository.deleteFromLesson(lessonRepository.getById(2), testGroupList.get(0));
        assertEquals(testGroupList.subList(1, 2), groupRepository.getByLesson(lessonRepository.getById(2)));
    }

    @AfterEach
    public void clearDatabase() throws Exception {
        Reader reader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("clearDatabase.sql").getFile()));
        scriptRunner.runScript(reader);
    }
}
