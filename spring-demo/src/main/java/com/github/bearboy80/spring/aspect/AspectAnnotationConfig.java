package com.github.bearboy80.spring.aspect;

import com.github.bearboy80.spring.service.DefaultEchoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectAnnotationConfig {
    //定义切入点
    @Pointcut("execution(public * *(..))  && args(args,..)")
    public void anyPublicMethod(String args) {
    }

    //前置拦截
    @Before("anyPublicMethod(args)")
    public void beforeAnyPublicMethod(JoinPoint joinPoint, Object args) {
        Object target = joinPoint.getTarget();
        if (target.getClass().isAssignableFrom(DefaultEchoService.class)) {
            System.out.println("AspectAnnotationConfig.before  DefaultEchoService.class");
        }
        System.out.println("AspectAnnotationConfig.before  args " + args);
    }
}
