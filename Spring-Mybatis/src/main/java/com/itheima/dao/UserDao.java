package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


public interface UserDao {
    @Results(id="userMap", value={
           @Result(column="user_name", property="name", jdbcType=JdbcType.VARCHAR)
   })       //这就是ResultMap
    @Select("select * from user")
    List<User> selectAll();
    @Select("select * from user where user_name = #{name} ")    //前面的填数据库里的 后面的填实体类的名字
    @ResultMap("userMap")
    User SelectByName(String name);
    User SelectByLevel(int level);

    @Update("update user set money = money + #{money} where user_name = #{name}")
    @ResultMap("userMap")
    void inMoney(@Param("name")String name,@Param("money")int money);

 @Update("update user set money = money - #{money} where user_name = #{name}")
   @ResultMap("userMap")
 //多个参数记得用@Param注解
   void outMoney(@Param("name")String name,@Param("money")int money);


}
