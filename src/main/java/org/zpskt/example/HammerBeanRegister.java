package org.zpskt.example;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ResourceLoaderAware;

public class HammerBeanRegister implements ResourceLoaderAware, BeanPostProcessor {
    @Override
    public void setResourceLoader(org.springframework.core.io.ResourceLoader resourceLoader) {

    }
}
