package com.code.framework.support;

import com.code.framework.config.BeanDefinitionHolder;

public class BeanDefinitionReaderUtils {

    public static void registerBeanDefinition(
            BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {
        // Register bean definition under primary name.
        String beanName = definitionHolder.getBeanName();
        registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());
    }

}
