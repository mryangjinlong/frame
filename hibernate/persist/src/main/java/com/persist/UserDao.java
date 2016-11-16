package com.persist;

import com.model.base.User;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Created by Administrator on 2016/8/23.
 */
public interface UserDao {
    public void saveUser(User user);
}
