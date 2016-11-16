import com.model.transaction1.Account;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.LockModeType;

/**
 * 一 事务并发可能会出现的问题
 * 1.第一类丢失更新
 * 2.脏读 读到了另一个事务没有提交的数据
 * 3.不可重复读 同一个数据前后读两次是不一样的
 * 4.第二类丢失更新
 * 5. 幻读  数据前后读  多了一条数据  数据库中的  hibernate中都可以设置  如果hibernate不设那么就按数据库的级别
 * 二 事务的隔离级别  事务级别越高效率越低
 * 1. read-uncommitted   允许读取没有commit的数据  很少设
 * 2. read-commited     只有commit后才能读取   一般设成这项
 * 3. repeatable read    可重复读  在读的时候加把锁 当前事务没有结束。 其他事务不能修改此记录
 * 4. serializable       不要并发， 所有事务按顺序执行   一般不会设这个级别
 */
public class Concurrency_Pessimistic_Lock {

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

        Account a = new Account();
        a.setBalance(100);
        session.save(a);

        session.getTransaction().commit();
        session.close();
    }

    /*
    * 这个例子生成的语句是
    *  select account0_.id as id1_0_0_, account0_.balance as balance2_0_0_ from Account account0_ where account0_.id=?
    * 没有加锁
    * */
    @Test
    public void testOperation1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account a = (Account)session.load(Account.class, 1);
        int balance = a.getBalance();
        //do some caculations
        balance = balance - 10;
        a.setBalance(balance);
        session.getTransaction().commit();
        session.close();
    }

    /*
    * LockMod 查hibernate文档
    * 这个例子生成的语句是
    * select account0_.id as id1_0_0_, account0_.balance as balance2_0_0_ from Account account0_ where account0_.id=? for update
    * 加了for update  事务提交前不能有事务再更新此条记录
    * */
    @Test
    public void testPessimisticLock() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account a = (Account)session.load(Account.class, 1, LockMode.UPGRADE);
        int balance = a.getBalance();
        //do some caculation
        balance = balance - 10;
        a.setBalance(balance);
        session.getTransaction().commit();
        session.close();
    }
}
