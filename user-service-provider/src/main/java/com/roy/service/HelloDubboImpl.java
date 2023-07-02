package com.roy.service;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 01:51 2023/5/7
 */

public class HelloDubboImpl implements HelloDubbo {
    @Override
    public String sayHello(String name) {
        return "provider2 ack:"+name;
    }
    @Override
    public String sayHello(String name, int timeToWait) {
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "provider ack:"+name;
    }
}
