package org.example.test;

import org.springframework.stereotype.Component;

@Component
public class NormalMeta {
    public NormalMeta() {
        System.out.println("component注解的普通bean");
    }
}
