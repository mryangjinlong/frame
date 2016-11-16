import com.model.manyToManyMappingCRUD.ClassC;
import com.model.manyToManyMappingCRUD.ClassD;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 使用的类是  classC   classD
 */
public class TestMappingCRUD {

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
    public void testCascade1(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ClassD classD = new ClassD();
        classD.setName("d1");

        ClassC classC = new ClassC();
        classC.setName("c1");

        classD.setClassC(classC);
        //如果不保存classC那么这里会出错。  hibernate不会自动保存 classD的关联
        // 如果想要自动级联保存  需要设置cascade
//        session.save(classC);
        session.save(classD);

        session.getTransaction().commit();
    }

    @Test
    public void testCascade2(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ClassD d1 = new ClassD();
        d1.setName("d1");
        ClassD d2 = new ClassD();
        d2.setName("d2");

        ClassC c = new ClassC();

        c.getSet().add(d1);
        c.getSet().add(d2);
        c.setName("c");

        d1.setClassC(c);
        d2.setClassC(c);
        session.save(c);


        session.getTransaction().commit();
    }

    @Test
    public void testFetch(){
        testCascade2();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        //取many的其中一个的时候会默认把one也取出来  取classD的时候会默认把classC取出来
        //取one的时候默认不会把many取出来
//        ClassD classD = (ClassD)session.get(ClassD.class,1);
//        System.out.println(classD);
//        System.out.println(classD.getClassC());

        ClassC classC = (ClassC)session.get(ClassC.class,1);
        System.out.println(classC);
        System.out.println(classC.getSet());
        session.getTransaction().commit();

    }

    @Test
    public void testDelete(){
        testCascade2();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        ClassD classD = (ClassD)session.get(ClassD.class,1);
//        classD.setClassC(null);   //解除关联关系，不然删的时候会关联删除classC,  classC又关联删除所有的classD  或者使用hql
//        session.delete(classD);
        session.createQuery("delete from ClassD d where d.id = 1").executeUpdate();
        session.getTransaction().commit();

    }


}
