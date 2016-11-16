import com.model.ql_1.Category;
import com.model.ql_1.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public class List_IterateTest {
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

    //join fetch
    @Test
    public void testQueryList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List<Topic> topics = (List<Topic>)session.createCriteria(Topic.class).list();
        List<Category> categories = (List<Category>)session.createQuery("from Category").list();

        for(Category c : categories) {
            System.out.println(c.getName());
        }

        List<Category> categories2 = (List<Category>)session.createQuery("from Category").list();
        for(Category c : categories2) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }



    //join fetch
    @Test
    public void testQueryIterate() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //iterate()   这个方法不支持left  join    这个方法不取对象的内容只取主键在用到的时候再取内容
        // 这个方法会使用session级缓存 ，如果在一个session中执行两次这个方法只会发一次sql语句
        //list()   会取出这个对象的所有内容   不会使用session级缓存，  在一个session中执行几次就会发几次的sql语句
        Iterator<Category> categories = (Iterator<Category>)session.createQuery("from Category").iterate();


        while(categories.hasNext()) {
            Category c = categories.next();
            System.out.println(c.getName());
        }

        Iterator<Category> categories2 = (Iterator<Category>)session.createQuery("from Category").iterate();

        while(categories2.hasNext()) {
            Category c = categories2.next();
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }
}
