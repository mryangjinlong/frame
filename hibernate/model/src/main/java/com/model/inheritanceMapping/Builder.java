package com.model.inheritanceMapping;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/8/31.
 */
@Entity
@DiscriminatorValue("2")
public class Builder extends  Human{

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
