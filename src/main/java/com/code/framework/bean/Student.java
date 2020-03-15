package com.code.framework.bean;

import com.code.framework.stereotype.Component;
import com.code.framework.stereotype.Scope;
import com.code.framework.config.BeanDefinition;
import lombok.Data;

@Component("studentA")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
public class Student {

    private String id;

    private String name;

    private String age;

    private String sex;

}
