package com.model.inheritanceMapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/8/31.
 */
@Entity
@DiscriminatorValue("1")
public class Driver extends Human {
    private int level;  //级别
    private int length; //驾驶长度


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
