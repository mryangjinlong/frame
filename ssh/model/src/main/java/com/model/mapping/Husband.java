package com.model.mapping;

import javax.persistence.*;

/**
 * one to one
 * 这个类与wife类描述了  双向外键关联
 */
@Entity
public class Husband {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne
//  @JoinColumn(name = "wife_id")    指定生成的外键的字段名  很少用
    private Wife wife;   //如果只有一边有引用则是单向外键关联  只有husband里面有wife的关联 ， wife里面没有husband的关联


    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

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
