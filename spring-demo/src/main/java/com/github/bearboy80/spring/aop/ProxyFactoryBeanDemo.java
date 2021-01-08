package com.github.bearboy80.spring.aop;

import com.github.bearboy80.spring.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml 配置spring aop
 * 同一个aspect中 advice 执行顺序 ：around  >  Before > After >  | -AfterReturning
 *                                                            | -AfterThrowing
 */
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-factorybean.xml");
        EchoService echoService = context.getBean("echoServiceFactoryBean", EchoService.class);
        echoService.echo("echoServiceFactoryBean");
        context.close();
    }
}
