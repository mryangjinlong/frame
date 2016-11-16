import com.model.base.User;
import com.persist.UserDao;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/9/14.
 */
public class HibernateTemplate_SpringTest {
    @Test
    public void testHibernate(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("persistSpring.xml");
        UserDao userDao = (UserDao) classPathXmlApplicationContext.getBean("userDao");
        User user = new User();
        user.setName("testSpringhibernateTemplate");
        userDao.saveUser(user);
    }
}
