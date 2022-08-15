package com.itheima.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface LogService {
    //目的是实现无论转账是否成功都要记录日志
    @Transactional(propagation = Propagation.REQUIRES_NEW) //表示log是一个独立的事务 不和他们一起处理 免得事务绑定一起回滚
    void log(String in,String out,int money);
    @Transactional
    void truncate();
}
