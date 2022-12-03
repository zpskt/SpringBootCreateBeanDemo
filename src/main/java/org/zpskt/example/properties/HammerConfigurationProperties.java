package org.zpskt.example.properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * 自动配置类
 * ignoreUnknownFields是用来告诉SpringBoot在有属性不能匹配到声明的域时抛出异常
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConfigurationProperties(prefix = "hammer", ignoreUnknownFields = true)
public class HammerConfigurationProperties {

    private String beanId;

    private String beanName;

    private int beanAge;

    private String schoolName;

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public int getBeanAge() {
        return beanAge;
    }

    public void setBeanAge(int beanAge) {
        this.beanAge = beanAge;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
