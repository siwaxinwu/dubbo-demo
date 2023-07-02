package com.roy.service;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 01:50 2023/5/7
 */
public interface HelloDubbo {
    String sayHello(String name);
    String sayHello(String name,int timeToWait);
}
