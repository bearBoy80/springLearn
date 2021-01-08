# SpringBean实例化方式
## 通过构造器（配置元信息：XML、Java 注解和 Java API ）
* XML 
```xml
    <bean id="user" class="org.github.bearBoy80.spring.bean.domain.User">
     <constructor-arg name="id" value="1"/>
     <constructor-arg name="name" value="bearBoy80"/>
    </bean>
```	
```java
public class MovieRecommender {

    private final CustomerPreferenceDao customerPreferenceDao;

    @Autowired
    public MovieRecommender(CustomerPreferenceDao customerPreferenceDao) {
        this.customerPreferenceDao = customerPreferenceDao;
    }
}
```
## 通过静态工厂方法（配置元信息：XML 和 Java API ）
   <bean id="user-by-instance-method" factory-bean="userFactory" factory-method="createUser"/>
## 通过 Bean 工厂方法（配置元信息：XML和 Java API ）
    <bean id="userFactory" class="org.github.bearBoy80.spring.bean.factory.DefaultUserFactory"/>
## 通过 FactoryBean（配置元信息：XML、Java 注解和 Java API ）
   <bean id="user-by-factory-bean" class="org.github.bearBoy80.spring.bean.factory.UserFactoryBean" />
## 通过 ServiceLoaderFactoryBean（配置元信息：XML、Java 注解和 Java API ）
## 通过 AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)
## 通过 BeanDefinitionRegistry#registerBeanDefinition(String,BeanDefinition)