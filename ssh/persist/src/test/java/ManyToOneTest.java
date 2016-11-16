import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 使用的类是  Group  ,  User    会在多的表中建立外键
 *
 */
public class ManyToOneTest {

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
    public void testManyToOne(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.getTransaction().commit();;
    }
}
