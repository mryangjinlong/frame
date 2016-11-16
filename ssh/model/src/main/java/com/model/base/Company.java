package com.model.base;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;

/**
 * 联合主键展示
 */
@Entity
@IdClass(CompanyKey.class)
public class Company {

    private int id;
    private String name;
    private  String notes;
    private BigDecimal golds;
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getGolds() {
        return golds;
    }

    public void setGolds(BigDecimal golds) {
        this.golds = golds;
    }
}
