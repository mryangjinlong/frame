import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 使用的类是  Person  ,  PersonCardMsg
 * 一个对象的属性引用另一个对象  如果是1对1的关系  那么可以放在一张表里面  就是一个对象是另一个的对象的一部份
 */
public class ComponentMappingTest {

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
    public void testComponentMapping(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.getTransaction().commit();;
    }
}
