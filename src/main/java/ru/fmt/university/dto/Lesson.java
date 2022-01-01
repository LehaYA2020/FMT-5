package ru.fmt.university.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="course_id")
    private int courseId;
    @Column(name="teacher_id")
    private int teacherId;
    @Column(name="classroom")
    private int classRoom;
    @Column(name="day")
    private DayOfWeek dayOfWeek;
    @Column(name="time")
    private LocalTime startTime;
    @Column(name="type")
    private LessonType type;

    public Lesson(int id, int courseId, int teacherId, int classRoom, DayOfWeek dayOfWeek, LocalTime startTime, LessonType type) {
        this.id = id;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.classRoom = classRoom;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.type = type;
    }

    public Lesson(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    public DayOfWeek getDay() {
        return dayOfWeek;
    }

    public void setDay(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, teacherId, classRoom, dayOfWeek, startTime, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id && classRoom == lesson.classRoom && Objects.equals(courseId, lesson.courseId) && Objects.equals(teacherId, lesson.teacherId) && dayOfWeek == lesson.dayOfWeek && Objects.equals(startTime, lesson.startTime) && type == lesson.type;
    }
}
