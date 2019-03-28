package SpringMySQL.dao;

import SpringMySQL.entity.Robot;

import java.util.List;

public interface RobotDao {
    Long create(Robot robot);

    Robot read(Long id);

    boolean update(Robot robot);

    boolean delete(Robot robot);

    List<Robot> findAll();


}
