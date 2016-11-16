package com.model.oneToMangeDouble;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/29.
 */
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    private PhoneHander phoneHander;


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

    public PhoneHander getPhoneHander() {
        return phoneHander;
    }

    public void setPhoneHander(PhoneHander phoneHander) {
        this.phoneHander = phoneHander;
    }
}
