package com.roy;
import com.roy.service.async.AsyncUserQueryService;
import com.roy.service.aync.AsyncUserQueryServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.junit.Test;

import java.io.IOException;

/**
 * 泛化调用测试异步
 */
public class ProviderApplication {
    @Test
    public void test() throws IOException {
        // 设置应用服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-testAycnc-provider");
        // 设置注册中心地址
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://1.117.109.40:2181?backup=1.117.109.40:2182,1.117.109.40:2183");

        // 创建暴露 UserUpdateFacade 这个服务的对象
        ServiceConfig<AsyncUserQueryService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(AsyncUserQueryService.class);
        serviceConfig.setRef(new AsyncUserQueryServiceImpl());

        serviceConfig.export();

        System.out.println("Dubbo " + AsyncUserQueryService.class.getSimpleName() + " started!");
        System.out.println(" ProviderApplication  server started!");

        System.in.read();

    }
}
