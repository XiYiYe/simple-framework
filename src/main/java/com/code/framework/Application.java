package com.code.framework;


import com.code.framework.annotation.AnnotationConfigApplicationContext;
import com.code.framework.config.BeanDefinition;
import com.code.framework.context.ApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.code.framework");
        BeanDefinition beanDefinition = (BeanDefinition) applicationContext.getBean("studentA");
        System.out.println("student:" + beanDefinition.getBeanClassName() + beanDefinition.getResource());
    }

}
