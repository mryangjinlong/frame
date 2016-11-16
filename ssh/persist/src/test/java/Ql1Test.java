import com.model.ql_1.Category;
import com.model.ql_1.Msg;
import com.model.ql_1.MsgInfo;
import com.model.ql_1.Topic;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Ql1Test {

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


    @Test
    public void testHQL_01() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Category");   //这里的Category是类名 不要写表名
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println("==========="+c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_02() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Category c where c.name > 'c5'");
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_03() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Category c order by c.name desc");
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_04() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select distinct c from Category c order by c.name desc");
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_05() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
		/*Query q = session.createQuery("from Category c where c.id > :min and c.id < :max");
		//q.setParameter("min", 2);
		//q.setParameter("max", 8);
		q.setInteger("min", 2);
		q.setInteger("max", 8);*/
        //使用点位符
        Query q = session.createQuery("from Category c where c.id > :min and c.id < :max")
                .setParameter("min",2)
                .setParameter("max", 8);
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getId() + "-" + c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_06() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Category c where c.id > ? and c.id < ?");
        q.setParameter(0, 2)  //这里第一个参数表示 第几个问号
                .setParameter(1, 8);
//		q.setParameter(1, 8);
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getId() + "-" + c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }
    //分页
    @Test
    public void testHQL_07() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Category c order by c.name desc");
        q.setMaxResults(4);   //每页4条
        q.setFirstResult(2);  //从第几条开始
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getId() + "-" + c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_08() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select c.id,  c.name from Category c order by c.name desc");
        List<Object[]> categories = (List<Object[]>)q.list();
        for(Object[] o : categories) {
            System.out.println(o[0] + "-" + o[1]);
        }
        session.getTransaction().commit();
        session.close();

    }

    //设定fetch type 为lazy后将不会有第二条sql语句
    @Test
    public void testHQL_09() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.category.id = 1");
        List<Topic> topics = (List<Topic>)q.list();
        for(Topic t : topics) {
            System.out.println(t.getTitle());
            //System.out.println(t.getCategory().getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    //设定fetch type 为lazy后将不会有第二条sql语句
    @Test
    public void testHQL_10() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.category.id = 1");
        List<Topic> topics = (List<Topic>)q.list();
        for(Topic t : topics) {
            System.out.println(t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_11() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Msg m where m.topic.category.id = 1");  //导航再导航

        for(Object o : q.list()) {
            Msg m = (Msg)o;
            System.out.println(m.getCont());
        }
        session.getTransaction().commit();
        session.close();

    }
    //了解即可
    //VO Value Object
    //DTO data transfer object
    @Test
    public void testHQL_12() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select new com.bjsxt.hibernate.MsgInfo(m.id, m.cont, m.topic.title, m.topic.category.name) from Msg");

        for(Object o : q.list()) {
            MsgInfo m = (MsgInfo)o;
            System.out.println(m.getCont());
        }
        session.getTransaction().commit();
        session.close();

    }

    //动手测试left right join
    //为什么不能直接写Category名，而必须写t.category
    //因为有可能存在多个成员变量（同一个类），需要指明用哪一个成员变量的连接条件来做连接
    @Test
    public void testHQL_13() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select t.title, c.name from Topic t join t.category c "); //join Category c
        for(Object o : q.list()) {
            Object[] m = (Object[])o;
            System.out.println(m[0] + "-" + m[1]);
        }
        session.getTransaction().commit();
        session.close();

    }

    //学习使用uniqueResult
    @Test
    public void testHQL_14() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Msg m where m = :MsgToSearch ");  //对象相等  用于多个条件的查询
        Msg m = new Msg();
        m.setId(1);
        q.setParameter("MsgToSearch", m);

        Msg mResult = (Msg)q.uniqueResult();    //uniqueResult 要返回唯一的结果   如果已经知道拿到的只是唯一的结果
        System.out.println(mResult.getCont());
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_15() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select count(*) from Msg m");   //这里的count()  返回的是long类型所有要强转为long

        long count = (Long)q.uniqueResult();
        System.out.println(count);
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_16() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select max(m.id), min(m.id), avg(m.id), sum(m.id) from Msg m");

        Object[] o = (Object[])q.uniqueResult();
        System.out.println(o[0] + "-" + o[1] + "-" + o[2] + "-" + o[3]);
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_17() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Msg m where m.id between 3 and 5");

        for(Object o : q.list()) {
            Msg m = (Msg)o;
            System.out.println(m.getId() + "-" + m.getCont());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_18() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Msg m where m.id in (3,4, 5)");

        for(Object o : q.list()) {
            Msg m = (Msg)o;
            System.out.println(m.getId() + "-" + m.getCont());
        }
        session.getTransaction().commit();
        session.close();

    }

    //is null 与 is not null
    @Test
    public void testHQL_19() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Msg m where m.cont is not null");

        for(Object o : q.list()) {
            Msg m = (Msg)o;
            System.out.println(m.getId() + "-" + m.getCont());
        }
        session.getTransaction().commit();
        session.close();

    }


    //is empty and is not empty
    @Test
    public void testHQL_20() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.msgs is empty");   //is empty 意思是这个集合是不是为空

        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getId() + "-" + t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_21() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.title like '%5'");

        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getId() + "-" + t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }


    @Test
    public void testHQL_22() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.title like '_5'");

        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getId() + "-" + t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }
    //不重要
    @Test
    public void testHQL_23() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select lower(t.title)," +
                "upper(t.title)," +
                "trim(t.title)," +
                "concat(t.title, '***')," +
                "length(t.title)" +
                " from Topic t ");

        for(Object o : q.list()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + "-" + arr[1] + "-" + arr[2] + "-" + arr[3] + "-" + arr[4] + "-");
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_24() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select abs(t.id)," +
                "sqrt(t.id)," +
                "mod(t.id, 2)" +
                " from Topic t ");

        for(Object o : q.list()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + "-" + arr[1] + "-" + arr[2] );
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_25() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select current_date, current_time, current_timestamp, t.id from Topic t");

        for(Object o : q.list()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2] + " | " + arr[3]);
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_26() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.createDate < :date");
        q.setParameter("date", new Date());
        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_27() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title") ;
        for(Object o : q.list()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + "|" + arr[1]);
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_28() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title having count(*) >= 1") ;
        for(Object o : q.list()) {
            Object[] arr = (Object[])o;
            System.out.println(arr[0] + "|" + arr[1]);
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_29() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.id < (select avg(t.id) from Topic t)") ;
        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_30() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Topic t where t.id < ALL (select t.id from Topic t where mod(t.id, 2)= 0) ") ;   // t.id  小于 all里面取出来的所有值
        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }

    //用in 可以实现exists的功能
    //但是exists执行效率高
    @Test
    public void testHQL_31() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();// t.id not in (1)
        Query q = session.createQuery("from Topic t where not exists (select m.id from Msg m where m.topic.id=t.id)") ;
//		Query q = session.createQuery("from Topic t where exists (select m.id from Msg m where m.topic.id=t.id)") ;
        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getTitle());
        }
        session.getTransaction().commit();
        session.close();

    }

    //update and delete
    //规范并没有说明是不是要更新persistent object，所以如果要使用，建议在单独的trasaction中执行

    @Test
    public void testHQL_32() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createQuery("update Topic t set t.title = upper(t.title)") ;

        q.executeUpdate();
        q = session.createQuery("from Topic");
        for(Object o : q.list()) {
            Topic t = (Topic)o;
            System.out.println(t.getTitle());
        }
        session.createQuery("update Topic t set t.title = lower(t.title)")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    //不重要
    @Test
    public void testHQL_33() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.getNamedQuery("topic.selectCertainTopic");
        q.setParameter("id", 5);
        Topic t = (Topic)q.uniqueResult();
        System.out.println(t.getTitle());
        session.getTransaction().commit();
        session.close();

    }

    //Native（了解）  直接使用数据库语句
    @Test
    public void testHQL_34() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from category limit 2,4")
                .addEntity(Category.class);  //把返回值转换成 Category.class类型
        List<Category> categories = (List<Category>)q.list();
        for(Category c : categories) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHQL_35() {
        //尚未实现JPA命名的NativeSQL

    }
}
