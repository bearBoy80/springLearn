package com.github.bearboy80.spring.aop;

import com.github.bearboy80.spring.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml 配置spring aop
 * 同一个aspect中 advice 执行顺序 ：around  >  Before > After >  | -AfterReturning
 *                                                           | -AfterThrowing
 */
public class XmlAopDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop.xml");
        EchoService echoService = (EchoService) context.getBean("echoService");
        echoService.echo("XmlAopDemo");
        context.close();
    }
}
