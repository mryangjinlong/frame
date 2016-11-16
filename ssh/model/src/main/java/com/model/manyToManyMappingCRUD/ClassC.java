package com.model.manyToManyMappingCRUD;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * classC classD 描述 关联关系下的CURD
 */
@Entity
public class ClassC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //fetch 表示读取的时候是否把关联的对象也读出来  有两个取值  oneToMany 默认lazy    manyToOne 默认eager   一般用默认的情况就可以了
    // FetchType.EAGER   渴望的，马上读取出来   在双向关联的情况下最好是只有一边设eager，这样就不会出现 A取B ， B再去取A的情况
    // FetchType.LAZY    懒惰的，不马上读出来，如果对象在persistent状态 那么使用到这个对象时会再取出来
    @OneToMany(mappedBy = "classC" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<ClassD> set = new HashSet<ClassD>();


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

    public Set<ClassD> getSet() {
        return set;
    }

    public void setSet(Set<ClassD> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "ClassC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
