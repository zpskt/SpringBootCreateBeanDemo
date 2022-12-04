package org.example.demo;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class MetaBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public MetaBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,
                                     Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
        registerFilters();
    }

    protected void registerFilters() {
        //注册一个AnnotationTypeFilter，确保过滤获取所有@Meta注解的类
        addIncludeFilter(new AnnotationTypeFilter(Meta.class));
    }
}

