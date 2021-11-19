package ru.fmt.university.models;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class Lesson {
    private int id;
    private Course course;
    private List<Group> groups;
    private Teacher teacher;
    private int classRoom;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private LessonType type;

    public Lesson(int id, Course course, Teacher teacher, int classRoom, DayOfWeek dayOfWeek, Time startTime, LessonType type) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.type = type;
    }

    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id && classRoom == lesson.classRoom && Objects.equals(course, lesson.course) && dayOfWeek == lesson.dayOfWeek && Objects.equals(startTime, lesson.startTime) && type == lesson.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, groups, teacher, classRoom, dayOfWeek, startTime, type);
    }
}
