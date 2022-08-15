package com.itheima.service;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> selectAll();
    String selectByName(String name);
    User selectByLevel(int level);
    void save();
    @Transactional(rollbackFor = {IOException.class})
    void Transfer(String out,String in,int money) throws IOException;

}
