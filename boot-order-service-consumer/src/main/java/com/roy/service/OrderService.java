package com.roy.service;

import com.roy.bean.UserAddress;

import java.util.List;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 17:51 2023/1/15
 */
public interface OrderService {

    /**
     * 初始化订单
     */
    public List<UserAddress> initOrder(String userId);

}
