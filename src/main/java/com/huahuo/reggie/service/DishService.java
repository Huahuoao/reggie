package com.huahuo.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.reggie.dto.DishDto;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public  DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
}
