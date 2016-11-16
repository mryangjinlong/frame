import com.model.base.JobTitle;
import com.model.base.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/23.
 */
public class UserDaoTest {

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
        Session session = sessionFactory.getCurrentSession() ;
        User user = new User();
        user.setName("liuxiaoyan");
        user.setSex(false);
        user.setType(JobTitle.B);
        session.beginTransaction();
        session.save(user);//save后对象存入session的缓存中，修改对象后commit会提交修改后的数据，实际上还未保存进数据库
        // ,save后对象已经有了id
        user.setName("ddd");
        session.getTransaction().commit();//commit真正进入数据库 再修改对象已经不会再入库
        return;
    }
    @Test
    public  void testDelete(){
        User user = new User();
        user.setId(2);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public  void testLoad(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        /*
        * load的时候实际上还没有导入数据 产生的是代理对象 当需要用到的时候才去数据库取  commit后再用会报错
        * 导入缓存 修改数据commit后修改的数据也会进入数据库
        * */
        User user = (User) session.load(User.class,3);
        user.setName("deimadsflkj");
        session.getTransaction().commit();
    }
    @Test
    public  void testGet(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.get(User.class,3); //导入缓存 修改数据commit后修改的数据也会进入数据库
        System.out.println(user.getClass());
        user.setName("xiao liu");
        session.getTransaction().commit();
    }

    @Test
    public void testUpdate(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User();
        user.setId(3);
        user.setName("kkkkkk");
        session.update(user); //update    一般用在这个对象脱离管理的情况下， 如transient ， detached   会使对象进入缓存  并且会更新所有字段
        session.getTransaction().commit();
    }

    @Test
    public void testMerge(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User();
        user.setId(3);
        user.setName("ddddd");
        session.merge(user); //update 会使对象进入缓存  并且会更新所有字段
        session.getTransaction().commit();
    }
    @Test
    public void testQuery(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("update User set name='dfdf' where id = 3").executeUpdate();
        session.getTransaction().commit();
    }
    @Test
    public void testSaveOrUpdate(){
        Session session = sessionFactory.getCurrentSession();
        User user = new User();
        user.setId(3);
        user.setName("aaaaa");
        session.beginTransaction();
        session.saveOrUpdate(user);
        user.setBirthday(new Date());
        session.getTransaction().commit();
    }

    @Test
    public void testClear(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User)session.load(User.class,3);
        user.getName();
        session.clear();    //从缓存中将对象清除
        user.setName("sdfasdfasdf");
        session.getTransaction().commit();
    }
    @Test
    public void testFlush(){
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushModeType.COMMIT);
        session.beginTransaction();
        User user = (User)session.load(User.class,3);
        user.getName();
        user.setName("sdfasdfasdf");

        session.flush();   //强制将缓存中的对象与数据库同步
        user.setName("dfdfdf");
        session.getTransaction().commit();
    }

    //以下方法用到的时候再去看
    @Test
    public void testOther(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User();
        user.setName("kingking");
        session.persist(user);
        session.refresh(user);
        session.lock(user, LockModeType.NONE);
        session.getTransaction().commit();
    }
    @Test
    public void testSchemaExport(){
        //生成建表语句
    }




}