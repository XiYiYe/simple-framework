package com.code.framework.config;

public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    String getScope();

    void setScope(String scope);

    String getResource();

    void setResource(String resource);
}
