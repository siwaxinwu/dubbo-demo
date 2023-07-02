package com.roy.service;

import com.roy.bean.UserAddress;

import java.util.List;


public class OrderServiceImpl implements OrderService {

	private UserService userService;

	@Override
	public List<UserAddress> initOrder(String userId) {
		System.out.println("用户id："+userId);
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		for (UserAddress userAddress : addressList) {
			System.out.println("get userinfoList:" + userAddress.getUserAddress());
		}
		return addressList;
	}
	
	

}
