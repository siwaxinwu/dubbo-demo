package com.roy.service;


import com.roy.bean.UserAddress;

import java.util.List;

public interface UserService {
	
	/**
	 * 按照用户id返回所有的收货地址
	 */
	public List<UserAddress> getUserAddressList(String userId);


	public List<UserAddress> getUserAddressList(String userId, String name);


	public List<UserAddress> getUserAddressList(UserAddress userAddress);

	public List<UserAddress> getUserAddressList(List<UserAddress> userAddressList);


	public List<UserAddress> getUserAddressList(UserAddress[] userAddresses);
}
