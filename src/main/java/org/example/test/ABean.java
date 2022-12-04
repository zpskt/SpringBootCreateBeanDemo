package org.example.test;

import org.springframework.stereotype.Component;

@Component
public class ABean {
    public ABean(MetaDemo metaDemo) {
        System.out.println("我在Bean容器中发现了Meta Bean : " + metaDemo);
    }
}

