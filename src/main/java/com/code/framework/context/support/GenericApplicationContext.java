package com.code.framework.context.support;

import com.code.framework.beans.factory.DefaultListableBeanFactory;
import com.code.framework.config.BeanDefinition;
import com.code.framework.config.ConfigurableListableBeanFactory;
import com.code.framework.support.BeanDefinitionRegistry;

public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {

    /**
     * 委派模式
     */
    private DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

}
