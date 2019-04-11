package SpringMySQL.dao.impl;

import SpringMySQL.dao.RobotDao;
import SpringMySQL.entity.Robot;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RobotDaoImpl implements RobotDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Robot robot) {
        return (Long) factory
                .getCurrentSession()
                .save(robot);
    }

    @Override
    public Robot read(Long id) {
        return factory
                .getCurrentSession()
                .get(Robot.class, id);
    }

    @Override
    public boolean update(Robot robot) {
        factory
                .getCurrentSession()
                .saveOrUpdate(robot);
        return true;
    }

    @Override
    public boolean delete(Robot robot) {
        factory
                .getCurrentSession()
                .delete(robot);
        return true;
    }

    @Override
    public List<Robot> findAll() {
        return factory
                .getCurrentSession()
                .createCriteria(Robot.class)
                .list();
    }

}
