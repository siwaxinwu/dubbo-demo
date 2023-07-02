package com.roy.service;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 17:16 2023/1/15
 */
@Slf4j
public class DubboGenericInvoke {
    private static Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new ConcurrentHashMap<>();

    public static Object invoke(String interfaceClass, String methodName, String[] paramTypes, Object[] params) {
        ReferenceConfig<GenericService> referenceConfig;
        String key = interfaceClass;
        try {
            referenceConfig = referenceConfigMap.get(key);
            if (referenceConfig == null) {
                referenceConfig = new ReferenceConfig<>();
                RegistryConfig registry = new RegistryConfig("zookeeper://1.117.109.40:2181?backup=1.117.109.40:2182,1.117.109.40:2183");
                referenceConfig.setRegistry(registry);
                referenceConfig.setGeneric("true");
                referenceConfig.setInterface(interfaceClass);
                referenceConfig.setCheck(true);
                referenceConfigMap.put(key, referenceConfig);
            }
            GenericService genericService = referenceConfigMap.get(key).get();
            Object result = genericService.$invoke(methodName, paramTypes, params);
            if (result == null) {
                log.info("远程服务结果返回为空，请注意查看远程服务的参数：{}", params);
            }
            return result;
        } catch (GenericException e) {
            log.info("发起远程调用失败，错误信息：{}", e.getMessage());
            referenceConfigMap.remove(key);
            return null;

        } catch (Exception e) {
            log.info("远程服务获取结果失败，错误信息：{}", e.getMessage());
            referenceConfigMap.remove(key);
            return null;
        }
    }
}
