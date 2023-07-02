package com.roy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.roy.bean.UserAddress;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Service
@Component
public class UserServiceImpl implements UserService {

	//@HystrixCommand
	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		UserAddress address = new UserAddress(1, "上海市浦东新区张江集电港", userId, "丁老师", "15371452705", "Y");
		/*if(Math.random() > 0.5) {
			throw new RuntimeException();
		}*/
		System.out.println(address);
		return Arrays.asList(address);
	}

	@Override
	public List<UserAddress> getUserAddressList(String userId, String name) {
		UserAddress address = new UserAddress(2, "上海市浦东新区张江集电港", userId, name, "15371452705", "Y");
		return Arrays.asList(address);
	}

	@Override
	public List<UserAddress> getUserAddressList(UserAddress userAddress) {
		UserAddress address = new UserAddress(userAddress.getId(), userAddress.getUserAddress(), userAddress.getUserId(), userAddress.getConsignee(), userAddress.getPhoneNum(), userAddress.getIsDefault());
		return Arrays.asList(address);
	}

	@Override
	public List<UserAddress> getUserAddressList(List<UserAddress> userAddressList) {
		UserAddress userAddress = userAddressList.get(0);
		UserAddress address = new UserAddress(userAddress.getId(), userAddress.getUserAddress(), userAddress.getUserId(), userAddress.getConsignee(), userAddress.getPhoneNum(), userAddress.getIsDefault());
		return Arrays.asList(address);
	}

	@Override
	public List<UserAddress> getUserAddressList(UserAddress[] userAddresses) {
		UserAddress userAddress = userAddresses[0];
		UserAddress address = new UserAddress(userAddress.getId(), userAddress.getUserAddress(), userAddress.getUserId(), userAddress.getConsignee(), userAddress.getPhoneNum(), userAddress.getIsDefault());
		return Arrays.asList(address);
	}

}
