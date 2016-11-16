package com.model.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/26.
 */
//company的主键类   作为主键类需要实现序列化接口   并且要重写hashcode与 equal方法
public class CompanyKey implements Serializable {
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
