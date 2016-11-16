import com.model.mapping.Husband;
import com.model.mapping.Student;
import com.model.mapping.Teacher;
import com.model.mapping.Wife;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MappingTest {

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

    /*
    * 测试外键关联
    * */
    @Test
    public void testOneToOne1(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Husband husband = new Husband();
        Wife wife = new Wife();
        session.save(wife);
        husband.setWife(wife);
        husband.setName("long");
        session.save(husband);

        session.getTransaction().commit();;

    }
    /*
    * 测试主键关联
    * */
    @Test
    public void testOneToOne2(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Teacher teacher = new Teacher();
        teacher.setName("fang");
        session.save(teacher);
        Student student = new Student();
        student.setName("long");
        student.setTeacher(teacher);
        session.save(student);

        session.getTransaction().commit();;

    }
    /*
    * 测试联合主键关联
    * 使用的类是House  Owner  , HousePk
    * */
    @Test
    public void testOneToOne3(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();



        session.getTransaction().commit();;
    }


}
