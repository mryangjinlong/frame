import com.model.inheritanceMapping.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 使用的类是  Enterprise  ,  Employee  会建立中间表  以多对多处理
 *
 */
public class OneToManyTest {

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
        Driver driver = new Driver();
        driver.setName("driver1");
        driver.setLength(111);
        driver.setLevel(333);

        session.save(driver);
        session.getTransaction().commit();
    }
}
