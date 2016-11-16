import com.model.ql_1.Category;
import com.model.ql_1.Msg;
import com.model.ql_1.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/3.
 */
public class CapabilityTest {
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
        Session session = sessionFactory.getCurrentSession();
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
    }

    //1+N问题
    @Test
    public void test(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        //这里的前提是  Topic与Category是 多对一的关系
        //如果Topic里面的category的fetch是eager那么 取每一个topic的时候都单独再发出sql取category   这就是1+N问题
        //解决方案1 ：  fetch 设为lazy  这里只有用到category时才会发出
        //解决方案2 ：  session.createCriteria(Topic.class);   使用join的方式，不再单独发出sql语句
        //解决方案3 ：  @BatchSize
        //解决方案4 ：  testQuery4

        List<Topic> topicList = (List<Topic>)session.createQuery("from Topic ").list();
        for (Topic topic : topicList) {
            System.out.println("topic.getTitle()=======" + topic.getTitle());
        }
        session.clear();  //在导数据的时候如果内存中数据太多。 可以用clear清除内存中的数据
        session.getTransaction().commit();
    }


    //@BatchSize   在Category类上加这个注解  就会一次性的取指定的数量出来 而不会分开来单独取
    @Test
    public void testQuery3() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List<Topic> topics = (List<Topic>)session.createCriteria(Topic.class).list();
        List<Topic> topics = (List<Topic>)session.createQuery("from Topic").list();

        for(Topic t : topics) {
            System.out.println(t.getId() + "-" + t.getTitle());
            System.out.println(t.getCategory().getName());
        }
        session.getTransaction().commit();
        session.close();

    }


    //join fetch
    @Test
    public void testQuery4() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List<Topic> topics = (List<Topic>)session.createCriteria(Topic.class).list();
        List<Topic> topics = (List<Topic>)session.createQuery("from Topic t left join fetch t.category c").list();

        for(Topic t : topics) {
            System.out.println(t.getId() + "-" + t.getTitle());
            System.out.println(t.getCategory().getName());
        }
        session.getTransaction().commit();
        session.close();

    }
}
