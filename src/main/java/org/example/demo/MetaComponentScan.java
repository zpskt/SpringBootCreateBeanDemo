package org.example.demo;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MetaAutoConfigureRegistrar.class})
public @interface MetaComponentScan {

    /**
     * 当指定了值的时候，主要加载这些包路径下，包含@Meta注解的类；
     * 如果全是默认值（即为空），则扫描这个注解所在类对应的包路径下所有包含@Meta的类
     * @return
     */
    @AliasFor("basePackages") String[] value() default {};

    @AliasFor("value") String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}

