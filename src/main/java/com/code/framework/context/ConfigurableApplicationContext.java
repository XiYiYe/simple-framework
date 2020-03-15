package com.code.framework.context;

import com.code.framework.config.ConfigurableListableBeanFactory;

public interface ConfigurableApplicationContext extends ApplicationContext {


    ConfigurableListableBeanFactory getBeanFactory();


}
