package org.zpskt.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.zpskt.example.annotation.BindingVar;
import org.zpskt.example.config.HammerConfiguration;
import org.zpskt.example.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class HammerBeanProcessor implements InstantiationAwareBeanPostProcessor {

    public HammerBeanProcessor() {
    }

    private void processBean(Object bean, Class beanClass) {
        Method[] methods = beanClass.getDeclaredMethods();
        for (Method method : methods) {
            BindingVar annotation = method.getAnnotation(BindingVar.class);
            if (annotation == null) {
                continue;
            }
            String confId = annotation.configuration();
            HammerConfiguration configuration = null;
        }
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        processBean(bean, beanClass);
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
