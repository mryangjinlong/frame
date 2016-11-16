package com.model.collections_mapping;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/31.
 */
@Entity
public class ClassF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private ClassE classE;


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

    public ClassE getClassE() {
        return classE;
    }

    public void setClassE(ClassE classE) {
        this.classE = classE;
    }
}
