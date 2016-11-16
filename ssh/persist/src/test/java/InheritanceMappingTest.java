import com.model.component_mapping.Person;
import com.model.inheritanceMapping.Builder;
import com.model.inheritanceMapping.Driver;
import com.model.inheritanceMapping.Human;
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
public class InheritanceMappingTest {

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
    public void testInheritanceMappingSave(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Driver driver = new Driver();
        driver.setName("driver1");
        driver.setLength(111);
        driver.setLevel(333);

        Builder builder = new Builder();
        builder.setName("builder1");
        builder.setTitle("ddddddddd");
        session.save(driver);
        session.save(builder);
        session.getTransaction().commit();
    }
    @Test
    public void testInheritanceMappingLoad(){
        testInheritanceMappingSave();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Human person = (Human)session.load(Human.class,2);   //最好避免多态查询
        System.out.println(person.getName());
        session.getTransaction().commit();
    }


}
