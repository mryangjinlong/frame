import com.model.ql_1.Category;
import com.model.ql_1.Msg;
import com.model.ql_1.Topic;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/3.
 */
public class QBETest {

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
    public void save(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        for(int i=0; i<10; i++) {
            Category c = new Category();
            c.setName("c" + i);
            session.save(c);
        }

        for(int i=0; i<10; i++) {
            Category c = new Category();
            c.setId(1);
            Topic t = new Topic();
            t.setCategory(c);
            t.setTitle("t" + i);
            t.setCreateDate(new Date());
            session.save(t);

        }

        for(int i=0; i<10; i++) {

            Topic t = new Topic();
            t.setId(1);
            Msg m = new Msg();
            m.setCont("m" + i);
            m.setTopic(t);
            session.save(m);

        }




        session.getTransaction().commit();
        session.close();
    }


    //is empty and is not empty
    //query by criteria   / query by example
    //QBE是QBC的一部份
    @Test
    public void testQBE() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Topic tExample = new Topic();    //创建一个对象  在数据库中查找哪个对象与这个对象比较像
        tExample.setTitle("T_");

        Example e = Example.create(tExample)
                .ignoreCase().enableLike();   //enbleLike 就是sql中的  like
        Criteria c = session.createCriteria(Topic.class)
                .add(Restrictions.gt("id", 2))
                .add(Restrictions.lt("id", 8))
                .add(e)
                ;


        for(Object o : c.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getId() + "-" + t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }
}
