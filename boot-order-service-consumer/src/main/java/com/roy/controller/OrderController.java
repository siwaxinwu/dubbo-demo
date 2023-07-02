package com.roy.controller;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.roy.bean.UserAddress;
import com.roy.service.DubboGenericInvoke;
import com.roy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 演示各种类型的参数泛化调用
 */
@Controller
@ResponseBody
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping("/initOrder")
	public List<UserAddress> initOrder(@RequestParam("uid")String userId) {
		return orderService.initOrder(userId);
	}


	@RequestMapping("/generic")
	public List<UserAddress> initOrder1() {
		ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
		referenceConfig.setApplication(new ApplicationConfig("boot-order-service-consumer"));
		referenceConfig.setRegistry(new RegistryConfig("zookeeper://1.117.109.40:2181?backup=1.117.109.40:2182,1.117.109.40:2183"));
		referenceConfig.setInterface("com.roy.service.UserService");
		referenceConfig.setGeneric(true);
		GenericService genericService = referenceConfig.get();
		Object result = genericService.$invoke(
				"getUserAddressList",
				new String[]{"java.lang.String"},
				new Object[]{"1234"});
		System.out.println(result);
		List<UserAddress> userAddressList = (List<UserAddress>) result;
		System.out.println(userAddressList);
		return userAddressList;
	}


	@RequestMapping("/actual/generic2")
	public List<UserAddress> initOrder2() {
		Object result = DubboGenericInvoke.invoke("com.roy.service.UserService", "getUserAddressList", new String[]{"java.lang.String"}, new Object[]{"1234"});
		System.out.println(result);
		List<UserAddress> userAddressList = (List<UserAddress>) result;
		System.out.println(userAddressList);
		return userAddressList;
	}


	/**
	 * 两个String参数
	 */
	@RequestMapping("/actual/generic3")
	public List<UserAddress> initOrder3() {
		Object result = DubboGenericInvoke.invoke("com.roy.service.UserService", "getUserAddressList",
				new String[]{"java.lang.String","java.lang.String"},
				new Object[]{"123","dingyawu"});
		System.out.println(result);
		List<UserAddress> userAddressList = (List<UserAddress>) result;
		System.out.println(userAddressList);
		return userAddressList;
	}


	/**
	 * 传的是一个对象
	 */
	@RequestMapping("/actual/generic4")
	public List<UserAddress> initOrder4() {

		Map<String,Object> UserAddressMap = new HashMap<>();
		{
			UserAddressMap.put("id",12345);
			UserAddressMap.put("userAddress","苏州市");
			UserAddressMap.put("userId","123");
			UserAddressMap.put("consignee","丁老师");
			UserAddressMap.put("phoneNum","15371452705");
			UserAddressMap.put("isDefault","N");
			UserAddressMap.put("userId","123");
		}

		Object result = DubboGenericInvoke.invoke("com.roy.service.UserService", "getUserAddressList",
				new String[]{"com.roy.bean.UserAddress"},
				new Object[]{UserAddressMap});
		System.out.println(result);
		List<UserAddress> userAddressList = (List<UserAddress>) result;
		System.out.println(userAddressList);
		return userAddressList;
	}


	/**
	 * 传的是一个list
	 */
	@RequestMapping("/actual/generic5")
	public List<UserAddress> initOrder5() {
		List<UserAddress> userAddresses = new ArrayList<>();
		UserAddress userAddress = new UserAddress();
		userAddress.setId(12345);
		userAddress.setUserAddress("苏州市");
		userAddress.setUserId("123");
		userAddress.setConsignee("丁老师");
		userAddress.setPhoneNum("15371452705");
		userAddress.setIsDefault("N");
		userAddresses.add(userAddress);

		Object result = DubboGenericInvoke.invoke("com.roy.service.UserService", "getUserAddressList",
				new String[]{"java.util.List"},
				new Object[]{userAddresses});
		System.out.println(result);
		List<UserAddress> userAddressList = (List<UserAddress>) result;
		System.out.println(userAddressList);
		return userAddressList;
	}


	/**
	 * 传的是一个对象数组
	 */
	@RequestMapping("/actual/generic6")
	public List<UserAddress> initOrder6() {
		UserAddress[] userAddresses = new UserAddress[1];
		UserAddress userAddress = new UserAddress();
		userAddress.setId(12345);
		userAddress.setUserAddress("苏州市");
		userAddress.setUserId("123");
		userAddress.setConsignee("丁老师");
		userAddress.setPhoneNum("15371452705");
			userAddress.setIsDefault("N");
		userAddresses[0] = userAddress;

		Object result = DubboGenericInvoke.invoke("com.roy.service.UserService", "getUserAddressList",
				new String[]{"com.roy.bean.UserAddress[]"},
				new Object[]{userAddresses});
		System.out.println(result);
		List<UserAddress> userAddressList = (List<UserAddress>) result;
		System.out.println(userAddressList);
		return userAddressList;
	}







}
