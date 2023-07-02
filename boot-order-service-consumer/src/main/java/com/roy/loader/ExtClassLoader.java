package com.roy.loader;


import com.alibaba.fastjson.JSON;
import com.roy.bean.UserAddress;
import com.roy.service.DubboGenericInvoke;

import java.util.List;

public class ExtClassLoader implements ILoader{

    @Override
    public String getLoaderName() {
        Object result = DubboGenericInvoke.invoke("com.roy.service.UserService", "getUserAddressList", new String[]{"java.lang.String"}, new Object[]{"1234"});
        System.out.println(result);
        List<UserAddress> userAddressList = (List<UserAddress>) result;
        System.out.println(userAddressList);
        return JSON.toJSONString(userAddressList);
        //return "我自己写的一个改动的ClassLoader!!!";
    }
}
