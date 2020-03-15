package com.code.framework.config;

import lombok.Data;

@Data
public class BeanDefinitionHolder {

    private BeanDefinition beanDefinition;
    private String beanName;
    private String[] alias;

    public BeanDefinitionHolder(BeanDefinition beanDefinition) {
        this(beanDefinition, null, null);
    }

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
        this.alias = null;
    }

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName, String[] alias) {
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
        this.alias = alias;
    }
}
