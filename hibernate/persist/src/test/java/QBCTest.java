import com.model.ql_1.Category;
import com.model.ql_1.Msg;
import com.model.ql_1.Topic;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/3.
 */
public class QBCTest {

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

    //QBC的写法就是让查询语句更加的面向对象化
    @Test
    public void testQBC() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //criterion 标准/准则/约束   Criteria是与某个具体的session绑定到一起的     DetachedCriterea是自己创建再绑定到session上
        Criteria c = session.createCriteria(Topic.class) //from Topic     相当于为Topic这个类创建了一系列的约束

                .add(Restrictions.gt("id", 2)) //greater than = id > 2
                .add(Restrictions.lt("id", 8)) //little than = id < 8
                .add(Restrictions.like("title", "t_"))
                .createCriteria("category")
                .add(Restrictions.between("id", 3, 5)) //category.id >= 3 and category.id <=5
                ;
        //DetachedCriterea  脱离了session的管理
        for(Object o : c.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getId() + "-" + t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }
}
