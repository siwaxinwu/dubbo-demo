package com.roy.service;


import com.roy.bean.UserAddress;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl2 implements UserService {

	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		System.out.println("UserServiceImpl 20882........");
		UserAddress address = new UserAddress(2, "苏州市吴中区独墅湖", "1", "丁老师", "15371452705", "N");
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
