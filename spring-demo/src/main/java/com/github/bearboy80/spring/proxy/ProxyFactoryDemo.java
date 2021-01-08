package com.github.bearboy80.spring.proxy;

import com.github.bearboy80.spring.Interceptor.MethodBeforeInterceptor;
import com.github.bearboy80.spring.service.DefaultEchoService;
import com.github.bearboy80.spring.service.EchoHelloService;
import com.github.bearboy80.spring.service.EchoService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class ProxyFactoryDemo {
    public static void main(String[] args) {
        //创建代理(jdk 动态代理实现)
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        //设置前置拦截
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("DefaultEchoService 前置拦截....");
            }
        });
        EchoService proxy = (EchoService) proxyFactory.getProxy();
        System.out.println(proxy.getClass());
        proxy.echo("ProxyFactory 代理");
        //创建代理（cglib）
        EchoHelloService helloService = new EchoHelloService();
        ProxyFactory proxyHello = new ProxyFactory();
        proxyHello.setTarget(helloService);
        proxyHello.setProxyTargetClass(true);
        proxyHello.addAdvice(new MethodBeforeInterceptor());
        EchoHelloService helloService1 = (EchoHelloService) proxyHello.getProxy();
        System.out.println(helloService1.getClass());
        helloService1.sayHi("java world");
    }
}
