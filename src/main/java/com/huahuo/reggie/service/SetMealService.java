package com.huahuo.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.reggie.dto.SetmealDto;
import com.huahuo.reggie.entity.Setmeal;

import java.util.List;

public interface SetMealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}
