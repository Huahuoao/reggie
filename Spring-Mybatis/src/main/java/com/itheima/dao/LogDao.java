package com.itheima.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Update;

public interface LogDao {

    @ResultMap("userMap")
    @Insert("insert into user_log(info,createDate) values(#{info},now())")
    void log(String info);

    @Update("truncate table user_log ")
    void truncate();

}
