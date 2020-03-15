package com.code.framework.web.adapter;

import com.code.framework.web.controller.ServletController;

public class ServletHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof ServletController;
    }

    @Override
    public void handle(Object handler) {
        ((ServletController) handler).handlerServlet();
    }
}
