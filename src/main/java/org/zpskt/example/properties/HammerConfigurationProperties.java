package org.zpskt.example.properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * 自动配置类
 */
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConfigurationProperties(prefix = "hammer", ignoreUnknownFields = true)
public class HammerConfigurationProperties {
}
