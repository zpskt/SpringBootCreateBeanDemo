package org.zpskt.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.zpskt.example.annotation.HammerScannerRegister;
import org.zpskt.example.properties.HammerConfigurationProperties;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties({HammerConfigurationProperties.class})//设置当前类用的property
@Import({HammerScannerRegister.class}) //注入类到Bean中
public class HammerAutoConfiguration {
    @Resource
    private ConfigurableApplicationContext applicationContext;

    @Bean
    @DependsOn("forestBeanProcessor")//设置依赖组件，组件会有限注入到IOC容器中
    @ConditionalOnMissingBean
    public HammerBeanRegister hammerBeanRegister(HammerConfigurationProperties hammerConfigurationProperties) {
        HammerBeanRegister register = new HammerBeanRegister(
                applicationContext,
                hammerConfigurationProperties);
        register.registerHammerConfiguration();
        return register;
    }
}
