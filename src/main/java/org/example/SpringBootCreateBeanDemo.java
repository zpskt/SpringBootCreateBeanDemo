package org.example;

import org.example.demo.MetaComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MetaComponentScan
public class SpringBootCreateBeanDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCreateBeanDemo.class, args);
    }
}