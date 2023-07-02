package com.roy.controller;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 通用的泛化调用
 */




@RestController
public class CommonOrderController {

	public static final String SUCC = "000000";

	// 定义URL地址
	@PostMapping("/gateway/{className}/{mtdName}/{parameterTypeName}/request")
	public String commonRequest(@PathVariable String className,
								@PathVariable String mtdName,
								@PathVariable String parameterTypeName,
								@RequestBody String reqBody){
		// 将入参的req转为下游方法的入参对象，并发起远程调用
		return commonInvoke(className, parameterTypeName, mtdName, reqBody);
	}

	/**
	 * <h2>模拟公共的远程调用方法.</h2>
	 *
	 * @param className：下游的接口归属方法的全类名。
	 * @param mtdName：下游接口的方法名。
	 * @param parameterTypeName：下游接口的方法入参的全类名。
	 * @param reqParamsStr：需要请求到下游的数据。
	 * @return 直接返回下游的整个对象。
	 */
	public static String commonInvoke(String className,
									  String parameterTypeName,
									  String mtdName,
									  String reqParamsStr) {
		try {
			// 然后试图通过类信息对象想办法获取到该类对应的实例对象
			ReferenceConfig<GenericService> referenceConfig = createReferenceConfig(className);
			// 远程调用
			GenericService genericService = referenceConfig.get();
			Object resp = genericService.$invoke(
					mtdName,
					new String[]{parameterTypeName},
					new Object[]{JSON.parseObject(reqParamsStr, Map.class)});
			// 如果响应码为成功的话，则组装成功响应
			return JSON.toJSONString(resp);
		}catch (Exception e){
			System.out.println(e);
		}
		return  null;
	}

	private static ReferenceConfig<GenericService> createReferenceConfig(String className) {

		// 设置应用服务名称
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("boot-order-service-consumer");

		// 设置注册中心的地址
		RegistryConfig registryConfig = new RegistryConfig("zookeeper://1.117.109.40:2181?backup=1.117.109.40:2182,1.117.109.40:2183");
		ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
		referenceConfig.setApplication(applicationConfig);
		referenceConfig.setRegistry(registryConfig);
		referenceConfig.setInterface(className);

		// 设置泛化调用形式
		referenceConfig.setGeneric("true");
		// 设置默认超时时间5秒
		referenceConfig.setTimeout(5 * 1000);
		return referenceConfig;
	}
}