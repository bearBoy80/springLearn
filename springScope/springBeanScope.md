# 自定义scope 实现
1、实现org.springframework.beans.factory.config.Scope接口
2、注册 Scope
  - org.springframework.beans.factory.config.ConfigurableBeanFactory#registerScope
  - 配置
    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
    <property name="scopes">
    <map>
    <entry key="...">
    </entry>
    </map>
    </property>
    </bean>