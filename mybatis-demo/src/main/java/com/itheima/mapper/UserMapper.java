package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
     List<User> selectAll();

     @Select("select * from tb_User where id = #{id}")
     User selectById(int id);

    }

