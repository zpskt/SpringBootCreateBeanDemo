package org.example.demo;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 核心类
 * 流程就是：因为我们这里的目标是注册所有带 @Meta 注解的类，
 * 扫描所有的类，判断是否有@Meta注解，有则通过 registry 手动注册
 */

public class MetaAutoConfigureRegistrar
        implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    /**
     *
     */
    private ResourceLoader resourceLoader;

    private Environment environment;

    /**
     * 实现ResourceLoaderAware接口方法，可以获得外部资源xml、txt等。
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 实现EnvironmentAware接口方法，此属性可以获得application.properties中的属性值
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 核心！！注册bean
     * @param importingClassMetadata 注解元数据，多半是用来获取注解的属性
     * @param registry bean 定义注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //新建一个元注解扫描类，过滤出来要进行新建的Bean
        MetaBeanDefinitionScanner scanner =
                new MetaBeanDefinitionScanner(registry, true, this.environment, this.resourceLoader);
        //设置扫描包路径
        Set<String> packagesToScan = this.getPackagesToScan(importingClassMetadata);
        //根据扫描包路径，元注解扫描类去扫描对应的包，过滤出对应的类
        //找到以后就会注册到Bean容器中，当然我们的代码到这里就结束了，再向下的步骤就是Spring自己干了
        scanner.scan(packagesToScan.toArray(new String[]{}));
    }

    /**
     * 找到要扫描的包路径，set存储
     * @param metadata 注解元数据
     * @return
     */
    private Set<String> getPackagesToScan(AnnotationMetadata metadata) {
        //获取使用元数据扫描注解的类的注解属性值
        AnnotationAttributes attributes =
                AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(MetaComponentScan.class.getName()));
        //取出注解中的basePackages值
        String[] basePackages = attributes.getStringArray("basePackages");
        //取出注解中的basePackageClasses值
        Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
        //新建双向链表存储 包扫描路径
        Set<String> packagesToScan = new LinkedHashSet<>(Arrays.asList(basePackages));
        for (Class clz : basePackageClasses) {
            //添加扫描路径
            packagesToScan.add(ClassUtils.getPackageName(clz));
        }

        if (packagesToScan.isEmpty()) {
            //如果到这里包扫描路径为空，说明使用MetaComponentScan注解的时候没有设定值
            //那么就按照这个注解使用地的包路径作为扫描路径
            packagesToScan.add(ClassUtils.getPackageName(metadata.getClassName()));
        }
        // 返回包路径
        return packagesToScan;
    }
}
