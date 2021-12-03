package ru.fmt.university.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.fmt.university.dao.*;
import ru.fmt.university.dto.*;
import ru.fmt.university.service.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static java.util.Collections.singletonList;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
public abstract class ServiceTest {

    protected static Course course = new Course(1, "Test", "Course");
    protected static Group group = new Group(1, "Test");
    protected static Student student = new Student(1, "fName", "lName");
    protected static Teacher teacher = new Teacher(1, "TestT", "lName", course);
    protected static Lesson lesson = new Lesson(1, course, teacher, 10, DayOfWeek.MONDAY,
            LocalTime.of(9, 30, 0), LessonType.LECTURE);
    protected static List<Lesson> lessons = singletonList(lesson);
    protected static List<Teacher> teachers = singletonList(teacher);
    protected static List<Course> courses = singletonList(course);
    protected static List<Group> groups = singletonList(group);
    protected static List<Student> students = singletonList(student);
    @InjectMocks
    @Autowired
    protected CourseService courseService;
    @InjectMocks
    @Autowired
    protected GroupService groupService;
    @InjectMocks
    @Autowired
    protected LessonService lessonService;
    @InjectMocks
    @Autowired
    protected StudentService studentService;
    @InjectMocks
    @Autowired
    protected TeacherService teacherService;
    @Mock
    protected CourseService courseServiceMock;
    @Mock
    protected GroupService groupServiceMock;
    @Mock
    protected LessonService lessonServiceMock;
    @Mock
    protected StudentService studentServiceMock;
    @Mock
    protected TeacherService teacherServiceMock;
    @Mock
    protected CourseRepository courseRepository;
    @Mock
    protected GroupRepository groupRepository;
    @Mock
    protected LessonRepository lessonRepository;
    @Mock
    protected StudentRepository studentRepository;
    @Mock
    protected TeacherRepository teacherRepository;

    @BeforeAll
    protected static void prepare() {
        lesson.setCourse(course);
        lesson.setGroups(groups);
        lesson.setTeacher(teacher);

        course.setLessons(lessons);
        course.setGroups(groups);

        group.setCourses(courses);
        group.setStudents(students);

        student.setGroup(group);

        teacher.setCourse(course);
    }
}
