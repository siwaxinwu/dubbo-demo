package com.roy;

import com.roy.bean.ExtLoadInfo;
import com.roy.bean.Person;
import com.roy.controller.GroovyController;
import com.roy.loader.ILoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Objects;

/**
 * 我写的测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootConsumerAPPTests {

	@Autowired
	private ApplicationContext ac;

	@Autowired
	private GroovyController groovyController;

	@Test
	public void contextLoads() {
		Person person = ac.getBean("person", Person.class);
		System.out.println(person.getName());
		Map<String, ILoader> map = ac.getBeansOfType(ILoader.class);
		if (map.isEmpty()){
			ExtLoadInfo extLoadInfo = new ExtLoadInfo();
			extLoadInfo.setBeanName("roy1");
			extLoadInfo.setScriptBase64("cGFja2FnZSBjb20ucm95LmxvYWRlcjsKCgppbXBvcnQgY29tLmFsaWJhYmEuZmFzdGpzb24uSlNPTjsKaW1wb3J0IGNvbS5yb3kuYmVhbi5Vc2VyQWRkcmVzczsKaW1wb3J0IGNvbS5yb3kuc2VydmljZS5EdWJib0dlbmVyaWNJbnZva2U7CgppbXBvcnQgamF2YS51dGlsLkxpc3Q7CgpwdWJsaWMgY2xhc3MgRXh0Q2xhc3NMb2FkZXIgaW1wbGVtZW50cyBJTG9hZGVyewoKICAgIEBPdmVycmlkZQogICAgcHVibGljIFN0cmluZyBnZXRMb2FkZXJOYW1lKCkgewogICAgICAgIE9iamVjdCByZXN1bHQgPSBEdWJib0dlbmVyaWNJbnZva2UuaW52b2tlKCJjb20ucm95LnNlcnZpY2UuVXNlclNlcnZpY2UiLCAiZ2V0VXNlckFkZHJlc3NMaXN0IiwgbmV3IFN0cmluZ1tdeyJqYXZhLmxhbmcuU3RyaW5nIn0sIG5ldyBPYmplY3RbXXsiMTIzNCJ9KTsKICAgICAgICBTeXN0ZW0ub3V0LnByaW50bG4ocmVzdWx0KTsKICAgICAgICBMaXN0PFVzZXJBZGRyZXNzPiB1c2VyQWRkcmVzc0xpc3QgPSAoTGlzdDxVc2VyQWRkcmVzcz4pIHJlc3VsdDsKICAgICAgICBTeXN0ZW0ub3V0LnByaW50bG4odXNlckFkZHJlc3NMaXN0KTsKICAgICAgICByZXR1cm4gSlNPTi50b0pTT05TdHJpbmcodXNlckFkZHJlc3NMaXN0KTsKICAgICAgICAvL3JldHVybiAi5oiR6Ieq5bex5YaZ55qE5LiA5Liq5pS55Yqo55qEQ2xhc3NMb2FkZXIhISEiOwogICAgfQp9");
			String test = groovyController.test(extLoadInfo);
			Assert.assertTrue(Objects.equals(test, "我自己写的一个改动的ClassLoader!!!"));
		} else {
			Assert.fail();
		}



	}

}
