package com.itheima.service.impl;

import com.itheima.dao.LogDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.LogService;
import com.itheima.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserDao userDao;
    public List<User> selectAll() {
        return userDao.selectAll();
    }
@Autowired
private LogService logService;

    public String selectByName(String name) {
        User user = userDao.SelectByName(name);
        System.out.println(user);
        return "花火";
   }

    public User selectByLevel(int level) {
        return userDao.SelectByLevel(level);
    }

    public void save() {
        System.out.println("save...");
    }

    public void Transfer(String out, String in, int money) throws IOException {
    try{  userDao.outMoney(out,money);
        userDao.inMoney(in,money);
   }
    finally {
        logService.log(in,out,money);
    }

    }



}
