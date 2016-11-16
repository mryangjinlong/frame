package com.service;

import com.model.base.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/9/12.
 */
public class UserServiceImpl implements UserService {

    /*
    * @Transactional 用来配置事务
    *      一. propagation  表示事务的传播方式
    *           REQUIRED(0),    这个是默认值最常用的    一个方法被调用时，如果没有事务就创建新的事务， 如果有就使用旧的事务
                SUPPORTS(1),    如果有旧事务 就用旧事务  如果没有 就没有事务
                MANDATORY(2),    表示调用当前方法前必须已经有一个事务存在    如果没有就会抛异常
                REQUIRES_NEW(3),    创建新事务 ， 挂起旧事务      各自回滚互不影响
                NOT_SUPPORTED(4),   如果方法调用前有事务 .那么旧事务将被挂起    执行完方法后此事务再继续
                NEVER(5),           方法调用前必须没有事务。 如果有事务那么抛异常  与MANDATORY相反
                NESTED(6);  方法调用前如果已经有一个事务  那么会创建一个新的事务 ， 相当于在一个事务中内嵌了一个事务。
                内部的事务回滚时不会影响外面的事务，只会回滚到当前方法前（保存点处）
            二.  isolation  隔离级别
            三. readOnly  用于提高性能  如果确认这个函数保有读操作 设置成true 那么spring会使用readOnly的connection    readOnly的connection比可读可写的执行效率高
            四. timeout 设置超时时间  超过时间抛异常
            五. rollbackFor 在什么情况下回滚  默认RuntimeException时回滚    设置抛出什么异常时回滚
            六. norollbackFor  在什么情况下不回滚
    * */
    @Transactional(propagation = Propagation.REQUIRED , isolation = Isolation.DEFAULT ,
            readOnly = false , timeout = 10 ,rollbackFor = Throwable.class)
    @Override
    public void saveUser(User user) {
        System.out.println("adsfsdf");
    }
}
