# Spring Bean命名规则
每个 Bean 拥有一个或多个标识符（identifiers），这些标识符在 Bean 所在的容器必须是唯一的。通常，一个 Bean 仅有一个标识符，如果需要额外的，可考虑使用别名（Alias）来扩充。
在基于 XML 的配置元信息中，开发人员可用 id 或者 name 属性来规定 Bean 的 标识符。通常Bean 的 标识符由字母组成，允许出现特殊字符。如果要想引入 Bean 的别名的话，可在name 属性使用半角逗号（“,”）或分号（“;”) 来间隔。
Bean 的 id 或 name 属性并非必须制定，如果留空的话，容器会为 Bean 自动生成一个唯一的名称。Bean 的命名尽管没有限制，不过官方建议采用驼峰的方式，更符合 Java 的命名约定。
# Spring内置生成Bean名称
Spring框架默认提供BeanNameGenerator接口来生成bean名称，默认实现方式有两种
1、DefaultBeanNameGenerator 默认通用BeanNameGenerator实现
```java 
    public String generateBeanName(BeanDefinition definition, 
    BeanDefinitionRegistry registry) {
        //调用生成beanName工具类
		return BeanDefinitionReaderUtils.generateBeanName(definition, registry);
	}
``` 
BeanDefinitionReaderUtils.generateBeanName具体实现
``` JAVA
	public static String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry registry)
			throws BeanDefinitionStoreException {

		return generateBeanName(beanDefinition, registry, false);
	}
	public static String generateBeanName(
			BeanDefinition definition, BeanDefinitionRegistry registry, boolean isInnerBean)
			throws BeanDefinitionStoreException {
        //获取class名称
		String generatedBeanName = definition.getBeanClassName();
        //如果为空,从父类definition查找，如果查找到用父类+$child作为class名称
        //父类definition查找不到，判断FactoryBeanName是否为空,不为空用FactoryBeanName+$created
		if (generatedBeanName == null) {
			if (definition.getParentName() != null) {
				generatedBeanName = definition.getParentName() + "$child";
			}
			else if (definition.getFactoryBeanName() != null) {
				generatedBeanName = definition.getFactoryBeanName() + "$created";
			}
		}
        //判断class名称是否为空,为空报错
		if (!StringUtils.hasText(generatedBeanName)) {
			throw new BeanDefinitionStoreException("Unnamed bean definition specifies neither " +
					"'class' nor 'parent' nor 'factory-bean' - can't generate bean name");
		}

		String id = generatedBeanName;
        //判断否是内部类
		if (isInnerBean) {
			// Inner bean: generate identity hashcode suffix.
			id = generatedBeanName + GENERATED_BEAN_NAME_SEPARATOR + ObjectUtils.getIdentityHexString(definition);
		}
		else {
			// Top-level bean: use plain class name with unique suffix if necessary.
			return uniqueBeanName(generatedBeanName, registry);
		}
		return id;
	}
    //生成beanName
	public static String uniqueBeanName(String beanName, BeanDefinitionRegistry registry) {
		String id = beanName;
		int counter = -1;

		//判断bean id是否存在，如果存在一直循环至到不存在
		while (counter == -1 || registry.containsBeanDefinition(id)) {
			counter++;
			id = beanName + GENERATED_BEAN_NAME_SEPARATOR + counter;
		}
		return id;
	}    
```

2、AnnotationBeanNameGenerator基于注解扫描的BeanNameGenerator实现
```java
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        
		if (definition instanceof AnnotatedBeanDefinition) {
            //通过注解查找属性Value的值
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}
		// Fallback: generate a unique default bean name.
        //用class类名作为beanName
		return buildDefaultBeanName(definition, registry);
	}
```
针对@Bean注册的bean,如果未只能name属性,默认beanName为方法名
```JAVA
ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForBeanMethod方法
		//关键代码
		// Consider name and any aliases
		List<String> names = new ArrayList<>(Arrays.asList(bean.getStringArray("name")));
		String beanName = (!names.isEmpty() ? names.remove(0) : methodName);
```