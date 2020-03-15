package com.code.framework.bean;

public class ComputerImpl implements Computer {

    @Override
    public void sing() {
        System.out.println("唱歌");
    }

    @Override
    public void move(String type) {
        System.out.println("跳舞:" + type);
    }
}
