package com.foodkeeper.service;

import com.foodkeeper.domain.FcmDto;
import com.foodkeeper.interceptor.PcmRequestInterceptor;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PcmBizImpl implements PcmBiz {

    public static final Logger logger = LoggerFactory.getLogger(PcmBizImpl.class);

    private static final String FIREBASE_SERVER_KEY = "AAAAAJISX0E:APA91bFxi3ohERc-GwyF8mQunobLJKpUMsQi4pliJOX9-IlhgVbDbG60B8haYIeD_8QPkU1mwWWr4aoIV5rhC5WMjR4zZNtv9wA9-XWOzs5aKpqi9zsqHSRb4wIuo4CT_JkYR7pog25F";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    @Override
    public CompletableFuture<String> send(HttpEntity<String> entity) {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = Lists.newArrayList();

        interceptors.add(new PcmRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new PcmRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

    @Async
    @Override
    public void send(FcmDto fcmDto) {
        HttpEntity<String> entity = SetFcmRequest(fcmDto);

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
        CompletableFuture<String> pushNotification = CompletableFuture.completedFuture(firebaseResponse);
        CompletableFuture.allOf(pushNotification).join();

        try {
            logger.info("firebaseResponse info : " + pushNotification.get());
        } catch (Exception e) {
            logger.error("CompletableFuture 가져오는 중 에러가 발생되었습니다. err = " + e);
        }
    }

    private HttpEntity<String> SetFcmRequest(FcmDto fcmDto) {
        JsonObject body = new JsonObject();

        //여러개의 메시지일 경우 registration_ids, 단일 메세지는 to 사용
        //body.addProperty("to", fcmDto.getToken()); //token 으로 보낼 경우
        body.addProperty("to", "/topics/all"); //토픽으로 브로그 캐스트

        JsonObject notification = new JsonObject();
        notification.addProperty("title", fcmDto.getTitle());
        notification.addProperty("body", fcmDto.getBody());

        body.add("notification", notification);
        return new HttpEntity<>(body.toString());
    }
}
