package com.foodkeeper.service;


import com.foodkeeper.domain.FcmDto;
import org.springframework.http.HttpEntity;

import java.util.concurrent.CompletableFuture;

public interface PcmBiz {
    void send(FcmDto fcmDto);

    CompletableFuture<String> send(HttpEntity<String> entity);

}
