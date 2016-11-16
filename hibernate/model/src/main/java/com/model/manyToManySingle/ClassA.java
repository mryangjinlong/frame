package com.model.manyToManySingle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/29.
 */
@Entity
public class ClassA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;

    @ManyToMany
    private Set<ClassB> classbs = new HashSet<ClassB>();

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

    public Set<ClassB> getClassbs() {
        return classbs;
    }

    public void setClassbs(Set<ClassB> classbs) {
        this.classbs = classbs;
    }
}
