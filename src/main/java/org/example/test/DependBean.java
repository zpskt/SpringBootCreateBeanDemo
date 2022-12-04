package org.example.test;


import org.example.demo.Meta;

@Meta
public class DependBean {
    public DependBean(NormalMeta normalBean) {
        System.out.println("我是个Meta bean，我依赖于component注解下的bean " + normalBean);
    }
}
