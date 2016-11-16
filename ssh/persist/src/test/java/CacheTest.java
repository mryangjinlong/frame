import com.model.ql_1.Category;
import com.model.ql_1.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 缓存有三级
 * 一级：session级别的缓存
 * 二级：sessionFactory级别的缓存     hibernate的二级缓存是由第三方提供的
 *      不适合放经常改的，数据量很大的对象， 适合放数据量小的，改动小的，经常被访问的对象比如用户的权限
 *
 */
public class CacheTest {
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
        for(int i=0; i<10; i++) {
            Category c = new Category();
            c.setName("c" + i);
            Topic t = new Topic();
            t.setCategory(c);
            t.setTitle("t" + i);
            t.setCreateDate(new Date());
            session.save(c);
            session.save(t);
        }

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void cache(){
        Session session = sessionFactory.getCurrentSession();
        session.load(Category.class,1);   //load默认使用二级缓存  就是默认会先从二级缓存中找对象
        session.createQuery("").list();   //list默认在二级缓存中刷新数据加数据，但查询的时候不使用二级缓存
        session.createQuery("").setCacheable(true);  //  只有指定了使用二级缓存 query才会从二级缓存中查询
    }

    //join fetch
    @Test
    public void testCache1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category c = (Category)session.load(Category.class, 1);
        System.out.println(c.getName());

        //不会再发出sql语句，直接从session级别的缓存中取
        Category c2 = (Category)session.load(Category.class, 1);
        System.out.println(c2.getName());
        session.getTransaction().commit();
        session.close();

    }

    //join fetch
    @Test
    public void testCache2() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category c = (Category)session.load(Category.class, 1);
        System.out.println(c.getName());


        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        Category c2 = (Category)session2.load(Category.class, 1);
        System.out.println(c2.getName());


        session2.getTransaction().commit();
        session2.close();
    }

    //join fetch
    @Test
    public void testQueryCache() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Category> categories = (List<Category>)session.createQuery("from Category")
                .setCacheable(true).list();



        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        List<Category> categories2 = (List<Category>)session2.createQuery("from Category")
                .setCacheable(true).list();

        session2.getTransaction().commit();
        session2.close();
    }
}
