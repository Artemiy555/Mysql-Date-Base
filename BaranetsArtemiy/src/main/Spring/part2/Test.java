package Spring.part2;

import Spring.part2.config.BeanConfig;
import Spring.part2.entity.Company;
import Spring.part2.entity.Emploee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] arg)
    {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext();

        configApplicationContext.register(BeanConfig.class);
        configApplicationContext.refresh();

        Company company = configApplicationContext.getBean(Company.class);
        System.out.println(company);
        
    }
}
