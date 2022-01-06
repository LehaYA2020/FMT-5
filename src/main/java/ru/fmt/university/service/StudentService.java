package ru.fmt.university.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fmt.university.dao.StudentRepository;
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

    public boolean assignStudentToGroup(int studentId, int groupId) {
        log.debug("StudentService calls studentRepository.assignToGroup({},{}).", studentId, groupId);
        return studentRepository.assignToGroup(studentId, groupId);
    }

    public boolean updateGroupAssignment(int studentId, int groupId) {
        log.debug("StudentService calls studentRepository.updateGroupAssignment({},{}).", studentId, groupId);
        return studentRepository.updateGroupAssignment(studentId, groupId);
    }

    public List<Student> getByGroup(int groupId) {
        log.debug("StudentService calls studentRepository.getByGroupId({}).", groupId);
        return studentRepository.getByGroupId(groupId);
    }

    public boolean deleteFromGroup(int studentId, int groupId) {
        log.debug("StudentService calls studentRepository.deleteFromGroup({},{}).", studentId, groupId);
        return studentRepository.deleteFromGroup(studentId, groupId);
    }

    public List<Lesson> getSchedule(int studentId) {
        log.debug("StudentService calls lessonService.getLessonsByStudent({}).", studentId);
        return lessonService.getLessonsByStudent(studentId);
    }
}
