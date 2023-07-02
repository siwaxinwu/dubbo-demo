package com.roy.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;


@Activate(group = CONSUMER)
public class ConsumerTokenFilter implements Filter {

    public static final String KEY_AUTH_TOKEN = "auth.token";

    public static final String TOKEN = "TOKEN";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 从方法层面获取 auth.token 参数值
        String authToken = invoker.getUrl().getMethodParameter
                    (invocation.getMethodName(), KEY_AUTH_TOKEN);
        // authToken 不为空的话则设置到请求对象中
        if (StringUtils.isNotBlank(authToken)) {
            invocation.getObjectAttachments().put(TOKEN, authToken);
        }

        // 继续后面过滤器的调用
        return invoker.invoke(invocation);
    }
}