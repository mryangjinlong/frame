import com.model.tree.Org;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2016/8/31.
 */
public class TreeTest {


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
    public void testTree(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Org o = new Org();
        o.setName("o");

        Org o1 = new Org();
        o1.setName("o1");

        Org o2 = new Org();
        o2.setName("o2");

        Org o11 = new Org();
        o11.setName("o11");
        Org o12 = new Org();
        o12.setName("o12");

        o.getChildren().add(o1);
        o.getChildren().add(o2);

        o1.getChildren().add(o11);
        o1.getChildren().add(o12);

        o2.setParent(o);
        o1.setParent(o);

        o11.setParent(o1);
        o12.setParent(o1);

        session.save(o);

        session.getTransaction().commit();
    }
}
