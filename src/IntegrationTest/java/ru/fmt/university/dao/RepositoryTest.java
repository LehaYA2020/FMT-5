package ru.fmt.university.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public abstract class RepositoryTest {
    protected static final List<Course> testCourseList = new LinkedList<>();
    protected static final List<Group> testGroupList = new LinkedList<>();
    protected static final List<Student> testStudentList = new LinkedList<>();
    protected static final List<Teacher> testTeacherList = new LinkedList<>();
    protected static final List<Lesson> testLessonList = new LinkedList<>();

    @Autowired
    protected CourseRepository courseRepository;
    @Autowired
    protected GroupRepository groupRepository;
    @Autowired
    protected LessonRepository lessonRepository;
    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected TeacherRepository teacherRepository;

    @Autowired
    protected DataSource dataSource;
    protected ScriptRunner scriptRunner;
    @Autowired
    ApplicationContext context;

    @BeforeAll
    protected static void prepareLists() {

        for (int i = 1; i <= 3; i++) {
            testCourseList.add(new Course(i, "Course-" + i, "forTest"));
            testGroupList.add(new Group(i, "Group-" + i));
        }
        for (int i = 1; i <= 4; i++) {
            testStudentList.add(new Student(i, "S-0" + i, "Student"));
        }


        testTeacherList.add(new Teacher(1, "T-" + 1, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(2, "T-" + 2, "Teacher", testCourseList.get(0)));
        testTeacherList.add(new Teacher(3, "T-" + 3, "Teacher", testCourseList.get(1)));

        testLessonList.add(new Lesson(1, testCourseList.get(0), new Teacher(1), 10,
                DayOfWeek.MONDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE));
        testLessonList.add(new Lesson(2, testCourseList.get(1), new Teacher(1), 10,
                DayOfWeek.TUESDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE));
        testLessonList.add(new Lesson(3, testCourseList.get(1), new Teacher(2), 20,
                DayOfWeek.FRIDAY, LocalTime.of(9, 30, 0), LessonType.LECTURE));
    }

    @AfterAll
    public static void clearLists() {
        testCourseList.clear();
        testLessonList.clear();
        testTeacherList.clear();
        testGroupList.clear();
        testStudentList.clear();
    }

    @BeforeEach
    protected void fillDb() throws Exception {
        scriptRunner = new ScriptRunner(dataSource.getConnection());
        Reader createDatabaseReader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("createTables.sql").getFile()));
        scriptRunner.runScript(createDatabaseReader);

        Reader fillDatabaseReader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("fillDb.sql").getFile()));
        scriptRunner.runScript(fillDatabaseReader);

    }

    @AfterEach
    public void clearDatabase() throws Exception {
        Reader reader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("clearDatabase.sql").getFile()));
        scriptRunner.runScript(reader);
    }
}
