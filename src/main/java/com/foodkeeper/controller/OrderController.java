package com.foodkeeper.controller;

import com.foodkeeper.domain.Order;
import com.foodkeeper.domain.User;
import com.foodkeeper.service.OrderBiz;
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

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderBiz orderBiz;

    @GetMapping("/lists")
    public ResponseEntity getOrderList(@RequestParam("userId") Long userId) {
        logger.info("param {userId} =>" + userId);

        if (userId == null) {
            String message = "파라미터 확인을 해주세요. userId가 비어 있습니다";
            logger.error(message);
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }

        try {
            List<Order> orderList = orderBiz.getOrderListByUserId(userId);
            if (orderList == null) {
                String message = "해당 사용자와 일치하는 주문내역이 없습니다.";
                logger.error(message);
                return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(orderList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
