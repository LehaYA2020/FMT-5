package ru.fmt.university.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet set, int i) throws SQLException {
        return new Teacher(set.getInt("id"), set.getString("first_name"),
                set.getString("last_name"), new Course(set.getInt("course_id")));
    }
}
