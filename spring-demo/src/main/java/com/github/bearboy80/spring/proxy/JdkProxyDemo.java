package com.github.bearboy80.spring.proxy;

import com.github.bearboy80.spring.service.DefaultEchoService;
import com.github.bearboy80.spring.service.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过jdk 动态代理
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final DefaultEchoService defaultEchoService = new DefaultEchoService();
        EchoService proxy = (EchoService) Proxy.newProxyInstance(classLoader,
                new Class[]{EchoService.class}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置拦截
                        long startTime = System.currentTimeMillis();
                        method.invoke(defaultEchoService, args);
                        //后置拦截
                        long endTime = System.currentTimeMillis();
                        System.out.println(method.getName() + " costTime :" + (endTime - startTime) + " ms");
                        if (EchoService.class.isAssignableFrom(proxy.getClass())) {
                            System.out.println("jdk 代理的类 是 EchoService 的子类");
                        }
                        return null;
                    }
                });
        proxy.echo("hello proxy");
        EchoServiceHandle handle = new EchoServiceHandle(new DefaultEchoService());
        EchoService serviceProxy = (EchoService) handle.getProxy();
        serviceProxy.echo("EchoServiceHandle invoke echo");
    }

}

/**
 * 继承 InvocationHandler，实现EchoService代理
 */
class EchoServiceHandle implements InvocationHandler {
    private Object target;

    public EchoServiceHandle(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" before invoke method :" + method.getName());
        Object retVal = method.invoke(target, args);
        System.out.println(" after invoke method :" + method.getName());
        return retVal;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                new Class[]{EchoService.class}, this);
    }
}