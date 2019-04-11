package Spring.part2.config;


import Spring.part2.entity.Company;
import Spring.part2.entity.Emploee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;


@Configuration
    public class BeanConfig{
        @Bean(name = "Ivan")
        @Description("Иван, менеджер среднего звена")
        public Emploee beanIvan(){
            return new Emploee("Ivan","Manager",50.00);


        }


    @Bean(name = "Victor")
    @Description("Виктор, менеджер высокого звена")
    public Emploee beanViktor(){
        return new Emploee("Виктор","Manager",100.00);


    }


    @Bean
    public Company beanCompany(){
        return new Company("Company");
    }

    @Bean
    public List<Emploee> emploee(){
            List<Emploee> emploees = new ArrayList<>();
            emploees.add(beanIvan());
            emploees.add(beanViktor());
            return emploees;
    }





    }
