package com.roy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.roy.bean.UserAddress;
import com.roy.service.OrderService;
import com.roy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	//@Reference(parameters = {"token","123456"})
	@Reference
	UserService userService;
	
	@Override
	//@HystrixCommand(fallbackMethod="callbackFail")
	public List<UserAddress> initOrder(String userId) {
		System.out.println("用户id："+userId);
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}
	
	
	public List<UserAddress> callbackFail(String userId) {
		return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
	}
	

}
