package tel.SimAndNomer;

import hibernate.Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Test {

    public static void main() {
        SimAndNomer phone1 = new SimAndNomer(
                "1",
                "1"

        );
        SimAndNomer phone2 = new SimAndNomer(
                "2",
                "2"
        );
        SimAndNomer phone3 = new SimAndNomer(
                "3",
                "3"
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
