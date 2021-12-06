package ru.fmt.university.service;

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
        return studentRepository.getById(id);
    }

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public void delete(int id) {
        studentRepository.delete(id);
    }

    public Student update(Student forUpdate) {
        return studentRepository.update(forUpdate);
    }

    public void assignStudentToGroup(Student student, Group group) {
        studentRepository.assignToGroup(student, group.getId());
    }

    public void updateGroupAssignment(Student student, Group group) {
        studentRepository.updateGroupAssignment(student, group.getId());
    }

    public List<Student> getByGroup(int id) {
        return studentRepository.getByGroupId(id);
    }

    public List<Lesson> getSchedule(Student student) {
        return lessonService.getLessonsByStudent(student);
    }
}
