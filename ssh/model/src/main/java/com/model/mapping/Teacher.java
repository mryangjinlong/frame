package com.model.mapping;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/29.
 */
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Student student;


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
