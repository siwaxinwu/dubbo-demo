package com.roy.service.impl;

import com.roy.bean.UserAddress;
import com.roy.service.OrderService;
import com.roy.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@DubboReference(timeout = 10000, methods = {@Method(
			name = "getUserAddressList",
			parameters = {
					"auth.token", "dywceshi"
			})})
	private UserService userService;
	
	@Override
	public List<UserAddress> initOrder(String userId) {
		System.out.println("用户id："+userId);
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}
	
	
	public List<UserAddress> callbackFail(String userId) {
		return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
	}
	

}
