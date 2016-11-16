package com.persist;

import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/14.
 */
public class DaoImplSupport {

    @Resource
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void save(Object o){
        this.hibernateTemplate.save(o);
    }

}
