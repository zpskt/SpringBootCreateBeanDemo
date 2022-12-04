package org.example.demo;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Meta {
    /**
     * 元注解：类似于Component
     */
}
