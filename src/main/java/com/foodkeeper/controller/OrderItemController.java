package com.foodkeeper.controller;

import com.foodkeeper.domain.OrderItemDto;
import com.foodkeeper.domain.User;
import com.foodkeeper.service.OrderItemBiz;
import com.foodkeeper.service.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_item")
public class OrderItemController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private OrderItemBiz orderItemBiz;

    @GetMapping("/lists")
    public ResponseEntity getOrderItemList(@RequestParam("userId") String userId) {
        logger.info("param {userId} =>" + userId);

        User user = userBiz.getUserByUserId(userId);
        if (user == null) {
            String message = "파라미터 확인을 해주세요. userId와 일치하는 사용자가 없습니다";
            logger.error(message);
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }

        try {
            List<OrderItemDto> orderItemList = orderItemBiz.getOrderItemListByUserId(user.getId());
            if (orderItemList == null) {
                String message = "해당 사용자와 일치하는 주문 상품이 없습니다. (유통기한 +5일 이내)";
                logger.error(message);
                return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(orderItemList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/notification")
    public ResponseEntity changeNotification(@RequestParam("orderItemId") Long orderItemId) {
        try {
            orderItemBiz.changeNotificationById(orderItemId);
            return new ResponseEntity("정상적으로 수정되었습니다", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
