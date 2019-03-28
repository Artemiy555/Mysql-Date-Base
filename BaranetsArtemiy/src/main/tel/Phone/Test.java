package tel.Phone;


import hibernate.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import tel.Phone.Phone;

public class Test {

    public static void main() {
        Phone phone1 = new Phone(
                "1",
                "1",
                1
        );
        Phone phone2 = new Phone(
                "2",
                "2",
                2
        );
        Phone phone3 = new Phone(
                "3",
                "3",
                3
        );

        Session session =
                HibernateUtil.getFactory().openSession();
        try {
            session.beginTransaction();
            session.save(phone1);
            session.save(phone2);
            session.save(phone3);
            session.getTransaction().commit();
        } catch (HibernateException exc) {
            session.getTransaction().rollback();
        }

        HibernateUtil.getFactory().close();
    }
}