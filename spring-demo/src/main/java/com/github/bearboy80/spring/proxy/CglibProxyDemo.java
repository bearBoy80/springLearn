package com.github.bearboy80.spring.proxy;

import com.github.bearboy80.spring.service.DefaultEchoService;
import com.github.bearboy80.spring.service.EchoService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 通过 cglib 进行 DefaultEchoService 增强
 */
public class CglibProxyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DefaultEchoService.class);
        enhancer.setCallback(new MethodInterceptor() {

            public Object intercept(Object source, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                // Source -> CGLIB 子类
                // 目标类  -> DefaultEchoService
                //错误示例 会报错
                //Object result = method.invoke(source,args);
                // 正确的方法调用
                Object result = methodProxy.invokeSuper(source, args);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + " ms.");
                return result;
            }
        });
        EchoService echoService = (EchoService) enhancer.create();
        echoService.echo("enhancer Cglib ");
    }
}
