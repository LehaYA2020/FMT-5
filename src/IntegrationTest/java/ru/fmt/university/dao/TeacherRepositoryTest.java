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
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Teacher;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class TeacherRepositoryTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    private ScriptRunner scriptRunner;
    private static final List<Teacher> testTeacherList = new LinkedList<>();
    private static final List<Course> testCourseList = new LinkedList<>();

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 3; i++) {
            testCourseList.add(new Course(i));
        }
        testTeacherList.add(new Teacher(1, "T-" + 1, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(2, "T-" + 2, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(3, "T-" + 3, "Teacher", testCourseList.get(1)));
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
    public void getAll() {
        assertEquals(testTeacherList, teacherRepository.getAll());
    }

    @Test
    public void getById() {
        assertEquals(testTeacherList.get(0).getCourse(), teacherRepository.getById(1).getCourse());
    }

    @Test
    public void update() {
        Teacher teacher = new Teacher(2, "T-" + 2, "updated", testCourseList.get(1));
        teacherRepository.update(teacher);
        assertEquals(teacher, teacherRepository.getById(2));
    }

    @Test
    public void getByCourse() {
        assertEquals(testTeacherList.subList(0,2), teacherRepository.getByCourse(new Course(1)));
    }

    @Test
    public void getByLesson() {
        assertEquals(testTeacherList.get(0), teacherRepository.getByLesson(new Lesson(1)));
    }

    @AfterEach
    public void clearDatabase() throws Exception {
        Reader reader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("clearDatabase.sql").getFile()));
        scriptRunner.runScript(reader);
    }
}
