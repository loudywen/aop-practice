package com.devon.demo.springaoppractice;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

  @Around("@annotation(LogExecutionTime)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    final long      start           = System.currentTimeMillis();
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method          method           = methodSignature.getMethod();
    LogExecutionTime logExecutionTime = method.getAnnotation(LogExecutionTime.class);
    String temp = logExecutionTime.name();

    final Object    proceed         = joinPoint.proceed();

    final long executionTime = System.currentTimeMillis() - start;

    System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

    return proceed;
  }

}
