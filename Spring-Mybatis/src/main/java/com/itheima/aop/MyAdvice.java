//package com.itheima.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class MyAdvice {
//    //匹配业务层所有方法
//    @Pointcut("execution(* com.itheima.*.*Service.*(..))")
//    private void servicePt(){}
//
//    @Around("MyAdvice.servicePt()")
//   public void runSpeed(ProceedingJoinPoint pjp) throws Throwable
//    {
//        //前
//
//        Signature signature = pjp.getSignature();
//        String Type = String.valueOf(signature.getDeclaringType());
//        String name = signature.getName();
//        long start = System.currentTimeMillis();
//        for(int i=0;i<10000;i++)
//        {
//            pjp.proceed();
//
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("万次执行:"+Type+"."+name+"---->"+(end-start)+" ms");
//
//    }
//
//
//}
