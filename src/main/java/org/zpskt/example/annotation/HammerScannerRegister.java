package org.zpskt.example.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hammer扫描注册类
 */
public class HammerScannerRegister implements BeanFactoryAware, ImportBeanDefinitionRegistrar {

    /**
     * 所要扫描的所有包名
     */
    private static List<String> basePackages = new ArrayList<>();

    /**
     * Hammer全局配置ID
     */
    private static String configurationId;

    /**
     * bean工厂
     */
    private BeanFactory beanFactory;

    /**
     * 根据HammerScan注解获取扫描包全路径
     * @param importingClassMetadata 导入类元信息
     * @param registry  Bean定义注册中心
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //HammerScan.class.getName()：获取所有使用HammerScan注解的全类名
        //getAnnotationAttributes（）：通常对注解的解析之后,需要对注解的信息进行对象存储转换
        //这一步的作用就是把注解的数据放到map中
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(HammerScan.class.getName());
        // 先扫描 @HammerScan 注解中定义的包
        if (annotationAttributes != null) {
            //如果注解的属性不为null，那么取出注解属性
            //annoAttrs是一个LinkedHashMap
            AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(annotationAttributes);
            for (String pkg : annoAttrs.getStringArray("value")) {
                if (StringUtils.hasText(pkg)) {
                    //如果value有值，那么放到BasePackages（扫描包路径）里面
                    basePackages.add(pkg);
                }
            }

            for (String pkg : annoAttrs.getStringArray("basePackages")) {
                if (StringUtils.hasText(pkg)) {
                    basePackages.add(pkg);
                }
            }

            for (Class<?> clazz : annoAttrs.getClassArray("basePackageClasses")) {
                //ClassUtils.getPackageName获取一个类的包名
                basePackages.add(ClassUtils.getPackageName(clazz));
            }

            //配置Id
            configurationId = annoAttrs.getString("configuration");
        }

        // 若 @HammerScan 注解未定义扫描包名，则扫描整个项目
        if (basePackages.isEmpty()) {
            //@AutoConfigurationPackage是在springboot启动类注解@SpringBootApplication下的@EnableAutoConfiguration下
            // @AutoConfigurationPackage作用是指定springboot扫描包，默认就是扫描启动类同包下的类
            basePackages.addAll(AutoConfigurationPackages.get(beanFactory));
        }
    }

    /**
     * 设置Bean工厂
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取所要扫描的所有包名
     *
     * @return 包名字符串列表
     */
    public static List<String> getBasePackages() {
        return basePackages;
    }

    /**
     * 获取HammerId全局配置ID
     *
     * @return  Hammer全局配置ID字符串
     */
    public static String getConfigurationId() {
        return configurationId;
    }
}

