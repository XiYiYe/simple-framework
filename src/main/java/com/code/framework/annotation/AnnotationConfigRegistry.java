package com.code.framework.annotation;

public interface AnnotationConfigRegistry {

    void register(Class<?>... annotatedClasses);

    void scan(String... basePackages);

}
