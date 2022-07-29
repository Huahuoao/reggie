package com.huahuo.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.reggie.common.CustomException;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.Dish;
import com.huahuo.reggie.entity.Setmeal;
import com.huahuo.reggie.entity.SetmealDish;
import com.huahuo.reggie.mapper.CategoryMapper;
import com.huahuo.reggie.mapper.SetMealDishMapper;
import com.huahuo.reggie.service.CategoryService;
import com.huahuo.reggie.service.DishService;
import com.huahuo.reggie.service.SetMealDishService;
import com.huahuo.reggie.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetMealDishServiceImpl extends ServiceImpl<SetMealDishMapper, SetmealDish> implements SetMealDishService {

    }


