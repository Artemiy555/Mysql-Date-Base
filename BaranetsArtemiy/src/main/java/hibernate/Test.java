package hibernate;

//import hibernate.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.*;

public class Test {
    public static void main(String[] args) {

        Employ employee1 = new Employ(
                "1",
                "1",
                0,
                "1",
                1000.00
        );
        Employ employee2 = new Employ(
                "1",
                "1",
                0,
                "1",
                1000.00
        );
       // Session session =
            //    HibernateUtil.getFactory().openSession();
        try {
          //  session.beginTransaction();
          //  session.save(employee1);
           // session.save(employee2);
           // session.getTransaction().commit();
        } catch (HibernateException exc) {
           // session.getTransaction().rollback();
        }

       // HibernateUtil.getFactory().close();
    }
}