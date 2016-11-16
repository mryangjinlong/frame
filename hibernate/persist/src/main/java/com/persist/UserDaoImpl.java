package com.persist;

import com.model.base.User;
import com.model.transaction1.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/8/31.
 */
@Repository("userDao")
public class UserDaoImpl extends DaoImplSupport implements UserDao{

    @Override
    public void saveUser(User user){
        save(user);
    }
}
