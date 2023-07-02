package com.roy.controller;

import com.roy.bean.ExtLoadInfo;
import com.roy.loader.GroovyLoader;
import com.roy.loader.ILoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GroovyController {

    @Autowired
    private GroovyLoader groovyLoader;

    @RequestMapping("/groovy")
    public String test(@RequestBody ExtLoadInfo extLoadInfo){
        String beanName = extLoadInfo.getBeanName();
        String scriptBase64 = extLoadInfo.getScriptBase64();
        Boolean existBeanName = groovyLoader.existBeanName(beanName, scriptBase64);
        if (existBeanName){
            throw new RuntimeException("beanName 已存在，请重新传参beanName：" + beanName);
        }
        Object instance = groovyLoader.getBean(beanName, scriptBase64);
        ILoader loader = (ILoader) instance;
        return loader.getLoaderName();
    }
}
