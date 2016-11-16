package com.model.mapping;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/29.
 */
public class HousePk implements Serializable{
    private int id;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
