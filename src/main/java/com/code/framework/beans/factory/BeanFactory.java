package com.code.framework.beans.factory;

public interface BeanFactory {

    Object getBean(String beanName);

    boolean containsBean(String beanName);

    boolean isSingleton(String beanName);

}
