package org.example.demo;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * 扫描类
 */
public class MetaBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    /**
     * 仿照原生的构造方法全部来一遍
     * @param registry Bean定义注册中心
     * @param useDefaultFilters 是否使用用户默认的filter
     * @param environment 整个spring应用运行时的环境信息：类似于application.yml 里面就是。
     * @param resourceLoader 对资源封装的加载器：可以从文件中、网络中、流中加载资源。
     *                       资源加载器是个接口，默认从类路径下加载。
     *                       通过getResource方法从字符串中查找路径。
     *                       可以从容器中获取ResourceLoader，调用getResource方法。
     */
    public MetaBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,
                                     Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
        registerFilters();
    }

    /**
     * 注册过滤器  在此类的构造函数中引用
     */
    protected void registerFilters() {
        //注册一个AnnotationTypeFilter，确保过滤获取所有@Meta注解的类
        addIncludeFilter(new AnnotationTypeFilter(Meta.class));
    }
}

