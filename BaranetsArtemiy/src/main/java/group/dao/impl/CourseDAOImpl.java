package group.dao.impl;

import group.dao.CourseDao;
import group.entily.Course;
import group.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CourseDAOImpl implements CourseDao {

    private SessionFactory factory;

    public CourseDAOImpl() {
        factory = HibernateUtil.getFactory();
    }

    @Override
    public Long create(Course course) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(course);
            session.getTransaction().commit();
            return id;
        } catch (HibernateException exc) {
            return null;
        }
    }

    @Override
    public Course read(Long id) {
        return null;
    }

    @Override
    public boolean update(Course course) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }
}

