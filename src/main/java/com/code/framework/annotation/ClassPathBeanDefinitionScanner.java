package com.code.framework.annotation;

import com.code.framework.config.BeanDefinition;
import com.code.framework.config.BeanDefinitionHolder;
import com.code.framework.config.BeanNameGenerator;
import com.code.framework.stereotype.Component;
import com.code.framework.stereotype.Scope;
import com.code.framework.support.BeanDefinitionReaderUtils;
import com.code.framework.support.BeanDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;

@Slf4j
public class ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;

    private BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                beanDefinition.setScope(resolveScope(beanDefinition));
                String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
                BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(beanDefinition, beanName);
                beanDefinition.setBeanClassName(beanName);
                System.out.println("扫描bean" + beanDefinition);
                registerBeanDefinition(beanDefinitionHolder, registry);
                beanDefinitions.add(beanDefinitionHolder);
            }
        }
        return beanDefinitions;
    }

    private void registerBeanDefinition(BeanDefinitionHolder beanDefinitionHolder, BeanDefinitionRegistry registry) {
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
    }

    private Set<BeanDefinition> findCandidateComponents(String path) {
        Set<BeanDefinition> result = new HashSet<>();
        URL resource = this.getClass().getClassLoader().getResource(path.replaceAll("\\.", "/"));
        if (resource == null) {
            return null;
        }
        String basePackage = resource.getFile();
        String basePath = this.getClass().getClassLoader().getResource("").getFile();
        basePath = basePath.substring(1).replaceAll("/", Matcher.quoteReplacement(File.separator));
        File dirFile = new File(basePackage);
        List<String> fileList = this.getResource(dirFile);
        try {
            for (String filePath : fileList) {
                String classPath = filePath.replace(basePath, "").replaceAll("\\.class", "").replaceAll("\\\\", "\\.");
                Class<?> loadClass = this.getClass().getClassLoader().loadClass(classPath);
                if (loadClass.isAnnotationPresent(Component.class)) {
                    ScannedGenericBeanDefinition scannedGenericBeanDefinition = new ScannedGenericBeanDefinition();
                    scannedGenericBeanDefinition.setBeanClassName(loadClass.getName());
                    scannedGenericBeanDefinition.setResource(filePath);
                    result.add(scannedGenericBeanDefinition);
                }
            }
        } catch (ClassNotFoundException ex) {
            log.warn("Class Not Found Exception,", ex);
        }
        return result;
    }




    private String resolveScope(BeanDefinition beanDefinition) {
        String scopeValue = BeanDefinition.SCOPE_SINGLETON;
        try {
            Class<?> clazz = this.getClass().getClassLoader().loadClass(beanDefinition.getBeanClassName());
            if (clazz.isAnnotationPresent(Scope.class)) {
                Scope scope = clazz.getAnnotation(Scope.class);
                if (StringUtils.isNotBlank(scope.value())) {
                    scopeValue = scope.value();
                }
            }
        } catch (ClassNotFoundException ex) {
            log.warn("ClassNotFoundException,", ex);
        }
        return scopeValue;
    }

    public static void main(String[] args) {
//        Set<BeanDefinition> set = new ClassPathBeanDefinitionScanner().doScan(new String[]{});
//        System.out.println("beanDefinition:" + set);
    }

    private List<String> getResource(File file) {
        List<String> list = new ArrayList<>();
        if (file.isFile()) {
            list.add(file.getAbsolutePath());
            return list;
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {
                for (File tempFile : files) {
                    if (tempFile.isFile()) {
                        list.add(tempFile.getAbsolutePath());
                    } else {
                        list.addAll(getResource(tempFile.getAbsoluteFile()));
                    }
                }
            }
        }
        return list;
    }


}

