package com.code.framework.annotation;

import com.code.framework.config.BeanDefinition;
import com.code.framework.config.BeanNameGenerator;
import com.code.framework.stereotype.Component;
import com.code.framework.support.BeanDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    public final static AnnotationBeanNameGenerator INSTANCE = new AnnotationBeanNameGenerator();

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
            String beanClassName = definition.getBeanClassName();
            try {
                Class<?> clazz = this.getClass().getClassLoader().loadClass(beanClassName);
                Component component = clazz.getAnnotation(Component.class);
                String value = component.value();
                if (StringUtils.isNotBlank(value)) {
                    return value;
                }
            } catch (ClassNotFoundException ex) {
                log.warn("ClassNotFoundException,", ex);
            }
        }
        return buildDefaultBeanName(definition);
    }

    private String buildDefaultBeanName(BeanDefinition beanDefinition) {
        String beanName = beanDefinition.getBeanClassName();
        String simpleClassName = beanName.substring(beanName.lastIndexOf(".") + 1);
        char[] nameChar = simpleClassName.toCharArray();
        if (nameChar[0] >= 'A' && nameChar[0] <= 'Z') {
            nameChar[0] += 32;
        }
        return new String(nameChar);
    }
}
