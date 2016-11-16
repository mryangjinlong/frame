package com.model.collections_mapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/31.
 */
@Entity
public class ClassE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "classE" , cascade = CascadeType.ALL)
    @OrderBy("name desc ,id asc ")            //导出后按默认会按主键排序    指定排序后会按指定的字段排序
    private List<ClassF> list = new ArrayList<ClassF>();

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "classE" , cascade = CascadeType.ALL)
    @MapKey(name = "name")                    //指定map的key是哪个字段  这里指定的是classF的id字段
    private Map<Integer,ClassF> maps = new HashMap<Integer,ClassF>();


    public Map<Integer, ClassF> getMaps() {
        return maps;
    }

    public void setMaps(Map<Integer, ClassF> maps) {
        this.maps = maps;
    }

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

    public List<ClassF> getList() {
        return list;
    }

    public void setList(List<ClassF> list) {
        this.list = list;
    }
}
