package com.huahuo.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.reggie.common.CustomException;
import com.huahuo.reggie.dto.DishDto;
import com.huahuo.reggie.dto.SetmealDto;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.DishFlavor;
import com.huahuo.reggie.entity.Setmeal;
import com.huahuo.reggie.entity.SetmealDish;
import com.huahuo.reggie.mapper.CategoryMapper;
import com.huahuo.reggie.mapper.SetMealMapper;
import com.huahuo.reggie.service.CategoryService;
import com.huahuo.reggie.service.DishService;
import com.huahuo.reggie.service.SetMealDishService;
import com.huahuo.reggie.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper,Setmeal> implements SetMealService {
  @Autowired
  private SetMealDishService setMealDishService;
  @Autowired
private SetMealMapper setMealMapper;

    /**
     * 新增套餐
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
      for (SetmealDish setmealDish : setmealDishes) {
        setmealDish.setSetmealId(setmealDto.getId());
      }
      setMealDishService.saveBatch(setmealDishes);
    }


@Override
@Transactional
public void removeWithDish(List<Long> ids) {
  //select count(*) from setmeal where id in (1,2,3) and status = 1
  //查询套餐状态，确定是否可用删除
  LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
  queryWrapper.in(Setmeal::getId,ids);
  queryWrapper.eq(Setmeal::getStatus,1);

  int count = this.count(queryWrapper);
  if(count > 0){
    //如果不能删除，抛出一个业务异常
    throw new CustomException("套餐正在售卖中，不能删除");
  }

  //如果可以删除，先删除套餐表中的数据---setmeal
  this.removeByIds(ids);

  //delete from setmeal_dish where setmeal_id in (1,2,3)
  LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
  lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
  //删除关系表中的数据----setmeal_dish
  setMealDishService.remove(lambdaQueryWrapper);
}
}
