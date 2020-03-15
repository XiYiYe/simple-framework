package com.code.framework.web.adapter;

import com.code.framework.web.controller.AnnotationController;

public class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof AnnotationHandlerAdapter;
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController)handler).handlerAnnotation();
    }
}
