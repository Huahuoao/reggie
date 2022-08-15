package com.firework.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public void doException(Exception ex)
    {
        System.out.println("嘿嘿，出异常了吧！");
    }
}
