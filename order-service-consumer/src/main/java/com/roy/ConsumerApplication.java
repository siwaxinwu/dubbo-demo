package com.roy;

import com.roy.service.HelloDubbo;
import com.roy.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerApplication {
	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("consumer.xml");
		System.out.println("ConsumerApplication started success");

		//orderService(ac);
		helloDubboService(ac);
	}

	private static void orderService(ClassPathXmlApplicationContext ac) throws IOException {
		OrderService orderService = ac.getBean(OrderService.class);

		for (int i = 0; i < 10; i++) {
			orderService.initOrder("1");
			System.out.println("第" + (i + 1) +"次调用完成....");
		}
		System.in.read();
	}

	private static void helloDubboService(ClassPathXmlApplicationContext ac) throws IOException {
		final HelloDubbo bean = ac.getBean(HelloDubbo.class);
		while (true){
			System.in.read();
			String hello=bean.sayHello("test",100);
			System.out.println("consumer post:"+hello);
		}
	}

}
