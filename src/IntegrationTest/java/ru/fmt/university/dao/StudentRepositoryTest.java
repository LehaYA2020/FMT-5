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
public class StudentRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    StudentRepository studentRepository;
    private ScriptRunner scriptRunner;
    private static List<Student> testStudentList = new LinkedList<>();
    private static List<Group> testGroupList = new LinkedList<>();

    @BeforeAll
    public static void prepareList() {
        for (int i = 1; i <= 4; i++) {
            testStudentList.add(new Student(i, "S-0"+i, "Student"));
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
    public void getAll_shouldReturnAllStudentsFromDb() {
        assertEquals(testStudentList, studentRepository.getAll());
    }

    @Test
    public void getByID_shouldReturnStudentByIdFromDb() {
        assertEquals(testStudentList.get(0), studentRepository.getById(1));
    }

    @Test
    public void delete_shouldDeleteFromDb() {
        studentRepository.deleteById(1);
        assertEquals(testStudentList.subList(1,4), studentRepository.getAll());
    }

    @Test
    public void update_shouldUpdateStudent() {
        Student forUpdate = new Student(1,"S-01","UPDATED");
        studentRepository.update(forUpdate);
        assertEquals(forUpdate, studentRepository.getById(1));
    }

    @Test
    public void assignToGroup_shouldAssignToGroup() {
        studentRepository.assignToGroup(testStudentList.get(3), testGroupList.get(2));
        assertEquals(testStudentList.subList(3,4), studentRepository.getByGroupId(3));
    }

    @Test
    public void updateGroupAssignment_shouldUpdateGroupAssignment() {
        studentRepository.updateGroupAssignment(testStudentList.get(1), testGroupList.get(1));
        assertEquals(testStudentList.subList(1,3), studentRepository.getByGroupId(2));
    }

    @Test
    public void getByGroup_shouldReturnStudentsByGroup() {
        assertEquals(testStudentList.subList(0,2), studentRepository.getByGroupId(1));
    }

    @Test
    public void deleteFromGroup_shouldDeleteFromGroup() {
        studentRepository.deleteFromGroup(testStudentList.get(0), testGroupList.get(0));
        assertEquals(testStudentList.subList(1,2), studentRepository.getByGroupId(1));
    }

    @AfterEach
    public void clearDatabase() throws Exception {
        Reader reader = new BufferedReader(
                new FileReader(context.getClassLoader().getResource("clearDatabase.sql").getFile()));
        scriptRunner.runScript(reader);
    }
}
