package com.model.oneToMangeDouble;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * phoneHander 与 phone 用于描述  一对多 或多对一的双向关联
 */
@Entity
public class PhoneHander {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "phoneHander")   //双向关联的时候 设定关系由Phone对象里面的phoneHander主导
    private Set<Phone> phones = new HashSet<Phone>();


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

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }
}
