package SpringMySQL.service.Impl;

import SpringMySQL.dao.RobotDao;
import SpringMySQL.entity.Robot;
import SpringMySQL.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class RobotServiceImpl implements RobotService{

    @Autowired
    private RobotDao dao;

    @Override
    public Robot create(String model, Date creationDate, Double price) {
        Robot robot = new Robot(model, creationDate, price);
        robot.setId(dao.create(robot));
        return robot;
    }

    @Override
    public Robot findById(Long id) {
        if (id != null) {
            return dao.read(id);
        }
        return null;
    }

    @Override
    public void update(Robot robot) {
        if (dao.update(robot)) {
            System.out.println("Обновление прошло успешно");
            return;
        }
        System.out.println("Обновление не удалось");
    }

    @Override
    public void delete(Robot robot) {
        if (dao.delete(robot)) {
            System.out.println("Удаление прошло успешно");
            return;
        }
        System.out.println("Удаление не удалось");
    }

    @Override
    public List<Robot> findAll() {
        return dao.findAll();
    }

}
