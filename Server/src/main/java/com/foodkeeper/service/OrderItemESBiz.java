package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItemESDto;

import java.util.List;

public interface OrderItemESBiz {
    List<OrderItemESDto> getOrderItemListByUserId(Long userId);
    void save(OrderItemESDto orderItemESDto);
    List<OrderItemESDto> findAll();
}
