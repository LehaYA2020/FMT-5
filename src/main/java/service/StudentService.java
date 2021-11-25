package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.StudentRepository;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Student;

import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private LessonService lessonService;


    public Student create(Student student) {
        return studentRepository.create(student);
    }

    public Student getById(int id) {
        Student student = studentRepository.getById(id);
        setGroup(student);
        return student;
    }

    public List<Student> getAll() {
        List<Student> students = studentRepository.getAll();
        setGroup(students);
        return students;
    }

    public void delete(int id) {
        studentRepository.delete(id);
    }

    public Student update(Student forUpdate) {
        return studentRepository.update(forUpdate);
    }

    public void assignStudentToGroup(Student student, Group group) {
        studentRepository.assignToGroup(student, group);
    }

    public void updateGroupAssignment(Student student, Group group) {
        studentRepository.updateGroupAssignment(student, group);
    }

    public List<Student> getByGroup(int id) {
        return studentRepository.getByGroupId(id);
    }

    public List<Lesson> getSchedule(Student student) {
        List<Lesson> lessons = lessonService.getLessonsByStudent(student);
        return lessons;
    }

    private void setGroup(Student student, Group group) {
        student.setGroup(group);
    }

    private void setGroup(List<Student> students, Group group) {
        for (Student student : students) {
            student.setGroup(group);
        }
    }

    private void setGroup(Student student) {
        if (student.getGroup() == null) {
            student.setGroup(groupService.getByStudent(student));
        }
    }

    private void setGroup(List<Student> students) {
        for (Student student : students) {
            setGroup(student);
        }
    }
}
