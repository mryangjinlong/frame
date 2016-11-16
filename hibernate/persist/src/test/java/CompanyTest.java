import com.model.base.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/8/26.
 */
public class CompanyTest {
    @Test
    public void testCompany(){
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        Company company = new Company();
        company.setId(1212555);
        company.setName("sssss");
        company.setGolds(new BigDecimal(12));

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(company);
        transaction.commit();
        session.close();
        sf.close();

        return;
    }
}
