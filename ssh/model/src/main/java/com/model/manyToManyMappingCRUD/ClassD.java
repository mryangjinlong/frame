package com.model.manyToManyMappingCRUD;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/8/30.
 */
@Entity
public class ClassD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    /*
    * cascade为级联的意思，不设置时不产生任何级联 有几个取值
    *  CascadeType.ALL   表示所有的持久化操作都会关联到classC这个对象    常用
    *  CascadeType.MERGE     合并时
    *  CascadeType.PERSIST   保存时        常用
    *  CascadeType.REFRESH   刷新时
    *  CascadeType.REMOVE    删除时        常用
    * */
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private ClassC classC;

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

    public ClassC getClassC() {
        return classC;
    }

    public void setClassC(ClassC classC) {
        this.classC = classC;
    }

    @Override
    public String toString() {
        return "ClassD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
