package ru.fmt.university.dao;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public abstract class RepositoryTest {
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
    @Autowired
    ApplicationContext context;
    protected ScriptRunner scriptRunner;

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
