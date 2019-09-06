package com.foodkeeper.controller;

import com.foodkeeper.domain.CommonResponse;
import com.foodkeeper.domain.NotificationItemDto;
import com.foodkeeper.service.OrderItemBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderItemBiz orderItemBiz;

    @GetMapping("/lists")
    public ResponseEntity getNotificationItemList() {
        try {
            List<NotificationItemDto> notificationItemDtoList = orderItemBiz.getNotificationItemList();
            if (notificationItemDtoList.isEmpty()) {
                String message = "알림 대상이 없습니다";
                logger.error(message);
                return new ResponseEntity(new CommonResponse("ERROR", "알림 대상이 없습니다"), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(new CommonResponse("SUCCESS", notificationItemDtoList), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(new CommonResponse("ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
