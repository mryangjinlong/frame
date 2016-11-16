package com.model.component_mapping;

import javax.persistence.*;
import java.util.Date;

/**
 * 此类与personCardMsg来描述组合映射
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    private Date birthday;

    @Embedded //指的是personCardMsg 作为组件嵌入到这个表中
    private PersonCardMsg personCardMsg;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public PersonCardMsg getPersonCardMsg() {
        return personCardMsg;
    }

    public void setPersonCardMsg(PersonCardMsg personCardMsg) {
        this.personCardMsg = personCardMsg;
    }
}
