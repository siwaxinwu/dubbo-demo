package com.roy.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;


/**
 * 权限校验的服务消费者端过滤器
 *
 * 为服务消费者提供传递Token的功能
 */
@Activate(group = Constants.CONSUMER,value = "myAuthConsumer")
public class MyAuthConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //塞入token
        putToken(invoker,invocation);
        //继续执行调用链
        return invoker.invoke(invocation);
    }

    //校验权限
    public void putToken(Invoker<?> invoker, Invocation invocation){
        String token = invoker.getUrl().getParameter("token");
        invocation.getAttachments().put("token",token);
    }
}
