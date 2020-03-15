package com.code.framework.annotation;

import com.code.framework.context.support.GenericApplicationContext;

/**
 * 处理注解方式bean的spring管理
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigApplicationContext(String ... basePackage) {
        this();
        scan(basePackage);
        refresh();
    }

    public AnnotationConfigApplicationContext() {
        this.scanner = new ClassPathBeanDefinitionScanner(this);
    }

    @Override
    public void register(Class<?>... annotatedClasses) {

    }

    @Override
    public void scan(String... basePackages) {
        scanner.doScan(basePackages);
    }
}
