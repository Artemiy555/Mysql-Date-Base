package Spring.part1;

import Spring.part1.entity.Humen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLConfigTest {
    public static void main(String[] args){
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        new String[]{"Spring/config.xml"}
                );
        Humen humen = context.getBean("Ivan",Humen.class);
        System.out.println(humen);

        Humen humen1 = context.getBean("Nagibator228",Humen.class);
        System.out.println(humen1);


    }
}