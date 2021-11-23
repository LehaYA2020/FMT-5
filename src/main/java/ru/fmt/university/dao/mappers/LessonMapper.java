package ru.fmt.university.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Lesson;
import ru.fmt.university.dto.LessonType;
import ru.fmt.university.dto.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;

@Component
public class LessonMapper implements RowMapper<Lesson> {

    @Override
    public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Lesson(rs.getInt("id"), new Course(rs.getInt("course_id")),
                new Teacher(rs.getInt("teacher_id")), rs.getInt("classroom"),
                DayOfWeek.valueOf(rs.getString("day")), rs.getTime("time").toLocalTime(),
                LessonType.valueOf(rs.getString("type")));
    }
}
