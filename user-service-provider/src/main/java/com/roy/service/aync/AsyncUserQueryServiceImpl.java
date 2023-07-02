package com.roy.service.aync;

import com.roy.service.async.AsyncUserQueryService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务提供方异步方法：
 * 通过AsyncContext asyncContext = RpcContext.startAsync(); 开启异步
 * 返回CompletableFuture异步处理
 */
public class AsyncUserQueryServiceImpl implements AsyncUserQueryService {

    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 4,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


    @Override
    public String queryUserInfo(String id) {
        String resultMsg = "id = " + id + ",come from 上海张江";
        System.out.println(resultMsg);
        //开启异步
        AsyncContext asyncContext = RpcContext.startAsync();
        poolExecutor.execute(() -> {
            // 2. 如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 3. 将业务结果写回
            System.out.println("invoke result: " + resultMsg);
            asyncContext.write(resultMsg);
        });

        return null;
    }

    @Override
    public CompletableFuture<String> queryUserInfoByName(String name) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String resultMsg = "async name = " + name + ", come from 上海张江";
            return resultMsg;
        });
        return completableFuture;
    }
}
