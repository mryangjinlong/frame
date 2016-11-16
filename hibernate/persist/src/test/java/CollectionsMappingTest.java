import com.model.collections_mapping.ClassE;
import com.model.collections_mapping.ClassF;
import com.model.manyToManyMappingCRUD.ClassC;
import com.model.manyToManyMappingCRUD.ClassD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2016/8/31.
 */
public class CollectionsMappingTest {
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


    //map映射
    @Test
    public void testMapMapping(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        ClassF f1 = new ClassF();
        f1.setName("f1");
        ClassF f2 = new ClassF();
        f2.setName("f2");

        ClassE classE = new ClassE();
        classE.getList().add(f1);
        classE.getList().add(f2);
        classE.setName("e1");
        f1.setClassE(classE);
        f2.setClassE(classE);
        session.save(classE);

        session.getTransaction().commit();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ClassE ee = (ClassE)session.get(ClassE.class,1);
        System.out.println(classE);
    }
}
