package com.model.manyToOneMapping;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/29.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroupp(Group group) {
        this.group = group;
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
