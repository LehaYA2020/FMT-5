package ru.fmt.university.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.fmt.university.models.Course;

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
    @Autowired
    DataSource dataSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private CourseRepository courseRepository;
    private ScriptRunner scriptRunner;

    @BeforeEach
    public void fillDb() throws Exception {
        scriptRunner = new ScriptRunner(dataSource.getConnection());
        Reader fillDatabaseReader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("createTables.sql").getFile()));
        scriptRunner.runScript(fillDatabaseReader);
    }

    @Test
    public void insert_shouldInsertCourseToDb() {
        Course expected = new Course(1, "0", "test");
        courseRepository.create(expected);
        assertEquals(expected, courseRepository.getById(1));
    }

    @Test
    public void getAll_shouldReturnAllCourses() {
        List<Course> courses= new LinkedList<>();
        courses.add(new Course(1, "0", "test"));
        courses.add(new Course(2, "10", "test"));
        for (Course course:courses) {
            courseRepository.create(course);
        }
        assertEquals(courses, courseRepository.getAll());
    }
}
