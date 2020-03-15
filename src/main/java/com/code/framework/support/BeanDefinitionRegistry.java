package com.code.framework.support;

import com.code.framework.config.BeanDefinition;

/**
 * BeanDefinition注册和处理
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册bean
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
