package com.model.inheritanceMapping;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/31.
 */
@Entity
//继承映射， 继承映射的策略有三种
// InheritanceType.JOINED         这种策略要需要设定继承策略    一般情况下用的最多
//InheritanceType.SINGLE_TABLE     所有类变成一张表，如此设定为此策略 那么需要加个区分来标记当前记录是哪一种类型  @DiscriminatorColumn     用的第二多
//InheritanceType.TABLE_PER_CLASS   每个类生成一张表 human也会生成一张表。 此时用的id生成策略是  table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type" ,discriminatorType = DiscriminatorType.INTEGER)  //描述区分器的字段名与字段类型
@DiscriminatorValue("0")    //描述当前类区分器的值   就是 type = 0
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

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
