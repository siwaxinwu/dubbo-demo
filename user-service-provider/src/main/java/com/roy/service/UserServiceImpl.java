package com.roy.service;


import com.roy.bean.UserAddress;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {

	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		System.out.println("UserServiceImpl 20880........");
		UserAddress address = new UserAddress(1, "上海市浦东新区张江集电港", "1", "丁老师", "15371452705", "Y");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Arrays.asList(address);
	}

	@Override
	public List<UserAddress> getUserAddressList(String userId, String name) {
		return null;
	}

	@Override
	public List<UserAddress> getUserAddressList(UserAddress userAddress) {
		return null;
	}

	@Override
	public List<UserAddress> getUserAddressList(List<UserAddress> userAddressList) {
		return null;
	}

	@Override
	public List<UserAddress> getUserAddressList(UserAddress[] userAddresses) {
		return null;
	}

}
