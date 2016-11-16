package com.model.tree;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/31.
 */
@Entity
public class Org {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
//    private int parentId;   //如果为空说明是根结点
    @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL)
    private Set<Org> children = new HashSet<Org>();
    @ManyToOne
//    @JoinColumn(name = "parent_id")
    private Org parent;

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


    public Set<Org> getChildren() {
        return children;
    }

    public void setChildren(Set<Org> children) {
        this.children = children;
    }

    public Org getParent() {
        return parent;
    }

    public void setParent(Org parent) {
        this.parent = parent;
    }

}
