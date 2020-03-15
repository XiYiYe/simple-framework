package com.code.framework.web.adapter;

import com.code.framework.web.controller.HttpController;

public class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof HttpController;
    }

    @Override
    public void handle(Object handler) {
        ((HttpController) handler).handlerHttp();
    }
}
