package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItem;

import java.util.List;

public interface OrderItemBiz {
    List<OrderItem> getOrderItemListByUserId(Long userId);
}
