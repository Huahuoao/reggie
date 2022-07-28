package com.huahuo.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> ExceptionHandler(SQLIntegrityConstraintViolationException ex)
    {
        log.error(ex.getMessage());
        //判断元素是否在动态数组中
       if(ex.getMessage().contains("Duplicate entry"))
       {
           String[] split = ex.getMessage().split(" ");
           String msg = "用户 "+split[2] + "已存在";
           return  R.error(msg);
       }
      return R.error("未知错误");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> CustomerExceptionHandler(CustomException ex)
    {
        return R.error(ex.getMessage());
    }
}
