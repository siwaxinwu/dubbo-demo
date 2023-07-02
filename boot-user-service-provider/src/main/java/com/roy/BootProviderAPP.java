package com.roy;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@EnableDubbo
//@ImportResource(locations="classpath:provider.xml")
//@EnableDubbo(scanBasePackages="com.roy")
@EnableHystrix
@SpringBootApplication
public class BootProviderAPP {

	public static void main(String[] args) {
		SpringApplication.run(BootProviderAPP.class, args);
	}
}
