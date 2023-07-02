package com.roy.service.async;

import java.util.concurrent.CompletableFuture;

public interface AsyncUserQueryService {

    String queryUserInfo(String id);

    CompletableFuture<String> queryUserInfoByName(String name);
}
