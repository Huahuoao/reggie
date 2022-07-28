package com.huahuo.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.reggie.dto.DishDto;
import com.huahuo.reggie.dto.SetmealDto;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.DishFlavor;
import com.huahuo.reggie.entity.Setmeal;
import com.huahuo.reggie.mapper.CategoryMapper;
import com.huahuo.reggie.mapper.SetMealMapper;
import com.huahuo.reggie.service.CategoryService;
import com.huahuo.reggie.service.SetMealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper,Setmeal> implements SetMealService {


}
