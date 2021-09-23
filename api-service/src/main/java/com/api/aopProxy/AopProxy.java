package com.api.aopProxy;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by 李显琪 on 2020/10/1.
 */
@Component
@Aspect
public class AopProxy {

    @Pointcut("execution(public * com.api.service..*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void log(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("" + joinPoint.getSignature().getName() + "运行---@Before:参数列表是：{" + JSON.toJSONString(Arrays.asList(args)) + "}");
    }

}
