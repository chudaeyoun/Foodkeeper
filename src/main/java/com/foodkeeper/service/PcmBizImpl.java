package com.foodkeeper.service;

import com.google.common.collect.Lists;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PcmBizImpl {
    private static final String FIREBASE_SERVER_KEY = "AAAAAJISX0E:APA91bFxi3ohERc-GwyF8mQunobLJKpUMsQi4pliJOX9-IlhgVbDbG60B8haYIeD_8QPkU1mwWWr4aoIV5rhC5WMjR4zZNtv9wA9-XWOzs5aKpqi9zsqHSRb4wIuo4CT_JkYR7pog25F";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        /**
         https://fcm.googleapis.com/fcm/send
         Content-Type:application/json
         Authorization:key=FIREBASE_SERVER_KEY*/

        List<ClientHttpRequestInterceptor> interceptors = Lists.newArrayList();

        interceptors.add(new PcmRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new PcmRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
