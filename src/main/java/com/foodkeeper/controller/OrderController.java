package com.foodkeeper.controller;

import com.foodkeeper.domain.*;
import com.foodkeeper.repository.OrderItemRepository;
import com.foodkeeper.repository.OrderRepository;
import com.foodkeeper.repository.SkuRepository;
import com.foodkeeper.repository.UserRepository;
import com.foodkeeper.service.OrderBiz;
import com.foodkeeper.service.OrderItemBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderBiz orderBiz;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private OrderItemBiz orderItemBiz;

    @GetMapping("/lists")
    public ResponseEntity getOrderList(@RequestParam("userId") Long userId) {
        logger.info("param {userId} =>" + userId);

        if (userId == null) {
            String message = "파라미터 확인을 해주세요. userId가 비어 있습니다";
            logger.error(message);
            return new ResponseEntity(new CommonResponse("ERROR", message), HttpStatus.BAD_REQUEST);
        }

        try {
            List<Order> orderList = orderBiz.getOrderListByUserId(userId);
            if (orderList == null) {
                String message = "해당 사용자와 일치하는 주문내역이 없습니다.";
                logger.error(message);
                return new ResponseEntity(new CommonResponse("ERROR", message), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(new CommonResponse("SUCCESS", orderList), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(new CommonResponse("ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto) {
        User user = userRepository.findByCustNo(orderDto.getCustNo());
        if (user == null) {
            String message = "custNo를 확인해주세요. 일치하는 사용자가 없습니다.";
            logger.error(message);
            return new ResponseEntity(new CommonResponse("ERROR", message), HttpStatus.BAD_REQUEST);
        }

        // 하루 지났을 경우, 다시 알려줘야 함
        orderItemBiz.enableNotification();

        int totalPrice = 0;
        try {
            Order order = getOrder(user);

            for (Long skuId : orderDto.getOrderMap().keySet()) {
                Optional<Sku> sku = skuRepository.findById(skuId);
                if (sku.isPresent()) {
                    int quantity = orderDto.getOrderMap().get(skuId);
                    totalPrice += sku.get().getPrice() * quantity;
                    OrderItem orderItem = OrderItem.builder()
                            .order(order)
                            .user(order.getUser())
                            .sku(sku.get())
                            .quantity(quantity)
                            .use(true)
                            .noti(true)
                            .build();
                    orderItemRepository.save(orderItem);
                } else {
                    String message = "일치하는 상품이 없습니다 skuId =>> " + skuId;
                    logger.error(message);
                }
            }

            order.setTotalPrice(totalPrice);
            orderRepository.save(order);
            return new ResponseEntity(new CommonResponse("SUCCESS", "정상적으로 주문(결제) 되었습니다"), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(new CommonResponse("ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Order getOrder(User user) {
        return Order.builder().user(user).totalPrice(0).build();
    }
}
