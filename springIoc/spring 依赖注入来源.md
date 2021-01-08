# spring 依赖查找来源
## Spring BeanDefinition
1、xml 定义bean
2、spring 注解bean
3、BeanDefinitionBuilder
### Spring 內建 BeanDefintion

|Bean 名称| Bean 实例 |使用场景
|:------- |------|----|
|org.springframework.context.annotation.internalConfigurationAnnotationProcessor|ConfigurationClassPostProcessor对象|处理 Spring 配置类
org.springframework.context.annotation.internalAutowiredAnnotationProcessor|AutowiredAnnotationBeanPostProcessor 对象|处理 @Autowired 以及@Value 注解
|org.springframework.context.annotation.internalCommonAnnotationProcessor|CommonAnnotationBeanPostProcessor 对象|（条件激活）处理 JSR-250 注解，如 @PostConstruct 等
|org.springframework.context.event.internalEventListenerProcessor|EventListenerMethodProcessor对象|处理标注 @EventListener 的Spring 事件监听方法
### Spring 內建单例对象
|Bean 名称| Bean 实例 |使用场景
|:------- |------|----|
|environment|Environment 对象|外部化配置以及 Profiles
|systemProperties| java.util.Properties 对象 |Java 系统属性
|systemEnvironment |java.util.Map 对象 |操作系统环境变量
|messageSource| MessageSource 对象 |国际化文案
|lifecycleProcessor |LifecycleProcessor 对象 |Lifecycle Bean 处理器
|applicationEventMulticaster |ApplicationEventMulticaster 对象|Spring 事件广播器
## 单例对象 通过 GenericApplicationContext.registerBean 方法

# spring 依赖注入来源
## Spring BeanDefinition
## 单例对象 通过 GenericApplicationContext.registerBean 方法
spring 容器不会提供生命周期管理、无beanDefintion
## ResolvableDependency 
不是spring bean、spring 容器不会提供生命周期管理、无beanDefintion、无法依赖查找
## @Value 所标注的外部化配置