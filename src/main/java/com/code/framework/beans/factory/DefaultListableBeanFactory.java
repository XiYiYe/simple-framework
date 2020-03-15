package com.code.framework.beans.factory;

import com.code.framework.config.BeanDefinition;
import com.code.framework.config.ConfigurableListableBeanFactory;
import com.code.framework.support.BeanDefinitionRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {

    /**
     * BeanDefinition容器
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(32);

    @Override
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName);
    }


    @Override
    public boolean containsBean(String beanName) {
        return false;
    }

    @Override
    public boolean isSingleton(String beanName) {
        return false;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }
}
