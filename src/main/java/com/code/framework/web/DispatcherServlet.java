package com.code.framework.web;

import com.code.framework.web.adapter.AnnotationHandlerAdapter;
import com.code.framework.web.adapter.HandlerAdapter;
import com.code.framework.web.adapter.HttpHandlerAdapter;
import com.code.framework.web.adapter.ServletHandlerAdapter;
import com.code.framework.web.controller.Controller;
import com.code.framework.web.controller.HttpController;

import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {

    private static List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    static {
        handlerAdapters.add(new ServletHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new AnnotationHandlerAdapter());
    }

    public static HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.support(controller)) {
                return handlerAdapter;
            }
        }
        throw new RuntimeException("Not find HandlerAdapter");
    }

    public static void doDispatch() {
        HttpController httpController = new HttpController();
        HandlerAdapter handlerAdapter = getHandler(httpController);
        handlerAdapter.handle(httpController);
    }

    public static void main(String[] args) {
        doDispatch();
    }

}
