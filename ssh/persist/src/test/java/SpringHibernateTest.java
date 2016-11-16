import com.persist.UserDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/9/7.
 */
public class SpringHibernateTest {

    @Test
    public void testSpring(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("persistSpring.xml");
        UserDao userDao = (UserDao)classPathXmlApplicationContext.getBean("userDao");
        if (userDao != null)    userDao.saveUser(null);
        else System.out.println("userDao 是空的");
    }
}
