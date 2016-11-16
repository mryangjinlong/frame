package com.model.cache;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Cache 注解让这个对象使用二级缓存
 * 其中 useage 有三种策略  1.CacheConcurrencyStrategy.NONE
 *                         2.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE     非严格读写
 *                         3.CacheConcurrencyStrategy.READ_ONLY           常用  只读
 *                         4.CacheConcurrencyStrategy.READ_WRITE          常用  可读写
 *                         5.CacheConcurrencyStrategy.TRANSACTIONAL
 *   region 对应ehcache配置文件中策略的名字     默认是defaultCache
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL , region = "" )
public class ClassG {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
