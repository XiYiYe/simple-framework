package com.code.framework.config;

import com.code.framework.support.BeanDefinitionRegistry;

/**
 * bean别名生成接口
 */
public interface BeanNameGenerator {

    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);

}
