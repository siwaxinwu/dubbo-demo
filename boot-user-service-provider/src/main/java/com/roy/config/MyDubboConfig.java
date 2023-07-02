package com.roy.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyDubboConfig {

	/**
	 * 声明应用名
	 */
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("boot-user-service-provider");
		return applicationConfig;
	}

	/**
	 * 声明注册中心配置
	 */
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig config = new RegistryConfig("zookeeper://1.117.109.40:2181?backup=1.117.109.40:2182,1.117.109.40:2183");
		return config;
	}

	@Bean
	public ProtocolConfig protocolConfig(){
		ProtocolConfig config = new ProtocolConfig();
		config.setName("dubbo");
		config.setPort(28100);
		return config;
	}



}
