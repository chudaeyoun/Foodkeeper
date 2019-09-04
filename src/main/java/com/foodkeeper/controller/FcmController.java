package com.foodkeeper.controller;


import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/fcm")
public class FcmController {

    public static final Logger logger = LoggerFactory.getLogger(FcmController.class);

    private static final String apiKey = "AAAAAJISX0E:APA91bFxi3ohERc-GwyF8mQunobLJKpUMsQi4pliJOX9-IlhgVbDbG60B8haYIeD_8QPkU1mwWWr4aoIV5rhC5WMjR4zZNtv9wA9-XWOzs5aKpqi9zsqHSRb4wIuo4CT_JkYR7pog25F";
    private static final String senderId = "2450677569";
    private static final String fcmUrl = "https://fcm.googleapis.com/fcm/send";

    @PostMapping("/push")
    public ResponseEntity pushFcm(@RequestBody String clientFcmToken) {

        if (clientFcmToken == null && "".equals(clientFcmToken)) {
            String message = "fcmToken 를 확인해주세요. 빈 값입니다.";
            logger.error(message);
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }

        //path 설정해야함
        String path = "C:/** .. **/webapp/resources/google/{fcm-test-*******************************.json}";
        String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
        String[] SCOPES = { MESSAGING_SCOPE };

        HttpURLConnection conn;
        OutputStreamWriter wr;
        BufferedReader br;
        URL url;

        try {
            // http 컨넥션 설정
            url = new URL(fcmUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + apiKey);
            conn.setRequestProperty("Content-Type", "application/json");

            //알림 + 데이터 메세지 형태의 전달
            JsonObject json = new JsonObject();
            JsonObject info = new JsonObject();
            JsonObject dataJson = new JsonObject();

            //앱 백그라운드 발송시 기본noti는 이 내용을 참조한다
            info.addProperty("title", "알림");
            //info.addProperty("body", msgBody); // Notification body
            info.addProperty("body", "test");
            info.addProperty("sound", "default");

            //noti 알림 부분
            json.add("notification", info);

            //디바이스전송 (앱단에서 생성된 토큰키)
            json.addProperty("to", clientFcmToken); // deviceID
            //json.addProperty("to", "/topics/" + topicsKey);

            return new ResponseEntity("정상적으로 client 로 전송 되었습니다", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
