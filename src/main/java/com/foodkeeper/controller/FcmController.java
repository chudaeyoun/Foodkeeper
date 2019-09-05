package com.foodkeeper.controller;


import com.foodkeeper.service.PcmBizImpl;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/fcm")
public class FcmController {

    public static final Logger logger = LoggerFactory.getLogger(FcmController.class);

    @Autowired
    PcmBizImpl pcmBizImpl;

    @PostMapping("/push")
    public ResponseEntity pushFcm(@RequestBody Map<String, Object> paramInfo) {

        if (paramInfo == null) {
            String message = "param 를 확인해주세요. 빈 값입니다.";
            logger.error(message);
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> retVal = Maps.newHashMap();

        logger.info("token: " + paramInfo.get("token"));

        //FCM 메시지 전송//
        JsonObject body = new JsonObject();

        body.addProperty("to", "clientToken"); //여러개의 메시지일 경우 registration_ids, 단일 메세지는 to사용//

        JsonObject notification = new JsonObject();
        notification.addProperty("title", "FCM Test App");
        notification.addProperty("body", "받아랏 태호");

        body.add("notification", notification);

        logger.info("body: " + body.toString());

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = pcmBizImpl.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}
