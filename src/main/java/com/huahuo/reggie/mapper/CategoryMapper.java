package com.huahuo.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CategoryMapper extends MPJBaseMapper<Category> {
}
