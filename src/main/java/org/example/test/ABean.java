package org.example.test;

import org.springframework.stereotype.Component;

@Component
public class ABean {
    /**
     * 这里的构造函数用到MetaDemo，你的IDE可能会报错，因为无法自动装配
     * 因为Component等注解IDEA已经知道了，我们自定义的注解他还不认识，所以报错
     * 这里如果ABean也能成功构造，说明Bean已经注册成功了。
     * @param metaDemo
     */
    public ABean(MetaDemo metaDemo) {
        System.out.println("我在Bean容器中发现了Meta Bean : " + metaDemo);
    }
}

