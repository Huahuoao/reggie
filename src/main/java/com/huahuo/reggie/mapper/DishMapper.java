package com.huahuo.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface DishMapper extends  BaseMapper<Dish> {
}
