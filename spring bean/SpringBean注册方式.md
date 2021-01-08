# SpringBean注册方式
## BeanDefinition 注册
1、 XML 配置元信息
 * <bean name=”...” ... />

2、 Java 注解配置元信息
* @Bean
* @Component
* @Import

3、 Java API 配置元信息
* 命名方式：BeanDefinitionRegistry#registerBeanDefinition(String,BeanDefinition)
* 非命名方式：
	BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition,BeanDefinitionRegistry)
* 配置类方式：AnnotatedBeanDefinitionReader#register(Class...)
## 外部单例对象注册
Java API 配置元信息
* SingletonBeanRegistry#registerSingleton
