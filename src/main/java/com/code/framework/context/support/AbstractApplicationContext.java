package com.code.framework.context.support;

import com.code.framework.config.ConfigurableListableBeanFactory;
import com.code.framework.context.ConfigurableApplicationContext;

public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    /**
     * 模板模式，子类实现
     * @return
     */
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    public void refresh() {
        System.out.println("IOC刷新容器");
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return getBeanFactory().containsBean(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return getBeanFactory().isSingleton(beanName);
    }
}
