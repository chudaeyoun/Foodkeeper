package com.foodkeeper.controller;

import com.foodkeeper.domain.CommonResponse;
import com.foodkeeper.domain.User;
import com.foodkeeper.service.UserBiz;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserBiz userBiz;

    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestParam("userId") String userId) {
        logger.info("param {userId} =>" + userId);

        if (Strings.isNullOrEmpty(userId)) {
            String message = "파라미터 확인을 해주세요. param {userId} => " + userId;
            logger.error(message);
            return new ResponseEntity(new CommonResponse("ERROR", message), HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userBiz.getUserByUserId(userId);
            if (user == null) {
                String message = "userId와 일치하는 User가 없습니다";
                logger.error(message);
                return new ResponseEntity(new CommonResponse("ERROR", message), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(new CommonResponse("SUCCESS", user), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(new CommonResponse("ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
