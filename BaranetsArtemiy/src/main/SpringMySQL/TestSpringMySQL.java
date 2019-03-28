package SpringMySQL;

import SpringMySQL.service.RobotService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Calendar;

public class TestSpringMySQL {

    public static void main(String[] args){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("SpringMySQL/main-config.xml");

        RobotService service = context.getBean(RobotService.class);

        service.create("D2", Calendar.getInstance().getTime(),10.00);

    }

}
