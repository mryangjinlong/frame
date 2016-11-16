import com.model.transaction1.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2016/9/5.
 */
public class Concurrency_Optimistic_Lock {

    static SessionFactory sessionFactory = null;

    @BeforeClass
    public static void buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    @AfterClass
    public static void closeSessionFactory(){
        sessionFactory.close();
    }


    @Test
    public void testSave() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account a = new Account();
        a.setBalance(100);
        session.save(a);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testOptimisticLock() {
        Session session = sessionFactory.openSession();

        Session session2 = sessionFactory.openSession();




        session.beginTransaction();
        Account a1 = (Account) session.load(Account.class, 1);


        session2.beginTransaction();
        Account a2 = (Account) session2.load(Account.class, 1);

        a1.setBalance(900);
        a2.setBalance(1100);

        session.getTransaction().commit();
        System.out.println(a1.getVersion());

        session2.getTransaction().commit();
        System.out.println(a2.getVersion());

        session.close();
        session2.close();
    }

}
