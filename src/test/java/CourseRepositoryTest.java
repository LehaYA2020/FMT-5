import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.fmt.university.dao.CourseRepository;
import ru.fmt.university.models.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;


    @Test
    public void insert_shouldInsertCourseToDb(){
        Course expected = new Course(1,"0","test");
        courseRepository.insert(expected);
        assertEquals(expected, courseRepository.getById(1));
    }
}
