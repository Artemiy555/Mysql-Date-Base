package SpringMySQL.service;

import SpringMySQL.entity.Robot;

import java.util.Date;
import java.util.List;

public interface RobotService {
    Robot create(String model, Date date, Double price);

    Robot findById(Long id);

    void update(Robot robot);

    void delete(Robot robot);

    List<Robot> findAll();

}
