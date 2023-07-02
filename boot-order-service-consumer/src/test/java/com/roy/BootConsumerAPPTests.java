package com.roy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 我写的测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootConsumerAPPTests {

	@Autowired
	private ApplicationContext ac;



	@Test
	public void contextLoads() {


	}

}
