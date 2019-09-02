package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItemDto;

import java.util.List;

public interface OrderItemBiz {
    List<OrderItemDto> getOrderItemListByUserId(Long userId);
    void changeNotificationById(Long orderItemId);
}
