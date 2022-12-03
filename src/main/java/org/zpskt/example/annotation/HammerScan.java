package org.zpskt.example.annotation;

import com.dtflys.forest.springboot.annotation.ForestScannerRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({HammerScannerRegister.class})//向IOC注入ForestScannerRegister类
public @interface HammerScan {

    String[] value() default {};

    String configuration() default "";

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

}
