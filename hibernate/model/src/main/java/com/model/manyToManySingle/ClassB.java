package com.model.manyToManySingle;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/29.
 */
@Entity
public class ClassB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;

//    private Set<ClassA> classbs = new HashSet<ClassA>();

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

//    public Set<ClassA> getClassbs() {
//        return classbs;
//    }
//
//    public void setClassbs(Set<ClassA> classbs) {
//        this.classbs = classbs;
//    }
}
