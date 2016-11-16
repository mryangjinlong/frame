package com.model.mapping;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/28.
 */
@Entity
public class Wife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //两边都有引用则是双向外键关联    建议只要有双向关联  mappedBy 一定设 mappedBy说明此关系由husband对象里面的wife主导
    @OneToOne(mappedBy = "wife")
    private Husband husband;


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


    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }
}
