package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItemDto;

import java.util.HashMap;
import java.util.List;

public interface OrderItemBiz {
    HashMap<String, List<OrderItemDto>> getOrderItemMapByUserId(Long userId);
    void changeNotificationById(Long orderItemId);
}
