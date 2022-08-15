package com.itheima.service.impl;

import com.itheima.dao.LogDao;
import com.itheima.dao.UserDao;
import com.itheima.service.LogService;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    public void log(String in, String out, int money) {
         String info = out + " 转账给 " + in+" "+" 金额： " +money+" 元 ";
         logDao .log(info);
    }

    public void truncate() {
        logDao.truncate();
        System.out.println("日志已全部清除！");
    }
}
