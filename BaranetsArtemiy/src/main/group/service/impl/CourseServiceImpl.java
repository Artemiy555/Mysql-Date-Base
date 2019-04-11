package group.service.impl;

import group.dao.CourseDao;
import group.dao.impl.CourseDAOImpl;
import group.entily.Course;
import group.service.CourseService;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDAO;

    public CourseServiceImpl() {
        courseDAO = new CourseDAOImpl();
    }

    @Override
    public Course create(String title, String description) {
        if (title != null & description != null) {
            Course course = new Course(title, description);
            course.setId(courseDAO.create(course));
            return course;
        }
        return null;
    }
}
