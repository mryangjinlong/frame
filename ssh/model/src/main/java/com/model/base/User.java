package com.model.base;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/23.
 */
@Entity
public class User {

    //注解最好是放在方法上。 不要放成成员变量了。
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //默认的ID生成策略是Auto  mysql中用identity  这个生成的字段自动递增
    private int id;

    /*
    此注解指定属性对应的字段  updatable 默认true 改成false 那么在session.update时不会更新此字段 很少用这种方式
    * */
    @Column(name = "loginName")
    private String name;


    @Transient //此注解使属性不对应字段
    private boolean sex;


    //此注解可以配置数据库中日期的记录方式  如只记日期 或只记时间  或日期时间都记 用的不多
    @Temporal(TemporalType.DATE)
    private Date birthday;

//    @Enumerated(EnumType.STRING) //枚举类型映射成字符串
    @Enumerated(EnumType.ORDINAL) //枚举类型映射成integer
    private JobTitle type;

    @Column(updatable = false)
    private BigDecimal golds;

    public BigDecimal getGolds() {
        return golds;
    }

    public void setGolds(BigDecimal golds) {
        this.golds = golds;
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



    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public JobTitle getType() {
        return type;
    }

    public void setType(JobTitle type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", type=" + type +
                '}';
    }
}
