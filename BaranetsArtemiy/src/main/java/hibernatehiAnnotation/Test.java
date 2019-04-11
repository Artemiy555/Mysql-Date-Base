package hibernatehiAnnotation;

import com.sun.org.apache.xpath.internal.operations.Or;
import hibernatehiAnnotation.Util.HibernateUtil;
import hibernatehiAnnotation.types.ProductType;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.List;

public class Test {

    public static void main(String[] args){
        Session session = HibernateUtil.getFactory().openSession();

        Order order1 =  new Order(ProductType.FISH, 1, 1.00, Calendar.getInstance().getTime());
        Order order2 =  new Order(ProductType.BREAD, 2, 2.00, Calendar.getInstance().getTime());
        Order order3 =  new Order(ProductType.MEAT, 3, 3.00, Calendar.getInstance().getTime());
        Order order4 =  new Order(ProductType.MILK, 4, 4.00, Calendar.getInstance().getTime());

        session.beginTransaction();
        session.save(order1);
        session.save(order2);
        session.save(order3);
        session.save(order4);
        session.getTransaction().commit();

        List<Order> orders =
                HibernateUtil.getFactory().openSession().createCriteria(Order.class).list();

        for(Order order : orders){
            System.out.println(order);
        }


        HibernateUtil.getFactory().close();

    }
}
