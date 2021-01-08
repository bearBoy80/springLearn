package com.github.bearboy80.spring.aop;

import com.github.bearboy80.spring.aspect.AspectAnnotationConfig;
import com.github.bearboy80.spring.service.DefaultEchoService;
import com.github.bearboy80.spring.service.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class AnnotationAopDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册beanDefinition 到 ioc 容器
        context.register(AnnotationAopDemo.class, AspectAnnotationConfig.class, DefaultEchoService.class);
        //ioc 容器刷新
        context.refresh();
        //获取bean实例
        context.getBean(EchoService.class).echo("AnnotationAopDemo.echo");
        context.getBean(AnnotationAopDemo.class).execute("execute.......");

        context.close();
    }

    public void execute(String message) {
        System.out.println("AnnotationAopDemo.execute...." + message);
    }
}
