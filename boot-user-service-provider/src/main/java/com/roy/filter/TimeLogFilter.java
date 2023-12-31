package com.roy.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


/**
 * 自实现的日志过滤器
 */
@Activate(group = Constants.PROVIDER)
public class TimeLogFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(TimeLogFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //计时器
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //调用结果
        boolean invokeSuccess = true;
        try {
            Result result = invoker.invoke(invocation);
            return result;
        }catch (Exception e){
            invokeSuccess = false;
            throw e;
        }finally {
            //结束计时，打印监控日志
            stopWatch.stop();
            log(invoker,invocation,invokeSuccess,stopWatch.getTotalTimeMillis());
        }
    }

    public void log(Invoker<?> invoker, Invocation invocation,boolean invokeSuccess,long usedTimeMillis){
        try {

            //模拟示例：打印出调用的接口名、方法名、调用结果和耗时
            String interfaceName = invoker.getInterface().getName();
            String methodName = invocation.getMethodName();
            logger.info("{}.{} {} {}",interfaceName,methodName,invokeSuccess,usedTimeMillis);
        } catch (Throwable t) {
            logger.warn("Log error.", t);
        }
    }

}
