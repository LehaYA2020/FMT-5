package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.StudentRepository;
import ru.fmt.university.dto.Group;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.Student;

import java.util.List;

@Component
@Log4j2
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LessonService lessonService;

    public Student create(Student student) {
        log.debug("StudentService calls studentRepository.create({}).", student.getId());
        return studentRepository.create(student);
    }

    public Student getById(int id) {
        log.debug("StudentService calls studentRepository.getById({}).", id);
        return studentRepository.getById(id);
    }

    public List<Student> getAll() {
        log.debug("StudentService calls studentRepository.getAll().");
        return studentRepository.getAll();
    }

    public boolean delete(int id) {
        log.debug("StudentService calls studentRepository.delete({}).", id);
        return studentRepository.delete(id);
    }

    public Student update(Student forUpdate) {
        log.debug("StudentService calls studentRepository.update({}).", forUpdate.getId());
        return studentRepository.update(forUpdate);
    }

    public void assignStudentToGroup(Student student, Group group) {
        log.debug("StudentService calls studentRepository.assignToGroup({},{}).", student.getId(), group.getId());
        studentRepository.assignToGroup(student, group.getId());
    }

    public void updateGroupAssignment(Student student, Group group) {
        log.debug("StudentService calls studentRepository.updateGroupAssignment({},{}).", student.getId(), group.getId());
        studentRepository.updateGroupAssignment(student, group.getId());
    }

    public List<Student> getByGroup(int id) {
        log.debug("StudentService calls studentRepository.getByGroupId({}).", id);
        return studentRepository.getByGroupId(id);
    }

    public List<Lesson> getSchedule(Student student) {
        log.debug("StudentService calls lessonService.getLessonsByStudent({}).", student.getId());
        return lessonService.getLessonsByStudent(student);
    }
}
