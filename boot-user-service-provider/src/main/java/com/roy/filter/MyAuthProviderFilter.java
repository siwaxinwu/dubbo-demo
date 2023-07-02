package com.roy.filter;


import com.alibaba.dubbo.rpc.*;
import org.apache.commons.lang.StringUtils;


/**
 * 权限校验的服务提供者端过滤器
 * 采用自动激活机制，自动在服务提供者端激活
 */
//@Activate(group = Constants.PROVIDER)
public class MyAuthProviderFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //校验权限
        checkAuth(invoker,invocation);
        //继续执行调用链
        return invoker.invoke(invocation);
    }

    //校验权限
    public void checkAuth(Invoker<?> invoker, Invocation invocation){
        String token = invocation.getAttachment("token");
        System.out.println("token : " + token);
        if(StringUtils.isBlank(token)){
            throw new RpcException("Token is blank.");
        }
    }
}
