package ru.fmt.university.models;

import java.sql.Time;
import java.util.List;

public class Lesson {
    public Lesson(int id, Course course, Teacher teacher, int classRoom, Day day, Time startTime, LessonType type) {
        this.id = id;
        this.course = course;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.day = day;
        this.startTime = startTime;
        this.type = type;
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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

    private int id;
    private Course course;
    private List<Group> groups;
    private Teacher teacher;
    private int classRoom;
    private Day day;
    private Time startTime;
    private LessonType type;
}
