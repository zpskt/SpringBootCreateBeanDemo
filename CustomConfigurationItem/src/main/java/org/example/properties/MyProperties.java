package org.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.info") //配置前缀
public class MyProperties {
    private String name;
    private Integer age;

}
