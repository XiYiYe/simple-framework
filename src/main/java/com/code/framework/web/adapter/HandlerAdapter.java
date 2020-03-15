package com.code.framework.web.adapter;

public interface HandlerAdapter {

    boolean support(Object handler);

    void handle(Object handler);

}
