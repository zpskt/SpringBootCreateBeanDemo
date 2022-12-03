package org.zpskt.example;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.zpskt.example.config.HammerConfiguration;
import org.zpskt.example.properties.HammerConfigurationProperties;
import org.zpskt.example.util.StringUtils;

public class HammerBeanRegister implements ResourceLoaderAware, BeanPostProcessor {
    /**
     * 虽然是final不可以修改，但是可以根据这个获取Bean工厂等，这些工厂对象可以创建bean
     */
    private final ConfigurableApplicationContext applicationContext;
    /**
     * 资源加载
     */
    private ResourceLoader resourceLoader;

    /**
     * Forest的配置的properties
     * 这个和SpringForestProperties有什么区别？？ TODO
     */
    private HammerConfigurationProperties hammerConfigurationProperties;
    public HammerBeanRegister(
            ConfigurableApplicationContext applicationContext,
            HammerConfigurationProperties hammerConfigurationProperties) {
        this.applicationContext = applicationContext;
        this.hammerConfigurationProperties = hammerConfigurationProperties;
    }

    /**
     * 获取资源加载
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    /**
     * 注册Forest配置对象，返回这容器中的这个Bean
     * @return
     */
    public HammerConfiguration registerHammerConfiguration() {

        //构建bean配置元数据builder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(HammerConfiguration.class);


        //获取beanId
        String id = hammerConfigurationProperties.getBeanId();
        if (StringUtils.isBlank(id)) {
            id = "hammerConfiguration";
        }

        //根据property中的值注册bean。
        beanDefinitionBuilder
                .addPropertyValue("beanName", hammerConfigurationProperties.getBeanName())
                .addPropertyValue("schoolName", hammerConfigurationProperties.getSchoolName())
                .setLazyInit(false)
                .setFactoryMethod("configuration")
                .addConstructorArgValue(id);

        //根据map值，看bean配置元数据
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();


        //获取bean工厂后，开始根据beanDefinition注册Bean
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(id, beanDefinition);

        //根据id和类型获取到Spring已经初始化的Bean
        HammerConfiguration configuration = applicationContext.getBean(id, HammerConfiguration.class);

        return configuration;
    }

}
