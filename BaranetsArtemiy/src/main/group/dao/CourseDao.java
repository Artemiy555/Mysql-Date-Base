package group.dao;

import group.entily.Course;

import java.util.List;

public interface CourseDao {
    Long create(Course course);

    Course read(Long id);

    boolean update(Course course);

    boolean delete(Long id);

    List<Course> findAll();
}
